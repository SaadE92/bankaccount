package com.kata.bankaccount;

import com.kata.bankaccount.domain.BankAccount;
import com.kata.bankaccount.domain.Operation;
import com.kata.bankaccount.services.OperationService;
import com.kata.bankaccount.services.OperationServiceImpl;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class BankAccountOperationsClient implements Runnable{

    private final static Logger LOGGER = Logger.getLogger(BankAccountOperationsClient.class);


    private BankAccount bankAccount = new BankAccount("Tata", new BigDecimal(50));
    private OperationService operationService = new OperationServiceImpl();

    public static void main(String[] args) {
        BankAccountOperationsClient bankAccountOperationsClient = new BankAccountOperationsClient();
        Thread t1 = new Thread(bankAccountOperationsClient);
        Thread t2 = new Thread(bankAccountOperationsClient);
        t1.setName("Tata");
        t2.setName("Toto");
        t1.start();
        t2.start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(),e);
            }
            Operation operation = new Operation(new BigDecimal(10), new Date());
            operationService.withdraw(bankAccount, operation);
            if(bankAccount.getBalance().add(operation.getOperationAmount().negate()).signum() == -1){
                LOGGER.info("stopping withdrawal, account under limit");
            }
        }
        LOGGER.info(operationService.getOperationsHistory(bankAccount));
    }
}
