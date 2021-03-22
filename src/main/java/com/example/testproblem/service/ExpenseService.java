package com.example.testproblem.service;

import com.example.testproblem.entity.Exchange;
import com.example.testproblem.entity.Expense;
import com.example.testproblem.entity.Total;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private List<Expense> expenseList = new ArrayList<>();
    private Map<String, List<Expense>> resultMap;
    private double sum;

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public Map<String, List<Expense>> getResultMap() {
        return resultMap;
    }

    public double getSum() {
        return sum;
    }

    public Map<String,List<Expense>> getAllExpensesList(){
        return resultMap;
    }

    public void addExpense(Expense expense){
        sum = sum + expense.getAmount();
        expenseList.add(expense);
        resultMap = expenseList.stream().collect(Collectors.groupingBy(Expense::getDate, TreeMap::new, Collectors.toList()));
    }

    public void deleteExpense(String date){
        expenseList.removeIf(expense -> expense.getDate().equals(date));
        resultMap.remove(date);
    }

    public Exchange convertFromTo(String from, String to, double amount){
        RestTemplate restTemplate = new RestTemplate();
        Exchange exchange = restTemplate.getForObject("https://api.exchangerate.host/convert?from=" + from +
                "&to=" + to + "&amount=" + amount, Exchange.class);
        return exchange;
    }

    public Total getTotal(String base){
        Iterator<Expense> it = expenseList.iterator();
        Exchange exchange = null;
        Total total = new Total();
        while(it.hasNext()) {
            Expense ex = it.next();
            exchange = convertFromTo(ex.getCurrency(), base, ex.getAmount());
            total.total = total.total + exchange.getResult();
        }
        return total;
    }
}

