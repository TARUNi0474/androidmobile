package com.example.mynavactivity.retrofit.model;


public class ApiOrder{
    private long orderId;
    private String dateOfPurchase;
    private long amount;

    public ApiOrder(long orderId, String dateOfPurchase, long amount) {
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
