package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskModel extends BaseModel implements Serializable {

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

    public TaskModel() {
    }

    public TaskModel(@NonNull String id, String name, String description, String deadLine, String priorityName, String userName, Boolean active, String createdDate) {
        setId(id);
        Name = name;
        Description = description;
        DeadLine = deadLine;
        PriorityName = priorityName;
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
