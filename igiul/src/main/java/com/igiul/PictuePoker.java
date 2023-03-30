package com.igiul;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PictuePoker extends Game{
    public Scene giveScene(Stage window, Scene MainMenu, double scaleX, double scaleY){
        Button back = new Button("Zurück");
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                window.setScene(MainMenu);
                window.setFullScreen(true);
            }
        });

        StackPane picturepoker = new StackPane();
        picturepoker.getChildren().addAll(back);
        return new Scene(picturepoker);
    }
}