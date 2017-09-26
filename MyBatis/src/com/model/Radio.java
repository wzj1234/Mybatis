package com.model;

public class Radio {
    
    private int id;
    private String radio;
    private double activity;
    private String unit;
    private double ifExceedSum;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRadio() {
        return radio;
    }
    public void setRadio(String radio) {
        this.radio = radio;
    }
    public double getActivity() {
        return activity;
    }
    public void setActivity(double activity) {
        this.activity = activity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public double getIfExceedSum() {
        return ifExceedSum;
    }
    public void setIfExceedSum(double ifExceedSum) {
        this.ifExceedSum = ifExceedSum;
    }
}