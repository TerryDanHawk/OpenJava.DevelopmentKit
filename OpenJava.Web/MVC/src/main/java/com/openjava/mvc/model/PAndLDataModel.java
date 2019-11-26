package com.openjava.mvc.model;



public class PAndLDataModel  {
    private long Id;
    private String Year;
    private String Month;
    private String AccountDescription;
    private double MonthActual;
    private double YTDActual;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public String getAccountDescription() {
        return AccountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        AccountDescription = accountDescription;
    }

    public double getMonthActual() {
        return MonthActual;
    }

    public void setMonthActual(double monthActual) {
        MonthActual = monthActual;
    }

    public double getYTDActual() {
        return YTDActual;
    }

    public void setYTDActual(double YTDActual) {
        this.YTDActual = YTDActual;
    }
}
