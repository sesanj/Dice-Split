package com.example.dicesplit;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Animations {

    public void outcomeText(Text text){

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1200), text);
        translateTransition.setByY(-80);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1200), text);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(10), text);
        translateTransition1.setByY(80);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translateTransition, fadeTransition);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(parallelTransition, translateTransition1);
        sequentialTransition.setCycleCount(1);
        sequentialTransition.play();

    }

    public void winOrLoss(Text text){

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), text);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(6);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

    }

    public void successfulSettings(Text text){

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), text);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }
}
