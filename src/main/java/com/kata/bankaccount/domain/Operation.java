package com.kata.bankaccount.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

    private BigDecimal operationAmount = BigDecimal.ZERO;

    private OperationType operationType;

    private Date date;

    public Operation(BigDecimal operationAmount, OperationType operationType, Date date) {
        this(operationAmount, date);
        this.operationType = operationType;
    }

    public BigDecimal getOperationAmount() {
        BigDecimal operationAmount = this.operationAmount;
        return operationAmount;
    }

    public OperationType getOperationType() {
        OperationType operationType = this.operationType;
        return operationType;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public Operation(BigDecimal operationAmount, Date date) {
        this.operationAmount = operationAmount == null ? this.operationAmount : operationAmount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationAmount=" + operationAmount +
                ", operationType=" + operationType +
                ", date=" + date +
                '}';
    }
}
