package com.igiul;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Application{
    private Stage stage;
    private Label title, subtitle;
    private Button button;
    private static double scaleX, scaleY;
    public static void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scaleX = screenSize.getWidth()/32;
        scaleY = screenSize.getHeight()/18;
        launch(args);
    }
    @Override
    public void start(Stage ps){
        stage = ps;
        stage.setTitle("Igiul - Gioco-d'Azzardo");
        stage.setResizable(false);
        title = new Label("Igiul");
        title.setFont(new Font(40));
        title.setAlignment(Pos.TOP_CENTER);
        subtitle = new Label("Gioco-d'Azzardo");
        button = new Button();
        button.setText("joa");
        button.setPrefSize(1*scaleX, 1*scaleY);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                button.setText("thx");
                button.setDisable(true);
            }
        });
        VBox box = new VBox();
        box.getChildren().add(title);
        box.getChildren().add(subtitle);
        box.getChildren().add(button);
        Scene scene0 = new Scene(box);
        scene0.setFill(null);
        ps.setScene(scene0);
        ps.setFullScreen(true);
        ps.setFullScreenExitHint("");
        ps.setFullScreenExitKeyCombination(KeyCombination.valueOf("Ü"));
        ps.show();
    }
}