package com.ldh.sdcard_list;

public class Folder {
    private int avatar;
    private String name;
    private String path;
    public Folder(int avatar, String name,String path) {
        this.avatar = avatar;
        this.name = name;
        this.path = path;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
