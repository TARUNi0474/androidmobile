package com.example.mynavactivity.dto;

public class ProductDto {
    private int productId;
    private int categoryId;
    private int merchantId;
    private String productName;
    private int price;
    private int productImage;

    private String productTitle;
    private String productPrice;
    private String productDescription;

    public ProductDto(int productId, int categoryId, int merchantId, String productName, int price, int productImage) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.merchantId = merchantId;
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
