package com.example.mynavactivity.retrofit.dto;

public class ProductDto {
    private long productId;
    private long categoryId;
    private long merchantId;
    private String productName;
    private double price;
    private String productImage;
    private String productDescription;

    public ProductDto(long productId, long categoryId, long merchantId, String productName, double price, String productImage, String productDescription) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.merchantId = merchantId;
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.productDescription = productDescription;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
