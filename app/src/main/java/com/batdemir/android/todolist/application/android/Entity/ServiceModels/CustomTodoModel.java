package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomTodoModel implements Serializable {

    @SerializedName("TodoId")
    private String TodoId;

    @SerializedName("TodoName")
    private String TodoName;

    @SerializedName("TodoCreatedDate")
    private String TodoCreatedDate;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("CustomTodoTasksModels")
    private ArrayList<CustomTodoTasksModel> CustomTodoTasksModels;

    public CustomTodoModel() {
    }

    public CustomTodoModel(String todoId, String todoName, String todoCreatedDate, String userName, ArrayList<CustomTodoTasksModel> customTodoTasksModels) {
        TodoId = todoId;
        TodoName = todoName;
        TodoCreatedDate = todoCreatedDate;
        UserName = userName;
        CustomTodoTasksModels = customTodoTasksModels;
    }

    public String getTodoId() {
        return TodoId;
    }

    public void setTodoId(String todoId) {
        TodoId = todoId;
    }

    public String getTodoName() {
        return TodoName;
    }

    public void setTodoName(String todoName) {
        TodoName = todoName;
    }

    public String getTodoCreatedDate() {
        return TodoCreatedDate;
    }

    public void setTodoCreatedDate(String todoCreatedDate) {
        TodoCreatedDate = todoCreatedDate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public ArrayList<CustomTodoTasksModel> getCustomTodoTasksModels() {
        return CustomTodoTasksModels;
    }

    public void setCustomTodoTasksModels(ArrayList<CustomTodoTasksModel> customTodoTasksModels) {
        CustomTodoTasksModels = customTodoTasksModels;
    }
}
