package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("UserPassword")
    private String UserPassword;

    @SerializedName("Name")
    private String Name;

    @SerializedName("SurName")
    private String SurName;

    @SerializedName("Email")
    private String Email;

    @SerializedName("CreatedDate")
    private String CreatedDate;

    public UserModel() {
    }

    public UserModel(String id, String userName, String userPassword, String name, String surName, String email, String createdDate) {
        Id = id;
        UserName = userName;
        UserPassword = userPassword;
        Name = name;
        SurName = surName;
        Email = email;
        CreatedDate = createdDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;

        UserModel userModel = (UserModel) o;

        if (!UserName.equals(userModel.UserName)) return false;
        return Email.equals(userModel.Email);
    }

    @Override
    public int hashCode() {
        int result = UserName.hashCode();
        result = 31 * result + Email.hashCode();
        return result;
    }
}