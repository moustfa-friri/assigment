package com.nagarro.assignment.models;

import java.util.Date;

public class Statement {

    private int id;
    private int accountId;
    private Date dateField;
    private double amount;

    public Statement(int id, int accountId, Date dateField, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.dateField = dateField;
        this.amount = amount;
    }

    public Statement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
