package com.ntsoftware.vspc.myvspc.ui.home.news.model;

public class NewsBlock {
    private long id;
    private long news;
    private long type;
    private int position;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNews() {
        return news;
    }

    public void setNews(long news) {
        this.news = news;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsBlock{" +
                "id=" + id +
                ", news=" + news +
                ", type=" + type +
                ", position=" + position +
                ", data='" + data + '\'' +
                '}';
    }
}
