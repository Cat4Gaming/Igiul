package com.igiul;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    private static double scaleX, scaleY;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scaleX = screenSize.getWidth()/32;
        scaleY = screenSize.getHeight()/18;
        launch(args);
    }

    private Stage window;
    private Scene MainMenu;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Igiul");
        window.setFullScreen(true);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        Label titel = new Label("Igiul");
        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(new PictuePoker().giveScene(window, MainMenu, scaleX, scaleY));
                window.setFullScreen(true);
            }
        });

        VBox mainmenu = new VBox(20);
        mainmenu.getChildren().addAll(titel, start);
        MainMenu = new Scene(mainmenu);
        
        window.setScene(MainMenu);
        window.show();
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}