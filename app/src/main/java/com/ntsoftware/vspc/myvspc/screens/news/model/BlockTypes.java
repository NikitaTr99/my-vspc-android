package com.ntsoftware.vspc.myvspc.screens.news.model;

public enum BlockTypes {
    TEXT(1),
    IMAGE(2);

    private int id;

    BlockTypes(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
