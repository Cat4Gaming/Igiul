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

public class PicturePoker extends Game {
    private int[] deck; 
    private PPCard[] playerHand, computerHand;

    public Scene giveScene(Stage window, Scene MainMenu, double scaleX, double scaleY) {
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
    
    public void setRandPlayerCard(int position) {
        int tmp = Menu.randInt(0, 5);
        playerHand[position] = new PPCard(Menu.randInt(0, 5));
        if(deck[tmp] == 0) {setRandPlayerCard(position);}
        else {deck[tmp] = deck[tmp] - 1;}
        
    }
    
    public void setRandComputerCard(int position) {
        int tmp = Menu.randInt(0, 5);
        computerHand[position] = new PPCard(Menu.randInt(0, 5));
        if(deck[tmp] == 0) {setRandPlayerCard(position);}
        else {deck[tmp] = deck[tmp] - 1;}
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