package com.ntsoftware.vspc.myvspc.ui.home.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class SimpleNews {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("sub_title")
    @Expose
    private String sub_title;

    @SerializedName("date")
    @Expose
    private Timestamp date;

    @SerializedName("creator")
    @Expose
    private String creator;

    @SerializedName("type")
    @Expose
    private long type;

    @SerializedName("type_name")
    @Expose
    private String type_name;

    @SerializedName("image")
    @Expose
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "SimpleNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", date=" + date +
                ", creator='" + creator + '\'' +
                ", type=" + type +
                ", type_name='" + type_name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
