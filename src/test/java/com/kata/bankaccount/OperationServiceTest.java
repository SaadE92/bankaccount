package com.kata.bankaccount;

import com.kata.bankaccount.domain.BankAccount;
import com.kata.bankaccount.domain.Operation;
import com.kata.bankaccount.domain.OperationType;
import com.kata.bankaccount.services.OperationService;
import com.kata.bankaccount.services.OperationServiceImpl;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * OperationServiceTest : using junit 5 dynamic testing
 */
public class OperationServiceTest {
    @Test
    void testMakeDepositThenWithdrawal() {
        BankAccount bankAccount = new BankAccount();
        Operation operation1 = new Operation(new BigDecimal(100),  new Date());
        OperationService operationService = new OperationServiceImpl();

        assertEquals(operationService.deposit(bankAccount, operation1), new BigDecimal(100), "After adding one operation, account amount is 100 box");

        Operation operation2 = new Operation(new BigDecimal(100),  new Date());
        assertEquals(operationService.deposit(bankAccount,operation2), new BigDecimal(200), "After adding another 100 box, amount is cumulated to 200");

        Operation operation3 = new Operation(new BigDecimal(100),  new Date());
        assertEquals(operationService.withdraw(bankAccount,operation3), new BigDecimal(100), "After taking 100 box, amount is reduced to 100");

    }

    @Test
    void testGetOperations() {
        Operation operation1 = new Operation(new BigDecimal(100), new Date());
        BankAccount bankAccount = new BankAccount();
        OperationService operationService = new OperationServiceImpl();
        operationService.deposit(bankAccount,operation1);
        assertNotNull(operationService.getOperationsHistory(bankAccount));
        assertTrue(!operationService.getOperationsHistory(bankAccount).isEmpty(),"After adding one operation, Operations history is not empty");
        assertEquals(bankAccount.getOperations().size(), 1, "After adding one operation, Operations is counting 1");
        assertEquals(bankAccount.getOperations().get(0).getOperationType(), OperationType.PUT, "Operation deposit we just added is of type put");
    }

    @TestFactory
    public Iterator<DynamicTest> depositDynamicTestsFromIterator() throws InterruptedException{
        BankAccount bankAccount = new BankAccount("Saad", new BigDecimal(400));
        OperationService operationService = new OperationServiceImpl();
        Operation operation1 = new Operation(null,new Date());
        Thread.sleep(1000);
        Operation operation2 = new Operation(new BigDecimal(100),new Date());
        Thread.sleep(2000);
        Operation operation3 = new Operation(new BigDecimal(100000),new Date());
        List<Operation> operations =
                new ArrayList<>(Arrays.asList(operation1, operation2, operation3));
        List<BigDecimal> accountBalances =
                new ArrayList<>(Arrays.asList(new BigDecimal(400), new BigDecimal(500), new BigDecimal(100500)));
        return operations.stream().map(op -> DynamicTest.dynamicTest("Test deposit " + op.getOperationAmount() + " where initial is 400 ", () -> {
            int idx = operations.indexOf(op);
            assertEquals(accountBalances.get(idx), operationService.deposit(bankAccount,op));
        })).iterator();
    }

    @TestFactory
    public Iterator<DynamicTest> withdrawDynamicTestsFromIterator() throws InterruptedException{
        BankAccount bankAccount = new BankAccount("Saad", new BigDecimal(150));
        OperationService operationService = new OperationServiceImpl();
        Operation operation1 = new Operation(null,new Date());
        Thread.sleep(1000);
        Operation operation2 = new Operation(new BigDecimal(100),new Date());
        Thread.sleep(2000);
        Operation operation3 = new Operation(new BigDecimal(100000),new Date());
        List<Operation> operations =
                new ArrayList<>(Arrays.asList(operation1, operation2, operation3));
        List<BigDecimal> accountBalances =
                new ArrayList<>(Arrays.asList(new BigDecimal(150), new BigDecimal(50), new BigDecimal(50)));
        return operations.stream().map(op -> DynamicTest.dynamicTest("Test withdraw by " + op.getOperationAmount() + "where initial is 150 ", () -> {
            int idx = operations.indexOf(op);
            assertEquals(accountBalances.get(idx), operationService.withdraw(bankAccount,op));
        })).iterator();
    }
}
