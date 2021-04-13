package com.covid.Nepal.models;

public class LocationStats {
    private String country;
    private String State;
    private int totalConfirmedCases;
    private int diffWithPrevDay;


    public int getDiffWithPrevDay() {
        return diffWithPrevDay;
    }

    public void setDiffWithPrevDay(int diffWithPrevDay) {
        this.diffWithPrevDay = diffWithPrevDay;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalConfirmedCases() {
        return totalConfirmedCases;
    }

    public void setTotalConfirmedCases(int totalConfirmedCases) {
        this.totalConfirmedCases = totalConfirmedCases;
    }

    @Override
    public String toString() {
        return " country=' " + country + '\'' +
                ", State=' " + State + '\'' +
                ", totalConfirmedCases=" + totalConfirmedCases;
    }
}
