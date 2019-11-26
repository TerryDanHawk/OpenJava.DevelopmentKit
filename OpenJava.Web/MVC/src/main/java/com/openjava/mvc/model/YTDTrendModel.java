package com.openjava.mvc.model;



public class YTDTrendModel {
    private int Id;
    private String Year;
    private String Month;
    private String ItemName;
    private double YTDMoney;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getYTDMoney() {
        return YTDMoney;
    }

    public void setYTDMoney(double YTDMoney) {
        this.YTDMoney = YTDMoney;
    }
}
