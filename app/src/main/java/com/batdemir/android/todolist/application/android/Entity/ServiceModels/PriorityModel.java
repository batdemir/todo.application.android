package com.batdemir.android.todolist.application.android.Entity.ServiceModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PriorityModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("Active")
    private Boolean Active;

    @SerializedName("OrderNo")
    private Integer OrderNo;

    public PriorityModel() {
    }

    public PriorityModel(String id, String name, Boolean active, Integer orderNo) {
        Id = id;
        Name = name;
        Active = active;
        OrderNo = orderNo;
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

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public Integer getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(Integer orderNo) {
        OrderNo = orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriorityModel)) return false;

        PriorityModel that = (PriorityModel) o;

        return Name.equals(that.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }
}
