package com.openjava.mvc.model;

import java.math.BigDecimal;

public class ExchangeRateModel {
    private long Id;
    private String Year;
    private String Month;
    private String FmCurr;
    private String ToCurr;
    private String Category;
    private BigDecimal Rate;

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

    public String getFmCurr() {
        return FmCurr;
    }

    public void setFmCurr(String fmCurr) {
        FmCurr = fmCurr;
    }

    public String getToCurr() {
        return ToCurr;
    }

    public void setToCurr(String toCurr) {
        ToCurr = toCurr;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public BigDecimal getRate() {
        return Rate;
    }

    public void setRate(BigDecimal rate) {
        Rate = rate;
    }
}
