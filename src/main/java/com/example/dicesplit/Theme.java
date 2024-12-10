package com.example.dicesplit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Theme extends StackPane {

    private String theme = "light";


    public void theme(){

        Image background = new Image(getClass().getResourceAsStream(getTheme() + "Background.jpg"));
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitWidth(450);
        backgroundView.setFitHeight(750);

        this.getChildren().clear();

        this.getChildren().add(backgroundView);

    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
