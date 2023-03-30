package com.igiul;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Game {
    public abstract Scene giveScene(Stage window, Scene MainMenu, double scaleX, double scaleY);
}
