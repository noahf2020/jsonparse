package com.example.demo2.Sportsevents;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Sportsevents {
    @JsonProperty("key")
    String key;

    @JsonProperty("group")
    String group;

    @JsonProperty("title")
    String title;

    @JsonProperty("description")
    String description;

    @JsonProperty("active")
    boolean active;

    @JsonProperty("has_outrights")
    boolean hasOutrights;

    // Add getters and setters if necessary

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getGroup() {
        return key;
    }

    public void getGroup(String key) {
        this.group = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isHasOutrights() {
        return hasOutrights;
    }

    public void setHasOutrights(boolean hasOutrights) {
        this.hasOutrights = hasOutrights;
    }
}
