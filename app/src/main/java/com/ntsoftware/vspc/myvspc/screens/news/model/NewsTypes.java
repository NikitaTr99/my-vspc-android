package com.ntsoftware.vspc.myvspc.screens.news.model;

public enum NewsTypes {
    NULL(1),
    NEWS(2),
    NOTIFICATION(2),
    WARRNING(4);

    private int id;

    NewsTypes(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
