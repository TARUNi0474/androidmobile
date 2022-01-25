package com.example.mynavactivity.homecategory.model;


public class ApiOrder{
    private int orderId;
    private String dateOfPurchase;
    private int amount;

    public ApiOrder(int orderId, String dateOfPurchase, int amount) {
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
