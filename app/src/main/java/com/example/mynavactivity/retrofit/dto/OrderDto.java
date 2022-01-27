package com.example.mynavactivity.retrofit.dto;

import java.util.Date;

public class OrderDto {
    private long id;
    private long productId;
    private int quantity;
    private long orderId;
    private Date dateOfPurchase;
    private int amount;

    public OrderDto(long id, long productId, int quantity, long orderId, Date dateOfPurchase, int amount) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
