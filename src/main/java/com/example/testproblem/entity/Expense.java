package com.example.testproblem.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Expense implements Comparable<Expense> {

    private int id;
    private static int idInc = 0;
    private double amount;
    private String currency;
    private String product;
    private String date;

    public Expense() {
        this.id = idInc++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Expense o) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(this.date).compareTo(sdf.parse(o.getDate()));
        } catch (Exception e) {
            System.err.println(e.getMessage()); //Please handle it better!
        }
        return 0;
    }
}
