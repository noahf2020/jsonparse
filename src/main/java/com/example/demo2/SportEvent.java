package com.example.demo2;

public class SportEvent {
   private String Game;
    private String Time;
    private String FavoritedTeam;
    private String AvgOdds;
    private String HomeTeam;
    private String Site;

    public SportEvent(String Game, String Time, String Site, String FavoritedTeam, String AvgOdds, String HomeTeam) {
        this.Game = Game;
        this.Time = Time;
        this.Site = Site;
        this.FavoritedTeam = FavoritedTeam;
        this.AvgOdds =  AvgOdds;
        this.HomeTeam = HomeTeam;

    }
    public String getGame() {
        return Game;
    }

    public void setGame(String game) {
        Game = game;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getFavoritedTeam() {
        return FavoritedTeam;
    }

    public void setFavoritedTeam(String favoritedTeam) {
        FavoritedTeam = favoritedTeam;
    }

    public String getAvgOdds() {
        return AvgOdds;
    }

    public void setAvgOdds(String avgOdds) {
        AvgOdds = avgOdds;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        HomeTeam = homeTeam;
    }


}