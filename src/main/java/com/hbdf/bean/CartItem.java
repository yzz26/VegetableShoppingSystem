package com.hbdf.bean;

/**
 * 购物车条目类（JavaBean）
 * 用于封装购物车中每条商品记录
 */
public class CartItem {
    private int productId;    // 商品编号
    private String productName; // 商品名称
    private double price;     // 商品单价
    private int quantity;     // 购买数量

    public CartItem() {}

    public CartItem(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // 计算小计金额
    public double getSubtotal() {
        return this.price * this.quantity;
    }
}