package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("listId")
    private int listId;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    // getters and setters
    public int getListId(){
        return listId;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setListId(int listId){
        this.listId = listId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
}
