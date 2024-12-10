package com.example.dicesplit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreditPage extends StackPane {
    // Declaration of static variables for theme, credits, and credit contents text
    public static Theme theme;
    public static Text credits;
    public static Text creditContents;

    // Constructor for the CreditPage class
    CreditPage(Stage primaryStage) {

        // Initialize the theme
        theme = new Theme();

        // Create a BorderPane for layout management
        BorderPane pane = new BorderPane();

        // Create and configure the credits title text
        credits = new Text("Credits");
        credits.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        // Create and configure the text for credit contents
        creditContents = new Text("PROFESSORS \n\n" +
                "Jonathon Bauer\n" +
                "and Cai Filiault\n\n\n" +
                "CORE DEVELOPERS\n\n" +
                "Abdul Aziz\n" +
                "Sesan Popoola\n\n\n" +
                "IMAGES\n\n" +
                "Dice by - Mark Otto, From - IconBolt.com\n" +
                "Logo Designed With Canva Web Application\n" +
                "Game Over Image by - The Florida Mall, From - simon.com\n" +
                "Background by - Apple, From - iphoneswallpapers.com \n" +
                "Icons - From - Google Images\n\n\n" +
                "MUSIC AND SFX\n\n" +
                "Background Music by - Kevin MacLeod, From - chosic.com\n" +
                "Win And Loss Ding Sound Effect, From - uppbeat.io\n" +
                "Jackpot Sound Effect, From - uppbeat.io\n" +
                "Game Over Sound Effect, From - uppbeat.io\n" +
                "Dice Rolling Sound Effect by Over & Out, From - Youtube\n\n\n" +
                "PHOTOSHOP DESIGNS & EDITS\n\n" +
                "Popoola Emmanuel\n\n\n" +
                "CONTRIBUTORS\n\n" +
                "Muhamed Musalemani");

        creditContents.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        // Create a VBox to hold the credits and credit contents, with spacing
        VBox creditContainer = new VBox(30);
        creditContainer.getChildren().addAll(credits, creditContents);
        creditContainer.setAlignment(Pos.CENTER);

        // Create an HBox to center align the VBox containing credits
        HBox creditBox = new HBox();
        creditBox.getChildren().add(creditContainer);
        creditBox.setAlignment(Pos.CENTER);

        // Create and configure the back to game button
        Button gamePageButton = new Button("Back To Game");
        gamePageButton.setTextFill(Color.WHITE);
        gamePageButton.setStyle("-fx-background-color: purple; " +  // Background color
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"           // Padding
        );

        // Create an HBox to hold the back to game button and center align it
        HBox gamePageButtonBox = new HBox();
        gamePageButtonBox.getChildren().add(gamePageButton);
        gamePageButtonBox.setAlignment(Pos.CENTER);
        gamePageButtonBox.setPadding(new Insets(0, 0, 30, 0));

        // Set the action for the back to game button to switch to the main game scene
        gamePageButton.setOnAction(e -> {
            primaryStage.setScene(DiceSplit.sceneMain);
        });

        // Set the center of the BorderPane to the credit box
        pane.setCenter(creditBox);
        // Set the bottom of the BorderPane to the game page button box
        pane.setBottom(gamePageButtonBox);

        // Add the theme and pane to this StackPane
        this.getChildren().addAll(theme, pane);
        // Apply the theme
        theme.theme();
    }
}