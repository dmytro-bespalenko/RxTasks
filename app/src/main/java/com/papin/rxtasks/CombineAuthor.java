package com.papin.rxtasks;

public class CombineAuthor {

    private int karma;
    private String name;
    private String title;


    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CombineAuthor(int karma, String name, String title) {
        this.karma = karma;
        this.name = name;
        this.title = title;
    }
}
