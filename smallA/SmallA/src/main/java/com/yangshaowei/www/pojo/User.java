package com.yangshaowei.www.pojo;

public class User {
    private int id;
    private String username;
    private String password;
    private int role;
    private int point;

    public User() {
    }

    public User(int id, String username, String password, int role, int point) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", point=" + point +
                '}';
    }
}
