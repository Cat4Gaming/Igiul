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
import javafx.scene.text.Text;
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
     */
    public Scene giveScene() {
        deck = new int[6];
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        
        resetPlayingField();
        
        VBox main = new VBox(50);
            HBox top_bar = new HBox();
                MenuButton menuButton = new MenuButton();
            top_bar.getChildren().addAll(menuButton);
            HBox center = new HBox();
                HBox instruction = new HBox(20);
                    ImageView cardcombovalue = new ImageView(new Image("assets/gfx/cardcombocalue.png", true));
                    cardcombovalue.setFitWidth(545);
                    cardcombovalue.setPreserveRatio(true);
                    ImageView cardvalue = new ImageView(new Image("assets/gfx/cardvalue.png", true));
                    cardvalue.setFitWidth(150);
                    cardvalue.setPreserveRatio(true);
                instruction.getChildren().addAll(cardvalue, cardcombovalue);
                VBox hands = new VBox(150);
                    HBox computerCards = new HBox(25);
                    HBox playerCards = new HBox(25);
                    for(int i = 0; i < 5; i++){
                        computerCards.getChildren().addAll(computerHand[i].getPPCard());
                        playerCards.getChildren().addAll(playerHand[i].getPPCard());
                    }
                hands.getChildren().addAll(computerCards, playerCards);
            center.getChildren().addAll(instruction, hands);
            Text t = new Text(Integer.toString(compareHands()));
        main.getChildren().addAll(t, top_bar, center);
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
            computerHand[i] = new PPCard(false);
        }
    }
    
    /**
     * Vergleicht die 'Hand' des Spielers mit der des Computers und gibt das ergebnis aus.
     * 
     * @return             '-1' = Verloren; 
     *                      '0' = Gleichstand; 
     *                      '1' = Gewonnen
     */
    public int compareHands(){
        int[] playerValue = new int[6];
        for(int i = 0; i < 5; i++) {
            playerValue[playerHand[i].getValue()] = playerValue[playerHand[i].getValue()] + 1;
        }
        int[] computerValue = new int[6];
        for(int i = 0; i < 5; i++) {
            computerValue[computerHand[i].getValue()] = computerValue[computerHand[i].getValue()] + 1;
        }
        if(handValue(playerValue) > handValue(computerValue)) {
            return 1;
        } else if(handValue(playerValue) < handValue(computerValue)) {
            return -1;
        } else {
            for(int i = 5; i >= 0; i--) {
                if(playerValue[i] == 5 || playerValue[i] == 4 || playerValue[i] == 3) return 1;
                if(computerValue[i] == 5 || computerValue[i] == 4 || computerValue[i] == 3) return -1;
            }
            for(int i = 5; i >= 0; i--) {
                if(playerValue[i] == 2 && computerValue[i] != 2) return 1;
                for(int y = 6; y >= 0; y--) {
                    if(y != i) {
                        if(playerValue[i] == 2 && computerValue[i] != 2) return 1;
                        if(playerValue[i] != 2 && computerValue[i] == 2) return -1;
                    }
                }
            }
            return 0;
        }
    }
    
    /**
     * Berechnet den Wert der übergebenen 'Hand' und gibt diesen aus.
     * 
     * @param   value       Array einer 'Hand', dessen Wert ausgegeben werden soll.
     * @return             Wert der zuvor übergebenen 'Hand'
     */
    public int handValue(int[] value) {
        for(int i = 0; i < 6; i++){
            if(value[i] == 5) return 16;
            if(value[i] == 4) return 8;
            if(value[i] == 3) {
                for(int y = 0; y < value.length; y++) {
                    if(value[y] == 2) return 6;
                    return 4;
                }
            }
            if(value[i] == 2) {
                for(int y = 0; y < value.length; y++) {
                    if(value[y] == 2) return 3;
                    return 2;
                }
            }
        }
        return 0;
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