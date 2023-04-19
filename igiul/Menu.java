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
        
        Label titel = new Label("Igiul");
        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(new PicturePoker().giveScene(window, MainMenu));
                window.setFullScreen(true);
            }
        });

        VBox mainmenu = new VBox(20);
        mainmenu.getChildren().addAll(titel, start);
        MainMenu = new Scene(mainmenu);
        //window.setScene(MainMenu);
        window.setScene(new PicturePoker().giveScene(window, MainMenu));
        window.show();
    }
    
    /**
     * Gibt eine zufällige Zahl (als int) zwischen zwei Werten, die man mit 'min' und 'max' festlegen kann, aus.
     * 
     * @param   min     kleinste Zahl, die ausgegeben werden kann
     * @param   max     größte Zahl, die ausgegeben werden kann
     * @return          zufällig generierte Zahl (int)
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}