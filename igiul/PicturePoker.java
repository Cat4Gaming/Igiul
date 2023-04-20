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
    private int[] deck; 
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
            computerCards.getChildren().addAll(computerHand[i]);
            playerCards.getChildren().addAll(playerHand[i]);
        }
        VBox main = new VBox(20);
        main.getChildren().addAll(computerCards, playerCards);
        return new Scene(main);
    }
    
    /**
     * Setzt eine neue Karte aus einem vorgegebenen Kartendeck in die Hand des Spielers
     * 
     * @param   position    !Werte nur zwischen 0 und 4 setzten! die Kartenposition in der Hand, die ersetzt werden soll
     */
    public void setRandPlayerCard(int position) {
        int tmp = Menu.randInt(0, 5);
        playerHand[position] = new PPCard(tmp);
        if(deck[tmp] == 0) {setRandPlayerCard(position);}
        else {deck[tmp] = deck[tmp] - 1;}
        
    }
    
    /**
     * Setzt eine neue Karte aus einem vorgegebenen Kartendeck in die Hand des Computers
     * 
     * @param   position    !Werte nur zwischen 0 und 4 setzten! die Kartenposition in der Hand, die ersetzt werden soll
     */
    public void setRandComputerCard(int position) {
        int tmp = Menu.randInt(0, 5);
        computerHand[position] = new PPCard(tmp, true);
        if(deck[tmp] == 0) {setRandPlayerCard(position);}
        else {deck[tmp] = deck[tmp] - 1;}
    }
    
    /**
     * Setzt das Spielfeld zum Anfang zurück, wobei die bisher gewonnene Münzanzahl und Sternanzahl nicht beeinträchtigt wird.
     */
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