package com.example.testproblem.service;

import com.example.testproblem.entity.Exchange;
import com.example.testproblem.entity.Expense;
import com.example.testproblem.entity.Total;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {

    ExpenseService es = new ExpenseService();

    @Test
    void getAllExpensesList() {
        Expense ex = new Expense(1, "USD", "Sweets", "2021-03-21");
        es.addExpense(ex);
        Map<String, List<Expense>> map = es.getAllExpensesList();
        Assert.assertFalse(map.isEmpty());
    }

    @Test
    void addExpense() {
        Expense ex = new Expense(1, "USD", "Sweets", "2021-03-21");
        es.addExpense(ex);
        Assert.assertNotNull(es.getResultMap());
    }

    @Test
    void deleteExpense() {
        Expense ex = new Expense(1, "USD", "Sweets", "2021-03-21");
        es.addExpense(ex);
        es.deleteExpense("2021-03-21");
        Assert.assertFalse(es.getResultMap().containsKey("2021-03-21"));
    }

    @Test
    void convertFromTo() {
        Expense ex = new Expense(1, "USD", "Sweets", "2021-03-21");
        es.addExpense(ex);
        Exchange exchange = es.convertFromTo("USD", "UAH", 1);
        Assert.assertTrue(exchange.isSuccess());
    }

    @Test
    void getTotal() {
        Expense ex = new Expense(1, "USD", "Sweets", "2021-03-21");
        es.addExpense(ex);
        Total total = new Total();
        Total total1 = es.getTotal("UAH");
        Assert.assertNotEquals(total, total1);
    }
}