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
    private int[] deck, playerHand, computerHand;

    public Scene giveScene(Stage window, Scene MainMenu, double scaleX, double scaleY) {
        Button back = new Button("Zurück");
        //gehe zur Menu-Szene zurück
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                window.setScene(MainMenu);
                window.setFullScreen(true);
            }
        });
        //Erstelle Spielfeld
        deck = new int[6];
        playerHand = new int[5];
        computerHand = new int[5];
        resetPlayingField();

        Button ph0 = new Button();
        ImageView vph0 = new ImageView(new Image("assets/gfx/cards/" + playerHand[0] + ".png"));
        vph0.setFitHeight(200);
        vph0.setPreserveRatio(true);
        ph0.setGraphic(vph0);
        ph0.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ph1 = new Button();
        ImageView vph1 = new ImageView(new Image("assets/gfx/cards/" + playerHand[1] + ".png"));
        vph1.setFitHeight(200);
        vph1.setPreserveRatio(true);
        ph1.setGraphic(vph1);
        ph1.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ph2 = new Button();
        ImageView vph2 = new ImageView(new Image("assets/gfx/cards/" + playerHand[2] + ".png"));
        vph2.setFitHeight(200);
        vph2.setPreserveRatio(true);
        ph2.setGraphic(vph2);
        ph2.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ph3 = new Button();
        ImageView vph3 = new ImageView(new Image("assets/gfx/cards/" + playerHand[3] + ".png"));
        vph3.setFitHeight(200);
        vph3.setPreserveRatio(true);
        ph3.setGraphic(vph3);
        ph3.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ph4 = new Button();
        ImageView vph4 = new ImageView(new Image("assets/gfx/cards/" + playerHand[4] + ".png"));
        vph4.setFitHeight(200);
        vph4.setPreserveRatio(true);
        ph4.setGraphic(vph4);
        ph4.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ch0 = new Button();
        ImageView vch0 = new ImageView(new Image("assets/gfx/cards/" + computerHand[0] + ".png"));
        vch0.setFitHeight(200);
        vch0.setPreserveRatio(true);
        ch0.setGraphic(vch0);
        ch0.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ch1 = new Button();
        ImageView vch1 = new ImageView(new Image("assets/gfx/cards/" + computerHand[1] + ".png"));
        vch1.setFitHeight(200);
        vch1.setPreserveRatio(true);
        ch1.setGraphic(vch1);
        ch1.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ch2 = new Button();
        ImageView vch2 = new ImageView(new Image("assets/gfx/cards/" + computerHand[2] + ".png"));
        vch2.setFitHeight(200);
        vch2.setPreserveRatio(true);
        ch2.setGraphic(vch2);
        ch2.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ch3 = new Button();
        ImageView vch3 = new ImageView(new Image("assets/gfx/cards/" + computerHand[3] + ".png"));
        vch3.setFitHeight(200);
        vch3.setPreserveRatio(true);
        ch3.setGraphic(vch3);
        ch3.setPadding(new Insets(-5, -5, -5, -5));
        
        Button ch4 = new Button();
        ImageView vch4 = new ImageView(new Image("assets/gfx/cards/" + computerHand[4] + ".png"));
        vch4.setFitHeight(200);
        vch4.setPreserveRatio(true);
        ch4.setGraphic(vch4);
        ch4.setPadding(new Insets(-5, -5, -5, -5));
        
        HBox computerCards = new HBox(25);
        HBox playerCards = new HBox(25);
        playerCards.getChildren().addAll(ph0, ph1, ph2, ph3, ph4);
        VBox main = new VBox(20);
        main.getChildren().addAll(back, computerCards, playerCards);
        return new Scene(main);
    }
    //Setze eine zufällige Karte aus dem Karten-Deck als neue Spielkarte des Spielers an einer gewissen Position.
    public void setRandPlayerCard(int position) {
        playerHand[position] = Menu.randInt(0, 5);
        if(deck[playerHand[position]] == 0) {setRandPlayerCard(position);}
        else {deck[playerHand[position]] = deck[playerHand[position]] - 1;}
    }
    //Setze eine zufällige Karte aus dem Karten-Deck als neue Spielkarte des Computers an einer gewissen Position.
    public void setRandComputerCard(int position) {
        computerHand[position] = Menu.randInt(0, 5);
        if(deck[computerHand[position]] == 0) {setRandPlayerCard(position);}
        else {deck[computerHand[position]] = deck[computerHand[position]] - 1;}
    }
    //Setze ein komplett neues Deck mit allen Karten und gebe sofort den Spieler und Computer gewisse Karten.
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