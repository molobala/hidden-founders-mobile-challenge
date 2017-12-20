package com.molo.app.challenge.mobile.models;

public class Repository {
    private int id;
    private String name;
    private String description;
    private int stargazersCount;
    private Owner owner;
    public Repository(){

    }
    public Repository(int id, String name, String description, int stargazersCount, Owner owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
