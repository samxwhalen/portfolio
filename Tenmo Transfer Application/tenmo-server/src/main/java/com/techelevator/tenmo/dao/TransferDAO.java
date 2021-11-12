package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.TransferNotFoundException;
import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


public interface TransferDAO {

    Transfer getTransferById(int transferId) throws TransferNotFoundException;

    int getTransferStatusId(String status);

    int getTransferTypeId(String type);

    List<Transfer> getAllUserTransfers(int userId);

    void sendTransfer(int senderId, int receiverId, BigDecimal amount) throws UserNotFoundException;

    String requestTransfer(int userFrom, int userTo, BigDecimal amount) throws UserNotFoundException;

    void updateTransferRequest(Transfer transfer, String status) throws UserNotFoundException, TransferNotFoundException;



}
