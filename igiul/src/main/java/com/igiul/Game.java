package com.igiul;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Game extends Application{
    private Stage stage;
    private Label label;
    private Button button;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage ps){
        stage = ps;
        stage.setTitle("Igiul - Gioco-d'Azzardo");
        stage.setResizable(false);
        label = new Label("Start");
        button = new Button();
        button.setText("joa");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                label.setText("Starte");
                button.setText("thx");
                button.setDisable(true);
            }
        });
        VBox box = new VBox();
        box.getChildren().add(label);
        box.getChildren().add(button);
        Scene scene0 = new Scene(box, 300, 250);
        ps.setScene(scene0);
        ps.setFullScreen(true);
        ps.show();
    }
}