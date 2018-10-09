package com.kata.bankaccount.domain;

public enum OperationType {

    PUT("PUT","+"),
    TAKE("TAKE","-");

    private String operationType;

    private String operationSign;

    OperationType(String operationType, String operationSign) {
        this.operationType = operationType;
        this.operationSign = operationSign;
    }

    @Override
    public String toString() {
        return "OperationType{" +
                "operationType='" + operationType + '\'' +
                ", operationSign='" + operationSign + '\'' +
                '}';
    }
}
