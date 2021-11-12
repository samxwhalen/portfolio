package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountsDao {

BigDecimal getBalance(int userId) throws UserNotFoundException;

Account getAccount(int userId) throws UserNotFoundException;

void addToBalance(BigDecimal addedAmount, int userId) throws UserNotFoundException;

void subtractFromBalance(BigDecimal subtractedAmount, int userId) throws UserNotFoundException;

}
