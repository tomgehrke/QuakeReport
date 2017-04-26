package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl; 

    // Constructors

    public Earthquake(double magnitude, String location, long date, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDate = date;
        this.mUrl = url;
    }


    // Getters & Setters

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(double magnitude) {
        this.mMagnitude = magnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String Url) {
        this.mUrl = Url;
    }

    // Overrides

    @Override
    public String toString() {
        return "Earthquake{" +
                "mMagnitude=" + mMagnitude +
                ", mLocation='" + mLocation + "'" +
                ", mDate=" + mDate +
                ", mUrl'" + mUrl + "'" +
                '}';
    }

}
