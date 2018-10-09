package com.kata.bankaccount.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String owner;

    private List<Operation> operations = new ArrayList<>();

    private BigDecimal balance = BigDecimal.ZERO;

    public BankAccount(String owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount() {

    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = this.balance;
        return balance;
    }

    public List<Operation> getOperations() {
        return new ArrayList<>(operations);
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.balance = accountAmount;
    }
}
