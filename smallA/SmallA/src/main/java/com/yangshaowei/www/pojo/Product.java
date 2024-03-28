package com.yangshaowei.www.pojo;

public class Product {
    private int id;
    private String name;
    private int category;
    private double price;
    private int stock;
    private String time;
    private int merchantId;

    private int sales;

    private boolean isLaunched;
    public Product() {
    }

    public Product(String name,int sales) {
        this.sales = sales;
        this.name = name;
    }

    public Product(int id, String name, int category, double price, int stock, String time, int merchantId, int sales) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.time = time;
        this.merchantId = merchantId;
        this.sales = sales;
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public void setLaunched(boolean launched) {
        isLaunched = launched;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", stock=" + stock +
                ", time='" + time + '\'' +
                ", merchantId=" + merchantId +
                ", sales=" + sales +
                '}';
    }
}
