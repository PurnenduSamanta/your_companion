package com.example.testing;

public class Model  {
    private String title;
    private String desc;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Model(int id,String title, String desc) {
        this.id=id;
        this.title = title;
        this.desc = desc;
    }
    public Model()
    {

    }



}
