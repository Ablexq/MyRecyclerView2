package com.xq.stickyheadersrecyclerview.bean;

public class Team {
    private String name;
    private String ImagePath;

    public Team(String name, String imagePath) {
        this.name = name;
        ImagePath = imagePath;
    }

    public Team(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}