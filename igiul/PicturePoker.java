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
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/**
 * Die Klasse 'PicturePoker' ist die Szene für das eigentliche Spiel.
 */
public class PicturePoker {
    private static int[] deck; 
    private PPCard[] playerHand, computerHand;
    private Menu menu;
    private Text t, ftxt, pl, cm;
    private double scaleX, scaleY;

    /** 
     * @param   menu        Benötigt das Menu, um wieder zurückzukehren.
     */
    public PicturePoker(Menu menu) {
        this.menu = menu;
    }
    
    /**
     * Gibt die Szene für das Spiel PicturePoker zurück.
     */
    public Scene giveScene() {
        scaleX = menu.getScaleX();
        scaleY = menu.getScaleY();
        deck = new int[6];
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        resetPlayingField();
        Font darumadropOneFont = Font.loadFont(getClass().getResource("/assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf").toExternalForm(), 60);
        
        VBox main = new VBox(100 * scaleY);
            HBox top_bar = new HBox();
                Button menuButton = new Button("Menu");
                menuButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        menu.getStage().setScene(menu.getMenu());
                        menu.getStage().setFullScreen(true);
                    }
                });
                Button drawButton = new Button("Draw");
                drawButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        changeSelectedCards();
                    }
                });
            top_bar.getChildren().addAll(menuButton, drawButton);
            HBox center = new HBox();
                PPInstruction instruction = new PPInstruction(this);
                VBox hands = new VBox(300 * scaleY);
                    HBox computerCards = new HBox(50 * scaleX);
                    HBox playerCards = new HBox(50 * scaleX);
                    for(int i = 0; i < 5; i++){
                        computerCards.getChildren().addAll(computerHand[i].getPPCard());
                        playerCards.getChildren().addAll(playerHand[i].getPPCard());
                    }
                hands.getChildren().addAll(computerCards, playerCards);
            center.getChildren().addAll(instruction, hands);
            t = new Text(Integer.toString(compareHands()));
            t.setFont(darumadropOneFont);
            t.setStyle("-fx-fill: RED; -fx-font-size: 16px; -fx-effect: dropshadow(gaussian, BLACK, 3,1.0, 0,0);");
            ftxt = new Text(scaleX + " " + scaleY);
        main.getChildren().addAll(t, ftxt, top_bar, center);
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
            playerHand[i] = new PPCard(false, this);
            computerHand[i] = new PPCard(false, this);
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
            int tmp = playerHand[i].getValue();
            playerValue[tmp] = playerValue[tmp] + 1;
        }
        int[] computerValue = new int[6];
        for(int i = 0; i < 5; i++) {
            int tmp = computerHand[i].getValue();
            computerValue[tmp] = computerValue[tmp] + 1;
        }
        if(handValue(playerValue) > handValue(computerValue)) return 11;
        if(handValue(playerValue) < handValue(computerValue)) return -11;
        for(int i = 5; i >= 0; i--) {
            if(playerValue[i] == 5 || playerValue[i] == 4 || playerValue[i] == 3) {
                if(playerValue[i] != computerValue[i]) return 12;
            }
            if(computerValue[i] == 5 || computerValue[i] == 4 || computerValue[i] == 3) {
                if(playerValue[i] != computerValue[i]) return -12;
            }
        }
        for(int i = 5; i >= 0; i--) {
            if(playerValue[i] == 2 && computerValue[i] != 2) return 13;
            if(playerValue[i] != 2 && computerValue[i] == 2) return -13;
            if(playerValue[i] == 2 && computerValue[i] == 2) {
                for(int y = 5; y >= 0; y--) {
                    if(y != i) {
                        if(playerValue[y] == 2 && computerValue[y] != 2) return 14;
                        if(playerValue[y] != 2 && computerValue[y] == 2) return -14;
                    }
                }
            }
        }
        return 0;
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
                for(int y = 0; y < 6; y++) {
                    if(y != i && value[y] == 2) return 6;
                }
                return 4;
            }
        }
        for(int i = 0; i < 6; i++) {
            if(value[i] == 2) {
                for(int y = 0; y < 6; y++) {
                    if(y != i && value[y] == 2) return 3;
                }
                return 2;
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
    public boolean addPossibleCard(int cardValue) {
        if(deck[cardValue] == 0) return false;
        else {
            deck[cardValue] = deck[cardValue] -1;
            return true;
        }
    }
    
    /**
     * Gibt das Hauptmenu zurück
     * 
     * @return              Hauptmenu
     */
    public Menu getMenu() {
        return menu;
    }
    
    /**
     * Ändert alle ausgewählten Karten in neue von dem KartenDeck.
     * 
     * @param   selCards    Array von allen Karten (jeweils 'true', wenn ausgewählt)
     */
    public void changeSelectedCards() {
        for(int i = 0; i < 5; i++) {
            if(playerHand[i].getSelected()) playerHand[i].setRandomCard();
        }
        t.setText(Integer.toString(compareHands()));
    }
    
    /**
     * Gibt die X-Achsen-Skalierung zurück.
     * 
     * @return              Sklaierung der X-Achse
     */
    public double getScaleX() {
        return scaleX;
    }
    
    /**
     * Gibt die X-Achsen-Skalierung zurück.
     * 
     * @return              Sklaierung der Y-Achse
     */
    public double getScaleY() {
        return scaleY;
    }
}