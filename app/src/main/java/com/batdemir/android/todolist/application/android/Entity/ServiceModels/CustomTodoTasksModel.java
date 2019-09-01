package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

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

    public static Comparator<CustomTodoTasksModel> ORDERBY_TODONAME(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getTodoName().compareTo(o2.getTodoName());
            }else {
                return o2.getTodoName().compareTo(o1.getTodoName());
            }
        };
    }

    public static Comparator<CustomTodoTasksModel> ORDERBY_CREATEDDATE(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
            }else {
                return o2.getCreatedDate().compareTo(o1.getCreatedDate());
            }
        };
    }

    public static Comparator<CustomTodoTasksModel> ORDERBY_TASKNAME(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getTaskName().compareTo(o2.getTaskName());
            }else {
                return o2.getTaskName().compareTo(o1.getTaskName());
            }
        };
    }

    public static Comparator<CustomTodoTasksModel> ORDERBY_PRIORITY(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getPriorityName().compareTo(o2.getPriorityName());
            }else {
                return o2.getPriorityName().compareTo(o1.getPriorityName());
            }
        };
    }

    public static Comparator<CustomTodoTasksModel> ORDERBY_DEADLINE(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getDeadLine().compareTo(o2.getDeadLine());
            }else {
                return o2.getDeadLine().compareTo(o1.getDeadLine());
            }
        };
    }

    public static Comparator<CustomTodoTasksModel> ORDERBY_STATUS(boolean isAsc){
        return (o1, o2) -> {
            if(isAsc){
                return o1.getStatusName().compareTo(o2.getStatusName());
            }else {
                return o2.getStatusName().compareTo(o1.getStatusName());
            }
        };
    }
}
