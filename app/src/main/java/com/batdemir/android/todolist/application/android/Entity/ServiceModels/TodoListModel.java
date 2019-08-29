package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TodoListModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("TodoName")
    private String TodoName;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("TaskName")
    private String TaskName;

    @SerializedName("StatusName")
    private String StatusName;

    @SerializedName("Active")
    private Boolean Active;

    @SerializedName("CreatedDate")
    private String CreatedDate;

    public TodoListModel() {
    }

    public TodoListModel(String id, String todoName, String userName, String taskName, String statusName, Boolean active, String createdDate) {
        Id = id;
        TodoName = todoName;
        UserName = userName;
        TaskName = taskName;
        StatusName = statusName;
        Active = active;
        CreatedDate = createdDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTodoName() {
        return TodoName;
    }

    public void setTodoName(String todoName) {
        TodoName = todoName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
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
        if (!(o instanceof TodoListModel)) return false;

        TodoListModel that = (TodoListModel) o;

        if (!TodoName.equals(that.TodoName)) return false;
        if (!UserName.equals(that.UserName)) return false;
        return TaskName.equals(that.TaskName);
    }

    @Override
    public int hashCode() {
        int result = TodoName.hashCode();
        result = 31 * result + UserName.hashCode();
        result = 31 * result + TaskName.hashCode();
        return result;
    }
}
