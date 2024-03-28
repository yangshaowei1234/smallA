package com.yangshaowei.www.pojo;

public class Cart {
    //购物车id
   private int id;
   //购物车所属用户id
   private int userId;
   //购物车的商品id
   private int productId;
   //该商品的购买数量
   private int quantity;
   //该商品添加的时间
   private String addedDate;
   //以下为基本javabean类方法
    public Cart() {
    }

    public Cart(int id, int userId, int productId, int quantity, String addedDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", addedDate='" + addedDate + '\'' +
                '}';
    }
}
