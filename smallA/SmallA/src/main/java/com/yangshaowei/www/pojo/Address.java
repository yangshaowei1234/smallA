package com.yangshaowei.www.pojo;

public class Address {
    private int id;
    private int userId;
    private String address;
    private int isDefault;

    public Address() {
    }

    public Address(int id, int userId, String address, int isDefault) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.isDefault = isDefault;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}