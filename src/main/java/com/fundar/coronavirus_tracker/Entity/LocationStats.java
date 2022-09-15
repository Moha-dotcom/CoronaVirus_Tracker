package com.fundar.coronavirus_tracker.Entity;


import java.time.LocalDate;

public class LocationStats {


    private LocalDate date;
    private String county;
    private String state;

    private String fips;
    private String cases;
    private String deaths;

    private long totalCases;

    private  long totalDeaths;

    public LocalDate getDate () {
        return date;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }

    public String getCounty () {
        return county;
    }

    public void setCounty (String county) {
        this.county = county;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public String getFips () {
        return fips;
    }

    public void setFips (String fips) {
        this.fips = fips;
    }

    public String getCases () {
        return cases;
    }

    public void setCases (String cases) {
        this.cases = cases;
    }

    public String getDeaths () {
        return deaths;
    }

    public void setDeaths (String deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString () {
        return "LocationStats{" +
                "date=" + date +
                ", county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", fips='" + fips + '\'' +
                ", cases='" + cases + '\'' +
                ", deaths='" + deaths + '\'' +
                '}';
    }

    public long getTotalCases () {
        return totalCases;
    }

    public void setTotalCases (long totalCases) {
        this.totalCases = totalCases;
    }

    public long getTotalDeaths () {
        return totalDeaths;
    }

    public void setTotalDeaths (long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }
}