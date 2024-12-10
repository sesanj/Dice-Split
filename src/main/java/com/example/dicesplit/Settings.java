package com.example.dicesplit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings extends StackPane {

    // Constructor for the Settings class
    public Settings(Stage primaryStage) {

        // Set the background color of the settings screen to transparent
        this.setStyle("-fx-background-color: transparent;");

        Theme theme = new Theme(); // Create a new Theme object
        Animations animate = new Animations(); // Create a new Animations object

        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: transparent;");

        // Text for displaying successful operations
        Text successful = new Text("");
        successful.setFill(Color.GREEN);
        successful.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));

        // Theme Mode Settings
        Text mode = new Text("Theme/Mode");
        mode.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox themeButtons = new HBox(10); // Horizontal box for theme buttons
        VBox themeBox = new VBox(10); // Vertical box for theme settings

        Button dark = new Button("Dark Mode");
        Button light = new Button("Light Mode");

        themeButtons.getChildren().addAll(dark, light);
        themeButtons.setAlignment(Pos.TOP_LEFT);

        themeBox.getChildren().addAll(mode, themeButtons);
        themeBox.setAlignment(Pos.TOP_LEFT);

        // Balance Refill Settings
        Text topBalance = new Text("Refill Balance");
        topBalance.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button topUpButton = new Button("Get 12 Points");
        VBox topUpBox = new VBox(10);

        topUpBox.getChildren().addAll(topBalance, topUpButton);

        // Action to refill balance and display success message
        topUpButton.setOnAction(e -> {
            GamePage.gameLogic.refillBalance(successful);
            animate.successfulSettings(successful);
        });

        HBox bottomBox = new HBox(20); // Horizontal box for bottom elements
        bottomBox.getChildren().addAll(successful);

        // Background Music Settings
        Text music = new Text("Background Music");
        music.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox musicButtons = new HBox(10); // Horizontal box for music buttons
        VBox musicBox = new VBox(10); // Vertical box for music settings

        Button bgOn = new Button("On BG Music");
        Button bgOff = new Button("Off BG Music");

        musicButtons.getChildren().addAll(bgOn, bgOff);
        musicButtons.setAlignment(Pos.TOP_LEFT);

        musicBox.getChildren().addAll(music, musicButtons);
        musicBox.setAlignment(Pos.TOP_LEFT);

        // Actions to play or pause background music
        bgOn.setOnAction(e -> {
            DiceSplit.bgPlayer.play();
        });

        bgOff.setOnAction(e -> {
            DiceSplit.bgPlayer.pause();
        });

        // SFX Music Settings
        Text sfxMusic = new Text("SFX Settings");
        sfxMusic.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox sfxMusicButtons = new HBox(10); // Horizontal box for SFX buttons
        VBox sfxMusicBox = new VBox(10); // Vertical box for SFX settings

        Button sfxOn = new Button("On SFX");
        Button sfxOff = new Button("Off SFX");

        sfxMusicButtons.getChildren().addAll(sfxOn, sfxOff);
        sfxMusicButtons.setAlignment(Pos.TOP_LEFT);

        sfxMusicBox.getChildren().addAll(sfxMusic, sfxMusicButtons);
        sfxMusicBox.setAlignment(Pos.TOP_LEFT);

        // Actions to enable or disable SFX
        sfxOn.setOnAction(e -> {
            GamePage.sfx.setText("On");
        });

        sfxOff.setOnAction(e -> {
            GamePage.sfx.setText("Off");
        });

        // Game Rules
        Label rulesLabel = new Label("Rules:");
        Label rulesContent = new Label("--> GAME PLAY: \n" +
                "1) Starting Balance: 60 points\n" +
                "2) Click Over to decide if outcome will be greater than 6 \n" +
                "3) Click Under to decide if the outcome will be less than 6 \n" +
                "4) Game ends when balance falls to Zero or you quit \n" +
                "--> POINTS:\n" +
                "5) You get 12 points if you win either Over or Under\n" +
                "6) You lose 12 points if lose either Over or Under\n" +
                "--> JACKPOT:\n" +
                "7) Enter your desired number for a jackpot\n" +
                "8) Outcome = number or double 6, JACKPOT (+120 Points)\n" +
                "9) Miss the jackpot and lose 24 points");

        rulesContent.setWrapText(true);
        rulesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        rulesContent.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        rulesContent.setUnderline(true);

        VBox rulesBox = new VBox(10); // Vertical box for rules
        rulesBox.setAlignment(Pos.CENTER_LEFT);
        rulesBox.getChildren().addAll(rulesLabel, rulesContent);

        // Setting image
        Image setting = new Image(getClass().getResourceAsStream("cancel.png"));
        ImageView settings = new ImageView(setting);

        settings.setFitWidth(40);
        settings.setFitHeight(40);

        VBox settingsBox = new VBox(); // Vertical box for settings image
        settingsBox.getChildren().add(settings);
        settingsBox.setAlignment(Pos.TOP_RIGHT);
        settingsBox.setPadding(new Insets(15, 10, 0, 0));

        // Action to switch to dark mode
        dark.setOnAction(e -> {
            GamePage.gameLogic.darkDice();
            successful.setText("Success!");
            animate.successfulSettings(successful);

            mode.setFill(Color.WHITE);
            topBalance.setFill(Color.WHITE);

            rulesLabel.setTextFill(Color.WHITE);
            rulesContent.setTextFill(Color.WHITE);

            music.setFill(Color.WHITE);

            theme.setTheme("dark");
            theme.theme();

            GamePage.theme.setTheme("dark");
            GamePage.theme.theme();

            CreditPage.theme.setTheme("dark");
            CreditPage.theme.theme();

            HomePage.theme.setTheme("dark");
            HomePage.theme.theme();

            CreditPage.credits.setFill(Color.WHITE);
            CreditPage.creditContents.setFill(Color.WHITE);

            GamePage.userName.setFill(Color.web("#b5b5b5"));
            GamePage.totalPoints.setFill(Color.web("#b5b5b5"));
        });

        // Action to switch to light mode
        light.setOnAction(e -> {
            GamePage.gameLogic.lightDice();
            successful.setText("Success!");
            animate.successfulSettings(successful);

            mode.setFill(Color.BLACK);
            topBalance.setFill(Color.BLACK);

            rulesLabel.setTextFill(Color.BLACK);
            rulesContent.setTextFill(Color.BLACK);

            music.setFill(Color.BLACK);

            theme.setTheme("light");
            theme.theme();

            GamePage.theme.setTheme("light");
            GamePage.theme.theme();

            CreditPage.theme.setTheme("light");
            CreditPage.theme.theme();

            HomePage.theme.setTheme("light");
            HomePage.theme.theme();

            CreditPage.credits.setFill(Color.BLACK);
            CreditPage.creditContents.setFill(Color.BLACK);

            GamePage.userName.setFill(Color.web("#444f5a"));
            GamePage.totalPoints.setFill(Color.web("#444f5a"));
        });

        // Button to view credits
        Button viewCredits = new Button("View Credits");
        viewCredits.setTextFill(Color.WHITE);
        viewCredits.setStyle("-fx-background-color: purple; " +  // Background color
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"           // Padding
        );

        // Action to switch to credits scene
        viewCredits.setOnAction(e -> {
            primaryStage.setScene(DiceSplit.creditScene);
        });

        HBox viewCreditsBox = new HBox(); // Horizontal box for view credits button
        viewCreditsBox.getChildren().addAll(viewCredits);
        viewCreditsBox.setPadding(new Insets(0, 0, 20, 0));
        viewCreditsBox.setAlignment(Pos.CENTER);

        // Action to switch back to the main scene
        settings.setOnMouseClicked(e -> {
            primaryStage.setScene(DiceSplit.sceneMain);
        });

        VBox allContents = new VBox(25); // Vertical box for all settings elements
        allContents.getChildren().addAll(themeBox, musicBox, sfxMusicBox, topUpBox, rulesBox);
        allContents.setPadding(new Insets(0, 0, 0, 30));
        allContents.setAlignment(Pos.CENTER_LEFT);

        pane.setCenter(allContents); // Set the central content of the pane
        pane.setBottom(viewCreditsBox); // Set the bottom content of the pane
        pane.setRight(settingsBox); // Set the right content of the pane

        this.getChildren().addAll(theme, pane); // Add theme and pane to the stack pane

        theme.theme(); // Apply the theme
    }
}
