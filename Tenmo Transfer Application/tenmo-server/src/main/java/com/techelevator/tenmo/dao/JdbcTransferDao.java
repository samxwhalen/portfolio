package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.TransferNotFoundException;
import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDAO {

    private AccountsDao accountsDao;
    private UserDao userDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(AccountsDao accountDao, JdbcTemplate jdbcTemplate, UserDao userDoa) {
        this.accountsDao = accountDao;
        this.userDao = userDoa;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void sendTransfer(int senderId, int receiverId, BigDecimal amount) throws UserNotFoundException {

        if (accountsDao.getBalance(senderId).compareTo(accountsDao.getBalance(senderId).subtract(amount)) >= 0){

            accountsDao.subtractFromBalance(amount, senderId);
            accountsDao.addToBalance(amount, receiverId);

            String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                    "VALUES (?, ?, (SELECT account_id from accounts WHERE user_id = ?) , (SELECT account_id from accounts WHERE user_id = ?), ?)";
            jdbcTemplate.update(sql, getTransferTypeId("Send"), getTransferStatusId("Approved"), senderId, receiverId, amount);

            System.out.println("Transfer successful!");

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    @Override
    public String requestTransfer(int senderId, int receiverId, BigDecimal amount) throws UserNotFoundException {
        if (accountsDao.getBalance(senderId).compareTo(accountsDao.getBalance(senderId).subtract(amount)) >= 0){

            String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                    "VALUES (?, ?, (SELECT account_id from accounts WHERE user_id = ?) , (SELECT account_id from accounts WHERE user_id = ?), ?)";
            jdbcTemplate.update(sql, getTransferTypeId("Request"), getTransferStatusId("Pending"), senderId, receiverId, amount);

            return "Request sent!";

        } else {
            return "Insufficient Funds.";
        }
    }

    public void updateTransferRequest(Transfer transfer, String status) throws UserNotFoundException, TransferNotFoundException {
        if (status.equals("Approved")){
            if (accountsDao.getBalance(transfer.getSenderId()).compareTo(accountsDao.getBalance(transfer.getSenderId()).subtract(transfer.getAmount())) >= 0){

                accountsDao.subtractFromBalance(transfer.getAmount(), transfer.getSenderId());
                accountsDao.addToBalance(transfer.getAmount(), transfer.getReceiverId());

                String sql = "UPDATE transfers SET transfer_status_id = ? WHERE transfer_id = ?";
                jdbcTemplate.update(sql, getTransferStatusId("Approved"), transfer.getTransferId());
                accountsDao.addToBalance(transfer.getAmount(), transfer.getReceiverId());
                accountsDao.subtractFromBalance(transfer.getAmount(), transfer.getSenderId());
            } else {
                System.out.println("You do not have enough funds to fulfill this request. It will remaining pending until rejected.");
            }

        } else if (status.equals("Rejected")){
            String sql = "UPDATE transfers SET transfer_status_id = ? WHERE transfer_id = ?";
            jdbcTemplate.update(sql, getTransferStatusId("Rejected"), transfer.getTransferId());
        }
    }

    @Override
    public Transfer getTransferById(int transferId) throws TransferNotFoundException {
        Transfer transfer = null;

        String sql = "SELECT t.transfer_id, su.user_id AS senderId, t.account_from, t.account_to, t.amount, ru.user_id AS receiverId, su.username AS senderName, ru.username AS recName," +
                "ts.transfer_status_id, ts.transfer_status_desc, tt.transfer_type_id, tt.transfer_type_desc FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id " +
                "JOIN accounts b ON t.account_to = b.account_id " +
                "JOIN users su ON su.user_id = a.user_id " +
                "JOIN users ru on ru.user_id = b.user_id " +
                "JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id " +
                "JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id " +
                "WHERE t.transfer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()){
            transfer = mapRowToTransfer(results);
        }

        return transfer;
    }

    @Override
    public int getTransferTypeId(String type){

        String sql = "SELECT transfer_type_id FROM transfer_types tt " +
                "WHERE tt.transfer_type_desc = ?";

        int transferTypeId = jdbcTemplate.queryForObject(sql, Integer.class, type);

        return transferTypeId;
    }

    @Override
   public int getTransferStatusId(String status){

       String sql = "SELECT transfer_status_id  FROM transfer_statuses tsd " +
               "WHERE tsd.transfer_status_desc = ?";

       int transferStatusId = jdbcTemplate.queryForObject(sql, Integer.class, status);

       return transferStatusId;
   }

    @Override
     public List<Transfer> getAllUserTransfers(int userId){
        List<Transfer> allTransfers = new ArrayList<>();
        String sql = "SELECT t.transfer_id, su.user_id AS senderId, t.account_from, t.account_to, t.amount, ru.user_id AS receiverId, su.username AS senderName, ru.username AS recName," +
                "ts.transfer_status_id, ts.transfer_status_desc, tt.transfer_type_id, tt.transfer_type_desc FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id " +
                "JOIN accounts b ON t.account_to = b.account_id " +
                "JOIN users su ON su.user_id = a.user_id " +
                "JOIN users ru on ru.user_id = b.user_id " +
                "JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id " +
                "JOIN transfer_types tt ON tt.transfer_type_id = t.transfer_type_id " +
                "WHERE a.user_id = ? OR b.user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next() ) {
            Transfer transfer = mapRowToTransfer(results);
            allTransfers.add(transfer);
        }

        return allTransfers;
     }


    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));


        transfer.setSenderId(rs.getInt("senderId"));
        transfer.setReceiverId(rs.getInt("receiverId"));

        transfer.setSenderName(rs.getString("senderName"));
        transfer.setRecName(rs.getString("recName"));

        transfer.setTransferStatusDescription(rs.getString("transfer_status_desc"));
        transfer.setTransferTypeDescription(rs.getString("transfer_type_desc"));

        return transfer;
    }

}
