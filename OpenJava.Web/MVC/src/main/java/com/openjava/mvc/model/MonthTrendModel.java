package com.openjava.mvc.model;



public class MonthTrendModel {
    private int Id;
    private String Year;
    private String Month;
    private String ItemName;
    private double MonthMoney;

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

    public double getMonthMoney() {
        return MonthMoney;
    }

    public void setMonthMoney(double monthMoney) {
        MonthMoney = monthMoney;
    }
}
