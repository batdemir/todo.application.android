package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TodoListModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("UserId")
    private String UserId;

    @SerializedName("TaskId")
    private String TaskId;

    @SerializedName("StatusId")
    private String StatusId;

    public TodoListModel() {
    }

    public TodoListModel(String id, String name, String userId, String taskId, String statusId) {
        Id = id;
        Name = name;
        UserId = userId;
        TaskId = taskId;
        StatusId = statusId;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String statusId) {
        StatusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoListModel)) return false;

        TodoListModel that = (TodoListModel) o;

        if (!Name.equals(that.Name)) return false;
        if (!UserId.equals(that.UserId)) return false;
        if (!TaskId.equals(that.TaskId)) return false;
        return StatusId.equals(that.StatusId);
    }

    @Override
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + UserId.hashCode();
        result = 31 * result + TaskId.hashCode();
        result = 31 * result + StatusId.hashCode();
        return result;
    }
}
