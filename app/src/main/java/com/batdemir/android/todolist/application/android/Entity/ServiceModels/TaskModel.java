package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("Description")
    private String Description;

    @SerializedName("DeadLine")
    private String DeadLine;

    @SerializedName("PriorityName")
    private String PriorityName;

    @SerializedName("UserName")
    private String UserName;

    @SerializedName("Active")
    private Boolean Active;

    @SerializedName("CreatedDate")
    private String CreatedDate;

    public TaskModel() {
    }

    public TaskModel(@NonNull String id, String name, String description, String deadLine, String priorityName, String userName, Boolean active, String createdDate) {
        Id = id;
        Name = name;
        Description = description;
        DeadLine = deadLine;
        PriorityName = priorityName;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDeadLine() {
        return DeadLine;
    }

    public void setDeadLine(String deadLine) {
        DeadLine = deadLine;
    }

    public String getPriorityName() {
        return PriorityName;
    }

    public void setPriorityName(String priorityName) {
        PriorityName = priorityName;
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
        if (!(o instanceof TaskModel)) return false;

        TaskModel taskModel = (TaskModel) o;

        return Name.equals(taskModel.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }
}
