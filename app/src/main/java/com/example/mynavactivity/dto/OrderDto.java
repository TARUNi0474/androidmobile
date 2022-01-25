package com.example.mynavactivity.dto;

import java.util.Date;

public class OrderDto {
    private int id;
    private int productId;
    private int quantity;
    private int orderId;
    private Date dateOfPurchase;
    private int amount;

    public OrderDto(int id, int productId, int quantity, int orderId, Date dateOfPurchase, int amount) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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
