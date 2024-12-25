package com.example.movieapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MovieAPI {
    private static final String API_KEY = "YOUR_API_KEY";  // Replace with your TMDB API key
    private static final String API_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=";

    public static List<Movie> fetchMovies(String query) {
        List<Movie> movies = new ArrayList<>();
        try {
            // Create an HttpClient to send a request to TMDB API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + query))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.path("results");

            // Iterate over the results and create Movie objects
            for (JsonNode resultNode : resultsNode) {
                String title = resultNode.path("title").asText();
                String description = resultNode.path("overview").asText();
                String posterPath = resultNode.path("poster_path").asText();
                String posterUrl = "https://image.tmdb.org/t/p/w500" + posterPath;  // Construct poster URL

                movies.add(new Movie(title, description, posterUrl));
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
