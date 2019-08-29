package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TodoModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("CreatedDate")
    private Boolean Active;

    @SerializedName("CreatedDate")
    private String CreatedDate;

    public TodoModel() {
    }

    public TodoModel(String id, String name, String userName, Boolean active, String createdDate) {
        Id = id;
        Name = name;
        UserName = userName;
        Active = active;
        CreatedDate = createdDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
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
        if (!(o instanceof TodoModel)) return false;

        TodoModel todoModel = (TodoModel) o;

        if (!Name.equals(todoModel.Name)) return false;
        return UserName.equals(todoModel.UserName);
    }

    @Override
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + UserName.hashCode();
        return result;
    }
}
