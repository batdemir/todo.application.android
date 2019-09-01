package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel extends BaseModel implements Serializable {

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("UserPassword")
    private String UserPassword;

    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("SurName")
    private String SurName;

    @SerializedName("Email")
    private String Email;

    public UserModel() {
    }

    public UserModel(String id, String userName, String userPassword, String firstName, String surName, String email, Boolean active, String createdDate) {
        setId(id);
        UserName = userName;
        UserPassword = userPassword;
        FirstName = firstName;
        SurName = surName;
        Email = email;
        setActive(active);
        setCreatedDate(createdDate);
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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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
