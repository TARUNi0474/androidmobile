package com.example.mynavactivity.retrofit.model;

public class ApiProduct {
    private String productImage;
    private String productName;
    private double price;
    private String productDescription;

//    public ApiProduct(String productImage, String productName, double productPrice, String productDescription) {
//        this.productImage = productImage;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productDescription = productDescription;
//    }

    public ApiProduct(String productImage, String productName, double price) {
        this.productImage = productImage;
        this.productName = productName;
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
