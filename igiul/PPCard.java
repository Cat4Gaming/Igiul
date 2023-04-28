import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Die Klasse 'PPCard' ist eine Erweiterung der Klasse 'Button', die es erleichtern soll mit den Spielkarten für das PicturePoker-Spiel zu arbeiten.
 */
public class PPCard extends Thread{
    private ImageView view;
    private int value;
    private boolean isHidden, selected;
    private Button card;
    private PicturePoker PP;
        
    /**
     * Setzt den Wert der Karte und die Sichtbarkeit der Vorderseite.
     * Setzt zudem die Funktion der Karte beim anklicken.
     * 
     * @param   PP          übergiebt PicturePoker Klasse
     * @param   isHidden    Bei verdeckter Karte 'true', 
     *                      und bei sichtbarer Vorderseite 'false'.
     */
    public PPCard(boolean isHidden, PicturePoker PP) {
        card = new Button();
        card.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isHidden) {
                    if(selected) {
                        card.setTranslateY(0);
                        selected = false;
                    } else {
                        card.setTranslateY(-50 * PP.getScaleY());
                        selected = true;
                    }
                }
            }
        });
        this.isHidden = isHidden;
        this.PP = PP;
        start();
    }
    
    /**
     * Damit man mehrere Spielkarten zugleich geladen werden.
     */
    @Override
    public void run() {
        setRandomCard();
        setHidden(isHidden);
    }
    
    /**
     * Gibt die Spielkarte aus.
     * 
     * @return              Spielkarte (PPCard)
     */
    public Button getPPCard(){
        return card;
    }
    
    /**
     * Gibt den Wert der Spielkarte zurück.
     * 
     * @return              Wert der Karte
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Gibt zurück, ob die Karte aktuell ausgewählt ist oder nicht.
     * 
     * @return              Auswahl-Status der Karte
     */
    public boolean getSelected() {
        return selected;    
    }
    
    /**
     * Gibt zurück, ob die Karte aktuell verdeckt ist oder nicht.
     * 
     * @return              bei 'true' ist die Karte verdeckt 
     *                      und bei 'false' ist die Kartevorderseite sichtbar
     */
    public boolean getIsHidden(){
        return isHidden;
    }
    
    /**
     * Setzt ob die Karte verdeckt sein soll oder nicht.
     * 
     * @param   isHidden    Bei verdeckter Karte 'true', 
     *                      und bei sichtbarer Vorderseite 'false'.
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
        if(isHidden) view = new ImageView(new Image("assets/gfx/cards/hidden.png", true));
        else view = new ImageView(new Image("assets/gfx/cards/" + value + ".png", true));
        view.setFitWidth(360 * PP.getScaleX());
        view.setPreserveRatio(true);
        card.setGraphic(view);
        card.setPadding(new Insets(-12 * PP.getScaleY(), -12 * PP.getScaleX(), -12 * PP.getScaleY(), -12 * PP.getScaleX()));
        card.setTranslateY(0);
        selected = false;
    }
    
    /**
     * Nimmt eine neue zufällige und mögliche Karte aus dem Deck.
     */
    public void setRandomCard() {
        int tmp = PP.getMenu().randInt(0, 5);
        value = tmp;
        if(!PP.addPossibleCard(tmp)) {
            setRandomCard();
        }
        setHidden(isHidden);
    }
}