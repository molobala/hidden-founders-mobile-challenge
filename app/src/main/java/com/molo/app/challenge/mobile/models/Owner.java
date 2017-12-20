package com.molo.app.challenge.mobile.models;

public class Owner {
    private String login;
    private String avatarUrl;
    private int id;

    public Owner() {
    }

    public Owner(String login, String avatarUrl, int id) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
