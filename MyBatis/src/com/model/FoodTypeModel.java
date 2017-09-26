package com.model;

public class FoodTypeModel {
    
    private int id;
    private String foodType;
    private double SD;
    private double number;

    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getSD() {
        return SD;
    }
    public void setSD(double SD) {
        this.SD = SD;
    }
    public double getNumber() {
        return number;
    }
    public void setNumber(double number) {
        this.number = number;
    }
}
