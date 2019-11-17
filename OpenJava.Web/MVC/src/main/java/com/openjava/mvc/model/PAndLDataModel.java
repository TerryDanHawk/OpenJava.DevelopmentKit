package com.openjava.mvc.model;

import java.math.BigDecimal;

public class PAndLDataModel  {
    private long Id;
    private String Year;
    private String Month;
    private String AccountDescription;
    private BigDecimal MonthActual;
    private BigDecimal YTDActual;

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

    public BigDecimal getMonthActual() {
        return MonthActual;
    }

    public void setMonthActual(BigDecimal monthActual) {
        MonthActual = monthActual;
    }

    public BigDecimal getYTDActual() {
        return YTDActual;
    }

    public void setYTDActual(BigDecimal YTDActual) {
        this.YTDActual = YTDActual;
    }
}
