package com.example.movieapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Create UI elements
        TextField searchField = new TextField();
        searchField.setPromptText("Enter movie name...");
        
        Button searchButton = new Button("Search");
        
        // Text and Image for movie details
        Text movieTitle = new Text();
        Text movieDescription = new Text();
        ImageView moviePoster = new ImageView();

        // Search button action
        searchButton.setOnAction(e -> {
            String query = searchField.getText();
            if (!query.isEmpty()) {
                List<Movie> movies = MovieAPI.fetchMovies(query);
                if (!movies.isEmpty()) {
                    Movie movie = movies.get(0);  // Show the first movie result
                    movieTitle.setText(movie.getTitle());
                    movieDescription.setText(movie.getDescription());
                    moviePoster.setImage(new Image(movie.getPosterUrl()));
                }
            }
        });

        // Add elements to the layout
        root.getChildren().addAll(searchField, searchButton, movieTitle, movieDescription, moviePoster);

        // Set up the scene and stage
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Movie Search Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
