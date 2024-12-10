package com.example.dicesplit;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class GameOver extends BorderPane {

    // Constructor for GameOver class
    GameOver() {

    }

    // Function to display the game over screen
    public void GameOverFunction() {
        // Set the background color of the game over screen
        this.setStyle("-fx-background-color: #000000df;");

        // File to read the high score from
        File file = new File("Highscore.txt");

        // Load and set the game over image
        Image gameOverImage = new Image(getClass().getResourceAsStream("gameover.png"));
        ImageView gameOverImageView = new ImageView(gameOverImage);

        // Set the dimensions of the game over image
        gameOverImageView.setFitHeight(300);
        gameOverImageView.setFitWidth(250);

        // Create and style the high score text
        Text highScore = new Text("High Score");
        highScore.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        highScore.setFill(Color.WHITE);

        // Create and style the high score number text
        Text highScoreNumber = new Text();
        highScoreNumber.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        highScoreNumber.setFill(Color.WHITE);

        // Create and style the end game button
        Button endGame = new Button("End Game");

        endGame.setTextFill(Color.WHITE);
        endGame.setStyle("-fx-background-color: purple;" +
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"
        );


        // Set action to end the game and close the application

        endGame.setOnAction(e -> {
            Platform.exit();
        });


        // Create a VBox to hold the game over elements and set alignment
        VBox highScoreBox = new VBox(30);
        highScoreBox.getChildren().addAll(gameOverImageView, highScore, highScoreNumber, endGame);
        highScoreBox.setAlignment(Pos.CENTER);

        // Try to read the high score from the file and display it
        try {
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String highestScore = input.next();
                highScoreNumber.setText(highestScore);
            }

        } catch (Exception a) {
            System.out.println("An Error Occurred");
        }

        // Set the VBox as the center of the game over screen
        this.setCenter(highScoreBox);
    }
}
