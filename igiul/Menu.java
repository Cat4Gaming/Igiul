import java.util.Random;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.List;
import javafx.stage.Screen;

/**
 * Die Klasse Menu ist das Hauptmenü.
 */
public class Menu extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private Scene MainMenu;

    /**
     * Lässt das Hauptmenü anzeigen.
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Igiul");
        window.setFullScreen(true);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.setForceIntegerRenderScale(true);
        Menu menu = this;
        
        Label titel = new Label("Igiul");
        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(new PicturePoker(menu).giveScene());
                window.setFullScreen(true);
            }
        });

        VBox mainmenu = new VBox(20);
        mainmenu.getChildren().addAll(titel, start);
        MainMenu = new Scene(mainmenu);
        //window.setScene(MainMenu);
        window.setScene(new PicturePoker(menu).giveScene());
        window.show();
    }
    
    /**
     * Gibt eine zufällige Zahl (als int) zwischen zwei Werten, die man mit 'min' und 'max' festlegen kann, aus.
     * 
     * @param   min     kleinste Zahl, die ausgegeben werden kann
     * @param   max     größte Zahl, die ausgegeben werden kann
     * @return          zufällig generierte Zahl (int)
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     * Gibt die Stage des Menus zurück.
     * 
     * @return          Menu-Stage
     */
    public Stage getStage() {
        return window;
    }
    
    /**
     * Gibt die Szene des Menus zurück.
     * 
     * @return          Menu-Scene
     */
    public Scene getMenu() {
        return MainMenu;
    }
    
    /**
     * Berechnet die benötigte X-Achsen-Skalierung, um auf Bildschirmen von verschiedenen Auflösungen und Skalierungen zu laufen.
     * 
     * @return          Sklaierung der X-Achse
     */
    public double getScaleX() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth() / (40 * Toolkit.getDefaultToolkit().getScreenResolution());
    }
    
    /**
     * Berechnet die benötigte Y-Achsen-Skalierung, um auf Bildschirmen von verschiedenen Auflösungen und Skalierungen zu laufen.
     * 
     * @return          Sklaierung der Y-Achse
     */
    public double getScaleY() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight() / (22.5 * Toolkit.getDefaultToolkit().getScreenResolution());
    }
}