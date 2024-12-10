package com.example.dicesplit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class GamePage extends StackPane {

    // Static variable for dice sound effects player
    public static MediaPlayer diceSFXPlayer;
    // Static variable for game over sound effects player (commented out)
    // public static MediaPlayer gameOverSfxPlayer;

    // Static variable for game logic, used to access methods from settings page
    public static GameLogic gameLogic = new GameLogic();
    // Static text for displaying total points, used for balance refill function in GameLogic
    public static Text totalPoints;

    // Static theme instance
    public static Theme theme = new Theme();

    // Static text for displaying user name
    public static Text userName;

    // Static text for sound effects status
    public static Text sfx;

    // Constructor for the GamePage class
    public GamePage(Stage primaryStage, Scene scene1) {

        // Initialize sound effects status text
        sfx = new Text("On");

        // Load dice roll sound effect
        // Sound from over and out youtube channel - https://youtu.be/MK0l_TQH6aA?si=ggHjeeZ4vMteU5ED
        Media diceSFX = new Media(new File("Music/sound2.MP3").toURI().toString());
        diceSFXPlayer = new MediaPlayer(diceSFX);

        // Load game over sound effect
        // Sound Name is Tuba Sting from uppbeat.io - https://uppbeat.io/sfx/tuba-sting-wrong-answer/3111/17041
        Media gameOverSfx = new Media(new File("Music/sound6.MP3").toURI().toString());
        MediaPlayer gameOverSfxPlayer = new MediaPlayer(gameOverSfx);

        // Create a BorderPane for layout management
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: transparent;");
        this.setStyle("-fx-background-color: transparent;");

        // HBox for play buttons with spacing
        HBox playButtons = new HBox(50);

        // VBox for game view with spacing and padding
        VBox gameView = new VBox(30);
        gameView.setPadding(new Insets(0, 0, 70, 0));

        // VBox for settings box
        VBox settingsBox = new VBox();

        // VBox for menu box
        VBox menuBox = new VBox();

        // HBox for top box
        HBox topBox = new HBox();

        // VBox for dice box with spacing and alignment
        VBox diceBox = new VBox(10);
        diceBox.setAlignment(Pos.CENTER);

        // Text for outcome with padding and font configuration
        Text outcome = new Text(" ");
        diceBox.setPadding(new Insets(0, 0, 40, 0));
        outcome.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        diceBox.getChildren().addAll(gameLogic, outcome);

        // VBox for name and point box with spacing
        VBox nameAndPointBox = new VBox(5);

        // VBox for jackpot box with padding
        VBox jackpotBox = new VBox(6);
        jackpotBox.setPadding(new Insets(0, 0, 20, 0));

        // Load settings and quit images
        Image setting = new Image(getClass().getResourceAsStream("settings.png"));
        ImageView settings = new ImageView(setting);
        Image quit = new Image(getClass().getResourceAsStream("off.png"));
        ImageView quitMenu = new ImageView(quit);

        // Set fit width and height for settings and quit images
        settings.setFitWidth(40);
        settings.setFitHeight(40);
        quitMenu.setFitWidth(40);
        quitMenu.setFitHeight(40);

        // Create and configure jackpot field and hint text
        TextField jackpotField = new TextField();
        jackpotField.setMaxWidth(50);
        jackpotField.setAlignment(Pos.CENTER);
        Text jackpotHint = new Text("");
        jackpotHint.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 12));
        jackpotHint.setFill(Color.web("#444f5a"));

        // Create and configure outcome text
        Text outcomeText = new Text(" ");
        outcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        // Create and configure total points text
        totalPoints = new Text("TOTAL POINTS: 60");
        totalPoints.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalPoints.setFill(Color.web("#444f5a"));

        // Create and configure user name text
        userName = new Text();
        userName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        userName.setFill(Color.web("#444f5a"));

        // Create and configure game buttons
        Button over = new Button("OVER");
        Button under = new Button("UNDER");
        Button jackpot = new Button("JACKPOT");

        // Style jackpot button
        jackpot.setTextFill(Color.WHITE);
        jackpot.setStyle("-fx-background-color: purple; " +  // Background color
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"           // Padding
        );

        // Style over and under buttons
        over.setTextFill(Color.WHITE);
        under.setTextFill(Color.WHITE);
        over.setStyle("-fx-background-color: #444f5a;" +
                "-fx-background-radius: 20px; " +   // Border radius
                "-fx-font-size: 14px; " +           // Font size
                "-fx-font-weight: bold; " +         // Font weight
                "-fx-padding: 8px 18px;"
        );
        under.setStyle("-fx-background-color: #444f5a;" +
                "-fx-background-radius: 20px; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 8px 18px;"
        );

        // Set minimum width for buttons
        over.setMinWidth(80);
        under.setMinWidth(80);
        jackpot.setMinWidth(80);

        // Create instance of GameOver class
        GameOver gameOver = new GameOver();

        // Set action for over button
        over.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                diceSFXPlayer.stop();
                gameOverSfxPlayer.stop();

                if (!gameLogic.isDiceRolling()) {
                    gameLogic.rollDiceOver(outcomeText, totalPoints, outcome);
                    if (sfx.getText().equals("On")) {
                        diceSFXPlayer.play();
                    }
                }
                if (gameLogic.getBalancePoints() < 1) {
                    GamePage.this.getChildren().clear();
                    gameOver.GameOverFunction();
                    GamePage.this.getChildren().addAll(theme, pane, gameOver);
                    DiceSplit.bgPlayer.stop();
                    if (sfx.getText().equals("On")) {
                        diceSFXPlayer.stop();
                        gameOverSfxPlayer.play();
                    }
                }
            }
        });

        // Set action for under button
        under.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                diceSFXPlayer.stop();
                gameOverSfxPlayer.stop();

                if (!gameLogic.isDiceRolling()) {
                    gameLogic.rollDiceUnder(outcomeText, totalPoints, outcome);
                    if (sfx.getText().equals("On")) {
                        diceSFXPlayer.play();
                    }
                }
                if (gameLogic.getBalancePoints() < 1) {
                    GamePage.this.getChildren().clear();
                    gameOver.GameOverFunction();
                    GamePage.this.getChildren().addAll(theme, pane, gameOver);
                    DiceSplit.bgPlayer.stop();
                    if (sfx.getText().equals("On")) {
                        diceSFXPlayer.stop();
                        gameOverSfxPlayer.play();
                    }
                }
            }
        });

        // Set action for jackpot button
        jackpot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameOverSfxPlayer.stop();
                diceSFXPlayer.stop();
                if (!gameLogic.isDiceRolling()) {
                    gameLogic.rollDiceJackpot(outcomeText, totalPoints, jackpotField, jackpotHint, outcome);
                }
                if (gameLogic.getBalancePoints() < 1) {
                    GamePage.this.getChildren().clear();
                    GamePage.this.getChildren().addAll(theme, pane, gameOver);
                    gameOver.GameOverFunction();
                    DiceSplit.bgPlayer.stop();
                    if (sfx.getText().equals("On")) {
                        diceSFXPlayer.stop();
                        gameOverSfxPlayer.play();
                    }
                }
            }
        });

        // Add play buttons to HBox and set alignment and padding
        playButtons.getChildren().addAll(over, under);
        playButtons.setAlignment(Pos.CENTER);
        playButtons.setPadding(new Insets(30, 0, 0, 0));

        // Set padding for various boxes
        settingsBox.setPadding(new Insets(5, 10, 0, 10));
        menuBox.setPadding(new Insets(5, 10, 0, 10));
        nameAndPointBox.setPadding(new Insets(20, 0, -60, 0));

        // Add elements to jackpot box and set alignment
        jackpotBox.getChildren().addAll(jackpotField, jackpot, jackpotHint);
        jackpotBox.setAlignment(Pos.CENTER);

        // Add elements to name and point box and set alignment
        nameAndPointBox.getChildren().addAll(userName, totalPoints);
        nameAndPointBox.setAlignment(Pos.CENTER);

        // Add name and point box to top box and set alignment
        topBox.getChildren().add(nameAndPointBox);
        topBox.setAlignment(Pos.CENTER);

        // Add elements to game view and set alignment
        gameView.getChildren().addAll(outcomeText, diceBox, playButtons);
        gameView.setAlignment(Pos.BOTTOM_CENTER);

        // Add settings to settings box and set alignment
        settingsBox.getChildren().add(settings);
        settingsBox.setAlignment(Pos.TOP_RIGHT);

        // Add home menu to menu box and set alignment
        menuBox.getChildren().add(quitMenu);
        menuBox.setAlignment(Pos.TOP_LEFT);

        // Set positions of elements in BorderPane
        pane.setCenter(gameView);
        pane.setTop(topBox);
        pane.setBottom(jackpotBox);
        pane.setLeft(menuBox);
        pane.setRight(settingsBox);

        // Set actions for settings and Quit menu images
        settings.setOnMouseClicked(e -> {
            primaryStage.setScene(scene1);
        });
        quitMenu.setOnMouseClicked(e -> {
            gameLogic.gameEnded = true;
            gameLogic.saveHighestScore();
            gameOver.GameOverFunction();
            GamePage.this.getChildren().clear();
            GamePage.this.getChildren().addAll(theme, pane, gameOver);
            DiceSplit.bgPlayer.stop();
            gameOverSfxPlayer.stop();
            gameOverSfxPlayer.play();
        });

        // Initialize dice roll
        gameLogic.rollDice();

        // Add theme and pane to this StackPane
        this.getChildren().addAll(theme, pane);
        // Apply the theme
        theme.theme();
    }


}
