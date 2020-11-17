package com.akshat.diet;

public class StructureDiet {

    String dietName;
    String dietContents;
    int calorie;
    String suitableFor;
    String category;

    public StructureDiet(String dietName, String category, String dietContents, String suitableFor, int calorie) {
        this.dietName = dietName;
        this.dietContents = dietContents;
        this.calorie = calorie;
        this.suitableFor = suitableFor;
        this.category = category;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietContents() {
        return dietContents;
    }

    public void setDietContents(String dietContents) {
        this.dietContents = dietContents;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
