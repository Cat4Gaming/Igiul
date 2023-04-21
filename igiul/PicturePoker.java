import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;

/**
 * Die Klasse 'PicturePoker' ist die Szene für das eigentliche Spiel.
 */
public class PicturePoker {
    private static int[] deck; 
    private PPCard[] playerHand, computerHand;

    /**
     * Gibt die Szene für das Spiel PicturePoker zurück.
     * 
     * @param   window      Stage, die benötigt wird, um zur vorherigen Szene (Hauptmenü) zurückzukehren.
     * @param   MainMenu    Szene, die benötigt wird, um zur vorherigen Szene (Hauptmenü) zurückzukehren.
     */
    public Scene giveScene(Stage window, Scene MainMenu) {
        deck = new int[6];
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        
        resetPlayingField();
        
        HBox computerCards = new HBox(25);
        HBox playerCards = new HBox(25);
        for(int i = 0; i < 5; i++){
            computerCards.getChildren().addAll(computerHand[i].getPPCard());
            playerCards.getChildren().addAll(playerHand[i].getPPCard());
        }
        VBox main = new VBox(20);
        main.getChildren().addAll(computerCards, playerCards);
        return new Scene(main);
    }
    
    /**
     * Setzt das Spielfeld zum Anfang zurück, wobei die bisher gewonnene Münzanzahl und Sternanzahl nicht beeinträchtigt wird.
     */
    public void resetPlayingField() {
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
        }
        for(int i = 0; i < 5; i++){
            playerHand[i] = new PPCard(false);
            computerHand[i] = new PPCard(true);
        }
    }
    
    /**
     * Überprüft ob eine Karte ein gewisser Kartenwert als Karte möglich wäre.
     * Wenn es möglich ist wird diese sofort dem Kartendeck entnommen.
     * 
     * @param   cardValue   zu überprüfender Kartenwert
     * @return              ob der Kartenwert möglich ist oder nicht
     */
    public static boolean addPossibleCard(int cardValue) {
        if(deck[cardValue] == 0) return false;
        else {
            deck[cardValue] = deck[cardValue] -1;
            return true;
        }
    }
}