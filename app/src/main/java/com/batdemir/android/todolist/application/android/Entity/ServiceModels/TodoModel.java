package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TodoModel extends BaseModel implements Serializable {

    @SerializedName("Name")
    private String Name;

    @SerializedName("UserName")
    private String UserName;

    public TodoModel() {
    }

    public TodoModel(String id, String name, String userName, Boolean active, String createdDate) {
        setId(id);
        Name = name;
        UserName = userName;
        setActive(active);
        setCreatedDate(createdDate);
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
