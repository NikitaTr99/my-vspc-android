package com.ntsoftware.vspc.myvspc.screens.news.model;

import java.util.UUID;

public class BlockEntity {

    private UUID id;
    private UUID newsId;
    private long type;
    private int position;
    private String data;

    public BlockEntity(UUID id, UUID newsId, long type, int position, String data) {
        this.id = id;
        this.newsId = newsId;
        this.type = type;
        this.position = position;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public UUID getNewsId() {
        return newsId;
    }

    public void setNewsId(UUID newsId) {
        this.newsId = newsId;
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
}
