package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

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

    @SerializedName("CreatedDate")
    private String CreatedDate;

    public TaskModel() {
    }

    public TaskModel(String id, String name, String description, String deadLine, String createdDate) {
        Id = id;
        Name = name;
        Description = description;
        DeadLine = deadLine;
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
