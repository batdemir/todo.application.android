package com.batdemir.android.todolist.application.android.Entity.UIModels;

import android.graphics.drawable.Drawable;
import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventsItemModel implements Serializable {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("to")
    private Class<?> to;

    @SerializedName("Icon")
    private Drawable Icon;

    public EventsItemModel() {
    }

    public EventsItemModel(String id, String name, Class<?> to, Drawable icon) {
        Id = id;
        Name = name;
        this.to = to;
        Icon = icon;
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

    public Class<?> getTo() {
        return to;
    }

    public void setTo(Class<?> to) {
        this.to = to;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public void setIcon(Drawable icon) {
        Icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventsItemModel)) return false;

        EventsItemModel that = (EventsItemModel) o;

        return Name.equals(that.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }
}
