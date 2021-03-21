package com.example.testproblem.controller;

import com.example.testproblem.entity.Exchange;
import com.example.testproblem.entity.Expense;
import com.example.testproblem.entity.Total;
import com.example.testproblem.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping("/expenses")
    public Map<String, List<Expense>> all() {
        return expenseService.getAllExpensesList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/expenses")
    public void addExpense(@RequestBody Expense newExpense) {
        expenseService.addExpense(newExpense);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/expenses")
    public void deleteExpense(@RequestParam("date") String date) {
        expenseService.deleteExpense(date);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/total")
    @ResponseBody
    public Total getTotal(@RequestParam("base") String base) {
        return expenseService.getTotal(base);
    }
}
