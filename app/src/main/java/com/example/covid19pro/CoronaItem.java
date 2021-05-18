package com.example.covid19pro;

public class CoronaItem {
    private String state ;
    private String deaths ;
    private String active ;
    private String recovered;
    private String confirmed;
    private String lastUpdated;
    private String TodayRecovered;
    private String  TodayActive;
    private String  TodayDeaths;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTodayRecovered() {
        return TodayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        TodayRecovered = todayRecovered;
    }

    public String getTodayActive() {
        return TodayActive;
    }

    public void setTodayActive(String todayActive) {
        TodayActive = todayActive;
    }

    public String getTodayDeaths() {
        return TodayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        TodayDeaths = todayDeaths;
    }

    public  CoronaItem(String state, String deaths, String active, String recovered, String confirmed, String lastUpdated, String todayRecovered, String todayActive, String todayDeaths) {
        this.state = state;
        this.deaths = deaths;
        this.active = active;
        this.recovered = recovered;
        this.confirmed = confirmed;
        this.lastUpdated = lastUpdated;
        TodayRecovered = todayRecovered;
        TodayActive = todayActive;
        TodayDeaths = todayDeaths;
    }
}
