package com.example.dicesplit;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameLogic extends HBox {

    // Instance of Animations for text animations
    Animations animate = new Animations();

    // Boolean to check if dice are rolling
    private boolean diceRolling = false;
    // Boolean to check if game has ended
    public boolean gameEnded = false;

    // Counter for balance refill attempts
    private int count = 0;

    // Initial balance points
    private int balancePoints = 60;

    // Variables for outcome and dice rolls
    private int outcome;
    private int rollOne;
    private int rollTwo;

    // Rotate transitions for dice animations
    private RotateTransition rotateTransition1;
    private RotateTransition rotateTransition2;

    // Current theme for dice images
    private String theme = "dark";

    // List to store high scores
    public ArrayList<Integer> highScores = new ArrayList<>();

    // File to save high scores
    public File file = new File("Highscore.txt");


    // Sound Name is bell congratulations from uppbeat.io - https://uppbeat.io/sfx/bell-congratulations/7989/23831
    // Media for positive sound effect
    public static Media ding = new Media(new File("Music/sound3.MP3").toURI().toString());
    public static MediaPlayer dingPlayer = new MediaPlayer(ding);

    // Sound Name is negative glass from uppbeat.io - https://uppbeat.io/sfx/notification-alert-negative-glass/13949/35693
    // Media for negative sound effect
    public static Media dingFail = new Media(new File("Music/sound4.MP3").toURI().toString());
    public static MediaPlayer dingFailPlayer = new MediaPlayer(dingFail);

    // Sound Name is Winner Trumpet from uppbeat.io - https://uppbeat.io/sfx/winner-trumpet-fanfare/11352/29787
    // Media for jackpot sound effect
    public static Media jackpotSfx = new Media(new File("Music/sound5.MP3").toURI().toString());
    public static MediaPlayer jackpotSfxPlayer = new MediaPlayer(jackpotSfx);

    // Method to roll dice
    public void rollDice() {

        setSpacing(10);

        Random random = new Random();

        rollOne = random.nextInt(1, 7);
        rollTwo = random.nextInt(1, 7);

        // Load dice images based on the theme and dice roll
        Image diceOne = new Image(getClass().getResourceAsStream(getTheme() + rollOne + ".png"));
        ImageView firstDice = new ImageView(diceOne);

        Image diceTwo = new Image(getClass().getResourceAsStream(getTheme() + rollTwo + ".png"));
        ImageView secondDice = new ImageView(diceTwo);

        // Set dice image dimensions
        firstDice.setFitWidth(160);
        firstDice.setFitHeight(160);

        secondDice.setFitWidth(160);
        secondDice.setFitHeight(160);

        // Initialize rotate transitions for dice
        rotateTransition1 = new RotateTransition(Duration.millis(1800), firstDice);
        rotateTransition1.setByAngle(1440);
        rotateTransition1.setCycleCount(1);
        rotateTransition1.play();

        rotateTransition2 = new RotateTransition(Duration.millis(2200), secondDice);
        rotateTransition2.setByAngle(1440);
        rotateTransition2.setCycleCount(1);
        rotateTransition2.play();

        // Arrays to store dice images for animation
        Image[] diceImages1 = new Image[6];
        for (int i = 0; i < 6; i++) {
            diceImages1[i] = new Image(getClass().getResourceAsStream(getTheme() + (i + 1) + ".png"));
        }

        Image[] diceImages2 = new Image[6];
        for (int i = 0; i < 6; i++) {
            diceImages2[i] = new Image(getClass().getResourceAsStream(getTheme() + (i + 1) + ".png"));
        }

        // Timeline to change the dice image periodically
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(125), event -> {
            // Display a random dice image
            firstDice.setImage(diceImages1[random.nextInt(6)]);
            secondDice.setImage(diceImages2[random.nextInt(6)]);
        }));

        timeline.setCycleCount(12); // Number of times the image changes
        timeline.setOnFinished(event -> {
            // Set the final dice image
            firstDice.setImage(diceOne);
            secondDice.setImage(diceTwo);
        });

        timeline.play();

        getChildren().clear();

        getChildren().addAll(firstDice, secondDice);
        setAlignment(Pos.CENTER);
    }

    // Method for rolling dice when OVER button is clicked
    public void rollDiceOver(Text outcomeText, Text totalPoints, Text winOrLose) {

        dingPlayer.stop();
        dingFailPlayer.stop();
        diceRolling = true;

        rollDice();

        int over = 6;

        outcome = rollOne + rollTwo;

        if (outcome > over) {
            balancePoints = getBalancePoints() + 12;

            rotateTransition2.setOnFinished(e -> {
                outcomeText.setText("+" + 12);
                outcomeText.setFill(Color.GREEN);
                totalPoints.setText("TOTAL POINTS: " + balancePoints);
                animate.outcomeText(outcomeText);
                diceRolling = false;
                winOrLose.setText("You Won!");
                winOrLose.setFill(Color.GREEN);
                animate.winOrLoss(winOrLose);

                if (GamePage.sfx.getText() == "On") {
                    dingPlayer.play();
                }
            });

        } else {
            balancePoints = getBalancePoints() - 12;

            rotateTransition2.setOnFinished(e -> {
                outcomeText.setText("-" + 12);
                outcomeText.setFill(Color.RED);
                totalPoints.setText("TOTAL POINTS: " + balancePoints);
                animate.outcomeText(outcomeText);
                diceRolling = false;
                winOrLose.setText("You Lost!");
                winOrLose.setFill(Color.RED);
                animate.winOrLoss(winOrLose);

                if (GamePage.sfx.getText() == "On" && getBalancePoints() > 1) {
                    dingFailPlayer.play();
                }
            });
        }

        highScores.add(getBalancePoints());
        saveHighestScore();
    }

    // Method for rolling dice when UNDER button is clicked
    public void rollDiceUnder(Text outcomeText, Text totalPoints, Text winOrLose) {
        dingPlayer.stop();
        dingFailPlayer.stop();

        diceRolling = true;

        rollDice();

        int under = 6;

        outcome = rollOne + rollTwo;

        if (outcome < under) {
            balancePoints = getBalancePoints() + 12;

            rotateTransition2.setOnFinished(e -> {
                outcomeText.setText("+" + 12);
                outcomeText.setFill(Color.GREEN);
                totalPoints.setText("TOTAL POINTS: " + balancePoints);
                animate.outcomeText(outcomeText);
                diceRolling = false;
                winOrLose.setText("You Won!");
                winOrLose.setFill(Color.GREEN);
                animate.winOrLoss(winOrLose);

                if (GamePage.sfx.getText() == "On") {
                    dingPlayer.play();
                }
            });

        } else {
            balancePoints = getBalancePoints() - 12;

            rotateTransition2.setOnFinished(e -> {
                outcomeText.setText("-" + 12);
                outcomeText.setFill(Color.RED);
                totalPoints.setText("TOTAL POINTS: " + balancePoints);
                animate.outcomeText(outcomeText);
                diceRolling = false;
                winOrLose.setText("You Lost!");
                winOrLose.setFill(Color.RED);
                animate.winOrLoss(winOrLose);

                if (GamePage.sfx.getText() == "On" && getBalancePoints() > 1) {
                    dingFailPlayer.play();
                }
            });
        }

        highScores.add(getBalancePoints());
        saveHighestScore();
    }

    // Method for rolling dice when JACKPOT button is clicked
    public void rollDiceJackpot(Text outcomeText, Text totalPoints, TextField jackpotField, Text hintText, Text winOrLose) {

        dingFailPlayer.stop();
        jackpotSfxPlayer.stop();
        diceRolling = true;

        try {
            int userJackpot = Integer.parseInt(jackpotField.getText());

            int jackPot = 12;

            if (userJackpot > 1 && userJackpot < 13) {

                rollDice();

                if (GamePage.sfx.getText() == "On") {
                    GamePage.diceSFXPlayer.play();
                }

                hintText.setText(" ");

                outcome = rollOne + rollTwo;

                if (outcome == userJackpot || outcome == jackPot) {
                    balancePoints = getBalancePoints() + 120;

                    rotateTransition2.setOnFinished(e -> {
                        outcomeText.setText("JACKPOT!!!:  +" + 120);
                        outcomeText.setFill(Color.GREEN);
                        totalPoints.setText("TOTAL POINTS: " + balancePoints);
                        animate.outcomeText(outcomeText);
                        diceRolling = false;
                        winOrLose.setText("JACKPOT!!!");
                        winOrLose.setFill(Color.GREEN);
                        animate.winOrLoss(winOrLose);

                        if (GamePage.sfx.getText() == "On") {
                            jackpotSfxPlayer.play();
                        }
                    });

                } else {
                    balancePoints = getBalancePoints() - 24;

                    rotateTransition2.setOnFinished(e -> {
                        outcomeText.setText("-" + 24);
                        outcomeText.setFill(Color.RED);
                        totalPoints.setText("TOTAL POINTS: " + balancePoints);
                        animate.outcomeText(outcomeText);
                        diceRolling = false;
                        winOrLose.setText("You Lost!");
                        winOrLose.setFill(Color.RED);
                        animate.winOrLoss(winOrLose);

                        if (GamePage.sfx.getText() == "On" && getBalancePoints() > 1) {
                            dingFailPlayer.play();
                        }
                    });
                }

            } else {
                hintText.setText("Hint: Enter A Number From 2 - 12");
                diceRolling = false;
            }

        } catch (Exception e) {
            hintText.setText("Hint: Enter A Number From 2 - 12");
            diceRolling = false;
        }

        highScores.add(getBalancePoints());
        saveHighestScore();
    }

    // Method to save the highest score to a file
    public void saveHighestScore() {

        if (getBalancePoints() < 1 || gameEnded) {
            int highestScore = Collections.max(highScores);

            try {
                PrintWriter output = new PrintWriter(file);

                output.print(highestScore);

                output.close();

            } catch (Exception a) {
                System.out.println("An Error Occurred");
            }
        }
    }

    // Method to change the dice color based on the theme
    public void changeDiceColor() {

        Image diceOne = new Image(getClass().getResourceAsStream(getTheme() + rollOne + ".png"));
        ImageView firstDice = new ImageView(diceOne);

        Image diceTwo = new Image(getClass().getResourceAsStream(getTheme() + rollTwo + ".png"));
        ImageView secondDice = new ImageView(diceTwo);

        firstDice.setFitWidth(160);
        firstDice.setFitHeight(160);

        secondDice.setFitWidth(160);
        secondDice.setFitHeight(160);

        getChildren().clear();

        getChildren().addAll(firstDice, secondDice);
        setAlignment(Pos.CENTER);
    }

    // Methods to set the dice theme to light or dark
    public void lightDice() {
        setTheme("dark");
        changeDiceColor();
    }

    public void darkDice() {
        setTheme("light");
        changeDiceColor();
    }

    // Method to refill balance points
    public void refillBalance(Text text) {

        if (getBalancePoints() < 25 && getCount() < 4) {
            setBalancePoints(getBalancePoints() + 12);
            GamePage.totalPoints.setText("TOTAL POINTS: " + balancePoints);
            setCount(getCount() + 1);
            text.setText("Successful!");

        } else if (getBalancePoints() > 24) {
            text.setText("You can only Top Up if balance is below 25 Points!");

        } else if (getCount() > 3) {
            text.setText("You have used up your 3 top ups!");
        }
    }

    // Getter and setter methods for balance points, outcome, dice rolls, dice rolling, theme, and count
    public int getBalancePoints() {
        return balancePoints;
    }

    public void setBalancePoints(int balancePoints) {
        this.balancePoints = balancePoints;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public void setRollOne(int rollOne) {
        this.rollOne = rollOne;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getRollTwo() {
        return rollTwo;
    }

    public void setRollTwo(int rollTwo) {
        this.rollTwo = rollTwo;
    }

    public boolean isDiceRolling() {
        return diceRolling;
    }

    public void setDiceRolling(boolean diceRolling) {
        this.diceRolling = diceRolling;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
