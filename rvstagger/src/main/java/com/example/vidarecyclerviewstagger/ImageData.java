package com.example.vidarecyclerviewstagger;

public class ImageData {

    private int resourceId;
    private int height;
    private int width;

    public ImageData(int resourceId,int height,int width){
        this.resourceId = resourceId;
        this.height = height;
        this.width = width;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
