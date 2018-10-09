package com.kata.bankaccount.services;

import com.kata.bankaccount.domain.BankAccount;
import com.kata.bankaccount.domain.Operation;
import com.kata.bankaccount.domain.OperationType;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class OperationServiceImpl implements OperationService {

    private final static Logger LOGGER = Logger.getLogger(OperationServiceImpl.class);


    @Override
    public BigDecimal deposit(BankAccount bankAccount, Operation operation) {
        BigDecimal newBalance = bankAccount.getBalance().add(operation.getOperationAmount());
        bankAccount.setAccountAmount(newBalance);
        bankAccount.addOperation(new Operation(operation.getOperationAmount(), OperationType.PUT,new Date()));
        return bankAccount.getBalance();
    }

    @Override
    public synchronized BigDecimal withdraw(BankAccount bankAccount, Operation operation) {
        BigDecimal newBalance = bankAccount.getBalance().subtract(operation.getOperationAmount());
        if(newBalance.signum() == -1)
        {
            LOGGER.info("cannot withraw " + operation.getOperationAmount() + ", not enough money for: "+ Thread.currentThread() + " to withdraw");
            return bankAccount.getBalance();
        }
        LOGGER.info(Thread.currentThread() + " is going to withdraw by: " + operation.getOperationAmount());
        bankAccount.setAccountAmount(newBalance);
        bankAccount.addOperation(new Operation(operation.getOperationAmount(), OperationType.TAKE ,new Date()));
        LOGGER.info(Thread.currentThread() + " has withdrawn by: " + operation.getOperationAmount());

        return bankAccount.getBalance();
    }

    @Override
    public Collection<Operation> getOperationsHistory(BankAccount bankAccount) {
        return bankAccount.getOperations();
    }


}
