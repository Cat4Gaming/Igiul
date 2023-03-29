package com.igiul;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application{

    private static double scaleX, scaleY;

    public static void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scaleX = screenSize.getWidth()/32;
        scaleY = screenSize.getHeight()/18;
        launch(args);
    }

    private Stage window;
    private Scene MainMenu, PicturePoker;

    @Override
    public void start(Stage primaryStage){
        window = primaryStage;
        
        //MainMenu-Scene
        Label titel = new Label("Igiul");
        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                window.setScene(PicturePoker);
                window.setFullScreen(true);
            }
        });

        VBox mainmenu = new VBox(20);
        mainmenu.getChildren().addAll(titel, start);
        MainMenu = new Scene(mainmenu, scaleX*32, scaleY*18);

        //PicturePoker-Scene
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
        PicturePoker = new Scene(picturepoker, 1920, 1080);
        
        //First-Time-Setup
        window.setScene(MainMenu);
        window.setTitle("Igiul");
        window.setFullScreen(true);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.show();
    }
}