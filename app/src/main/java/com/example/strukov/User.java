package com.example.strukov;

import android.graphics.Bitmap;

public class User {
    private String login;
    private String avatar_url;
    private String type;
    private Bitmap avatar;

    public User(String login, String avatar_url, String type) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.type = type;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
