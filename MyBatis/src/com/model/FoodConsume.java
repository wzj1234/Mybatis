package com.model;

public class FoodConsume {
    
    private int id;
    private String age;
    private String foodName;
    private String foodType;
    private double foodConsume;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
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
    public double getFoodConsume() {
        return foodConsume;
    }
    public void setFoodConsume(double foodConsume) {
        this.foodConsume = foodConsume;
    }
}