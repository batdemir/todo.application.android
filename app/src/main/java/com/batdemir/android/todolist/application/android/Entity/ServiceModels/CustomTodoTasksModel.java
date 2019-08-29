package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomTodoTasksModel implements Serializable {

    @SerializedName("TodoListId")
    private String TodoListId;

    @SerializedName("TaskName")
    private String TaskName;

    @SerializedName("PriorityName")
    private String PriorityName;

    @SerializedName("StatusName")
    private String StatusName;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("DeadLine")
    private String DeadLine;

    public CustomTodoTasksModel() {
    }

    public CustomTodoTasksModel(String todoListId, String taskName, String priorityName, String statusName, String userName, String deadLine) {
        TodoListId = todoListId;
        TaskName = taskName;
        PriorityName = priorityName;
        StatusName = statusName;
        UserName = userName;
        DeadLine = deadLine;
    }

    public String getTodoListId() {
        return TodoListId;
    }

    public void setTodoListId(String todoListId) {
        TodoListId = todoListId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getPriorityName() {
        return PriorityName;
    }

    public void setPriorityName(String priorityName) {
        PriorityName = priorityName;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDeadLine() {
        return DeadLine;
    }

    public void setDeadLine(String deadLine) {
        DeadLine = deadLine;
    }
}
