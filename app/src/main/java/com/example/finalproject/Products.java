package com.example.finalproject;

import java.io.Serializable;

public class Products implements Serializable {
    private int id;
    private String category;
    private String trademark;
    private double sellingprice;
    private double purchaseprice;
    private String quantityperunit;
    private double availablequantity;

    public Products(int id, String category, String trademark, double sellingprice, double purchaseprice, String quantityperunit, double availablequantity) {

        this.id = id;
        this.category = category;
        this.trademark = trademark;
        this.sellingprice = sellingprice;
        this.purchaseprice = purchaseprice;
        this.quantityperunit = quantityperunit;
        this.availablequantity = availablequantity;
    }

    public Products(String category, String trademark, double sellingprice, double purchaseprice, String quantityperunit, double availablequantity) {
        this.category = category;
        this.trademark = trademark;
        this.sellingprice = sellingprice;
        this.purchaseprice = purchaseprice;
        this.quantityperunit = quantityperunit;
        this.availablequantity = availablequantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(double sellingprice) {
        this.sellingprice = sellingprice;
    }

    public double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit;
    }

    public double getAvailablequantity() {
        return availablequantity;
    }

    public void setAvailablequantity(double availablequantity) {
        this.availablequantity = availablequantity;
    }
}

