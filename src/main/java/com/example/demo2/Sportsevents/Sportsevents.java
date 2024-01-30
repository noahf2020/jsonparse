package com.example.demo2.Sportsevents;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;

public class Sportsevents {
    @JsonProperty("id")
    String id;

    @JsonProperty("sport_key")
    String sport;

    @JsonProperty("sport_title")
    String sportTitle;

    @JsonProperty("commence_time")
    String startTime;

    @JsonProperty("home_team")
    String homeTeam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSportTitle() {
        return sportTitle;
    }

    public void setSportTitle(String sportTitle) {
        this.sportTitle = sportTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }


// Add getters and setters if necessary

}