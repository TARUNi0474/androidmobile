package com.example.mynavactivity.retrofit.model;

public class ApiCart {
    private String productName;
    private String productImage;
    private double price;
    private int productQuantity;

    public ApiCart(String productName, String productImage, double price, int productQuantity) {
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
