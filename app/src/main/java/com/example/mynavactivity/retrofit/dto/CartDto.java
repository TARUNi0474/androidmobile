package com.example.mynavactivity.retrofit.dto;

public class CartDto {
    private long id;
    private Long quantity;
    private Double price;
    private String email;
    private Long productId;
    private Long merchantId;

    public CartDto() {
    }

    public CartDto( Long quantity, Double price, String email, Long productId) {
        this.quantity = quantity;
        this.price = price;
        this.email = email;
        this.productId = productId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
