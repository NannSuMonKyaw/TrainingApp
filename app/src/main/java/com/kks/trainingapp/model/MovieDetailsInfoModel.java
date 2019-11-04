package com.kks.trainingapp.model;

public class MovieDetailsInfoModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
    int id;
    String poster_path;
    String original_path;
    String original_title;
    String release_date;
    int revenue;
    String tagline;
    String overview;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_path() {
        return original_path;
    }

    public void setOriginal_path(String original_path) {
        this.original_path = original_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }





}
