package com.kata.bankaccount.services;

import com.kata.bankaccount.domain.BankAccount;
import com.kata.bankaccount.domain.Operation;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Operation Service interface.
 *     1/ deposit service
 *     2/ withdraw service
 *     3/ getOperationsHistory history service
 */
public interface OperationService {

    BigDecimal deposit(BankAccount bankAccount, Operation operation);

    BigDecimal withdraw(BankAccount bankAccount, Operation operation);

    Collection<Operation> getOperationsHistory(BankAccount bankAccount);
}
