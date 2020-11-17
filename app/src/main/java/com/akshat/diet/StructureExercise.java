package com.akshat.diet;

public class StructureExercise {
    String name;
    String description;
    String category;
    String image;
    String time;

    public StructureExercise(String name, String description, String category, String image, String time) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.image = image;
        this.time = time;
    }

    public StructureExercise() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
