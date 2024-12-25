package com.example.movieapp;

public class Movie {
    private String title;
    private String description;
    private String posterUrl;

    // Constructor
    public Movie(String title, String description, String posterUrl) {
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
