package com.model;

public class FoodContent {
    
    private int id;
    private String radio;
    private String foodName;
    private String foodType;
    private double content;

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
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
     public double getContent() {
        return content;
    }
    public void setContent(double content) {
        this.content = content;
    }
 
}