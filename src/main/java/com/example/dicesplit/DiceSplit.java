package com.example.dicesplit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class DiceSplit extends Application {

    // This scene and stage will enable other classes access the primary stage and scene of our main application
    public static Scene sceneMain;

    public static MediaPlayer bgPlayer;

    public static Scene creditScene;

    @Override
    public void start(Stage primaryStage) {

        // Sound Name is Sneaky Snitch by Kevin MacLeod - https://www.chosic.com/download-audio/39325/
        Media backgroundMusic = new Media(new File("Music/bgMusic.mp3").toURI().toString());
        bgPlayer = new MediaPlayer(backgroundMusic);

        // Instantiating our Home Page and adding it to Home Scene

        HomePage homePage = new HomePage(primaryStage);
        Scene homeScene = new Scene(homePage, 450, 750);

        // Instantiating our settings page and adding it to Scene 2

        Settings setting = new Settings(primaryStage);
        Scene settingsScene = new Scene(setting, 450, 750);

        // Instantiating our Game page and Adding it to scene 1

        GamePage gamePage = new GamePage(primaryStage, settingsScene);

        Scene gameScene = new Scene(gamePage, 450, 750);


        CreditPage creditPage = new CreditPage(primaryStage);

        creditScene = new Scene(creditPage, 450, 750);

        sceneMain = gameScene;


        primaryStage.setScene(homeScene);
        primaryStage.setTitle("Dice Split");

        primaryStage.show();

        bgPlayer.play();
        bgPlayer.setCycleCount(1000);

    }

    public static void main(String[] args) {
        Application.launch();
    }
}