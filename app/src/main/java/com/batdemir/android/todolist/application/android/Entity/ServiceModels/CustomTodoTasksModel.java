package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomTodoTasksModel extends TodoListModel implements Serializable {

    @SerializedName("PriorityName")
    private String PriorityName;

    @SerializedName("DeadLine")
    private String DeadLine;

    public CustomTodoTasksModel() {
    }

    public String getPriorityName() {
        return PriorityName;
    }

    public void setPriorityName(String priorityName) {
        PriorityName = priorityName;
    }

    public String getDeadLine() {
        return DeadLine;
    }

    public void setDeadLine(String deadLine) {
        DeadLine = deadLine;
    }
}
