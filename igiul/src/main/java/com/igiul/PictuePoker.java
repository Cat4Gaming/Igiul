package com.igiul;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PictuePoker extends Game {
    private int[] deck, playerHand, computerHand;

    public Scene giveScene(Stage window, Scene MainMenu, double scaleX, double scaleY) {

        Button back = new Button("Zurück");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                window.setScene(MainMenu);
                window.setFullScreen(true);
            }
        });
        
        deck = new int[6];
        playerHand = new int[5];
        computerHand = new int[5];
        resetPlayingField();

        StackPane picturepoker = new StackPane();
        picturepoker.getChildren().addAll(back);
        return new Scene(picturepoker);
    }
    public void setRandPlayerCard(int position) {
        playerHand[position] = Menu.randInt(0, 5);
        if(deck[playerHand[position]] == 0) {setRandPlayerCard(position);}
        else {deck[playerHand[position]] = deck[playerHand[position]] - 1;}
    }
    public void setRandComputerCard(int position) {
        computerHand[position] = Menu.randInt(0, 5);
        if(deck[computerHand[position]] == 0) {setRandPlayerCard(position);}
        else {deck[computerHand[position]] = deck[computerHand[position]] - 1;}
    }
    public void resetPlayingField() {
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
        }
        for(int i = 0; i < 5; i++){
            setRandPlayerCard(i);
            setRandComputerCard(i);
        }
    }
}