package com.akshat.diet;

public class Users {

    private int userId;
    private String username;
    private String password;
    private String userWeightInKG;
    private String userHeightInCM;
    private String fullName;
    private int age;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserWeightInKG() {
        return userWeightInKG;
    }

    public void setUserWeightInKG(String userWeightInKG) {
        this.userWeightInKG = userWeightInKG;
    }

    public String getUserHeightInCM() {
        return userHeightInCM;
    }

    public void setUserHeightInCM(String userHeightInCM) {
        this.userHeightInCM = userHeightInCM;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Users(int userId, String username, String password, String userWeightInKG, String userHeightInCM, String fullName, int age, String gender) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userWeightInKG = userWeightInKG;
        this.userHeightInCM = userHeightInCM;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public Users(String username, String password, String userWeightInKG, String userHeightInCM, String fullName, int age) {
        this.username = username;
        this.password = password;
        this.userWeightInKG = userWeightInKG;
        this.userHeightInCM = userHeightInCM;
        this.fullName = fullName;
        this.age = age;
    }

    public Users(){}
}
