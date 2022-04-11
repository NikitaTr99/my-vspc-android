package com.ntsoftware.vspc.myvspc.screens.news.model;

import java.sql.Timestamp;
import java.util.UUID;

public class AssembledNewsPreview {
    private UUID newsId;
    private String title;
    private String subTitle;
    private Timestamp createdAt;
    private String creator;
    private long type;
    private String typeName;
    private UUID imageId;

    public AssembledNewsPreview(UUID newsId, String title, String subTitle, Timestamp createdAt, String creator, long type, String typeName, UUID imageId) {
        this.newsId = newsId;
        this.title = title;
        this.subTitle = subTitle;
        this.createdAt = createdAt;
        this.creator = creator;
        this.type = type;
        this.typeName = typeName;
        this.imageId = imageId;
    }

    public UUID getNewsId() {
        return newsId;
    }

    private void setNewsId(UUID newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "AssembledNewsPreview{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", createdAt=" + createdAt +
                ", creator='" + creator + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
