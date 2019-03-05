package com.example.hurski_r.worker;

import java.util.Date;

public class Order {
    private String number;
    private String date;
    private String admin;
    private String address;
    private String status;

    public Order(String number, String date, String admin, String address, String status, Double cost) {
        this.number = number;
        this.date = date;
        this.admin = admin;
        this.address = address;
        this.status = status;
    }

    public Order() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
