package com.hbdf.bean;

/**
 * 商品实体类（JavaBean）
 * 对应数据库中的product表
 */
public class Product {
    private int id;          // 商品编号
    private String name;     // 商品名称
    private double price;    // 商品价格
    private int stock;       // 商品库存
    private String category; // 商品分类

    // 无参构造方法
    public Product() {}

    // 全参构造方法
    public Product(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getter和Setter方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", stock=" + stock + "}";
    }
}