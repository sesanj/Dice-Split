package com.example.dicesplit;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomePage extends StackPane {

    // Declaration of a static Theme variable
    public static Theme theme;

    // Constructor for the HomePage class
    HomePage(Stage primaryStage) {

        // Create a BorderPane for layout management
        BorderPane pane = new BorderPane();

        // Set the background of the pane to transparent
        pane.setStyle("-fx-background-color: transparent;");

        // Initialize the theme
        theme = new Theme();

        // Set the background of this StackPane to transparent
        this.setStyle("-fx-background-color: transparent;");

        // Logo related codes
        VBox logoBox = new VBox();

        // Load and set the logo image
        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(280);
        logoView.setFitHeight(150);

        // Add the logo to the VBox and center it
        logoBox.getChildren().add(logoView);
        logoBox.setAlignment(Pos.CENTER);

        // Rotating designs
        ImageView image1 = new ImageView(new Image(getClass().getResourceAsStream("setting.png")));
        image1.setFitHeight(50);
        image1.setFitWidth(50);


        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("setting.png")));
        image2.setFitHeight(50);
        image2.setFitWidth(50);

        ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("setting.png")));
        image3.setFitHeight(50);
        image3.setFitWidth(50);

        ImageView image4 = new ImageView(new Image(getClass().getResourceAsStream("setting.png")));
        image4.setFitHeight(50);
        image4.setFitWidth(50);

        // Create an HBox to hold the rotating images and set spacing
        HBox imageBox = new HBox(20);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.getChildren().addAll(image1, image2, image3, image4);

        // Create and configure rotate transitions for the images
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(10000), image1);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.play();

        RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(10000), image2);
        rotateTransition2.setByAngle(-360f);
        rotateTransition2.setCycleCount(Timeline.INDEFINITE);
        rotateTransition2.play();

        RotateTransition rotateTransition3 = new RotateTransition(Duration.millis(10000), image3);
        rotateTransition3.setByAngle(360f);
        rotateTransition3.setCycleCount(Timeline.INDEFINITE);
        rotateTransition3.play();

        RotateTransition rotateTransition4 = new RotateTransition(Duration.millis(10000), image4);
        rotateTransition4.setByAngle(-360f);
        rotateTransition4.setCycleCount(Timeline.INDEFINITE);
        rotateTransition4.play();


        // Set image for speakerIcon
        Image speaker = new Image(getClass().getResourceAsStream("speaker.png"));
        Image speakerOff = new Image(getClass().getResourceAsStream("speaker off.png"));
        ImageView speakerIcon = new ImageView(speaker);

        // Set fit width and height for speakerIcon
        speakerIcon.setFitWidth(40);
        speakerIcon.setFitHeight(40);

        // Place the speakerIcon inside an HBox
        HBox speakerIconBox = new HBox();
        speakerIconBox.getChildren().add(speakerIcon);
        speakerIconBox.setAlignment(Pos.TOP_RIGHT);
        speakerIconBox.setPadding(new Insets(10, 10, 0, 0));

        // Set action on the speaker icon to mute sound
        speakerIcon.setOnMouseClicked(e -> {

            if (DiceSplit.bgPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                DiceSplit.bgPlayer.pause();
                speakerIcon.setImage(speakerOff);
            }else{
                DiceSplit.bgPlayer.play();
                speakerIcon.setImage(speaker);
            }

        });


        // Set image for speakerIcon
        Image quit = new Image(getClass().getResourceAsStream("off.png"));
        ImageView quitIcon = new ImageView(quit);

        // Set fit width and height for speakerIcon
        quitIcon.setFitWidth(40);
        quitIcon.setFitHeight(40);

        // Place the speakerIcon inside an HBox
        HBox quitIconBox = new HBox();
        quitIconBox.getChildren().add(quitIcon);
        quitIconBox.setAlignment(Pos.TOP_LEFT);
        quitIconBox.setPadding(new Insets(10, 0, 0, 10));


        quitIcon.setOnMouseClicked(e -> {
            Platform.exit();
        });


        // Player name and input related codes
        VBox playerNameBox = new VBox(15); // 15 is the spacing between elements

        // Create and configure the player name label
        Label playerNameLabel = new Label("Player Name");
        playerNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Create and configure the text field for player name input
        TextField playerNameInput = new TextField();
        playerNameInput.setAlignment(Pos.CENTER);
        playerNameInput.setPromptText("Enter your name");
        playerNameInput.setMaxWidth(200);

        // Create and configure the start button
        Button startButton = new Button("Start Game");
        startButton.setTextFill(Color.WHITE);
        startButton.setStyle("-fx-background-color: purple; " +  // Background color
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"           // Padding
        );

        // Center align the player name box and add the label, input, and button
        playerNameBox.setAlignment(Pos.CENTER);
        playerNameBox.getChildren().addAll(playerNameLabel, playerNameInput, startButton);

        // Add all contents to appropriate boxes
        VBox allContentsBox = new VBox();
        allContentsBox.setSpacing(80);
        allContentsBox.setAlignment(Pos.CENTER);
        allContentsBox.getChildren().addAll(logoBox, imageBox, playerNameBox);

        // Set the center of the BorderPane to the allContentsBox
        pane.setCenter(allContentsBox);
        // Set the right of the BorderPane to the speakerIconBox
        pane.setRight(speakerIconBox);
        // Set the left of the BorderPane to the quitIconBox
        pane.setLeft(quitIconBox);

        // Set the action for the start button to switch to the main game scene
        startButton.setOnAction(e -> {
            primaryStage.setScene(DiceSplit.sceneMain);
            GamePage.userName.setText("Welcome! " + playerNameInput.getText());
        });

        // Add the theme and pane to this StackPane
        this.getChildren().addAll(theme, pane);

        // Apply the theme
        theme.theme();
    }
}