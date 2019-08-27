package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    public StatusModel() {
    }

    public StatusModel(String id, String name) {
        Id = id;
        Name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusModel)) return false;

        StatusModel that = (StatusModel) o;

        return Name.equals(that.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }
}
