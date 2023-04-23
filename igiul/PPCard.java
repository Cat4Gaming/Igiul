import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

/**
 * Die Klasse 'PPCard' ist eine Erweiterung der Klasse 'Button', die es erleichtern soll mit den Spielkarten für das PicturePoker-Spiel zu arbeiten.
 */
public class PPCard extends Thread{
    private ImageView view;
    private int value;
    private boolean isHidden;
    private Button card;
        
    /**
     * Setzt den Wert der Karte und die Sichtbarkeit der Vorderseite.
     * 
     * @param   value       Wert der Karte
     * @param   isHidden    Bei verdeckter Karte 'true', 
     *                      und bei sichtbarer Vorderseite 'false'.
     */
    public PPCard(boolean isHidden) {
        card = new Button();
        this.isHidden = isHidden;
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
        view.setFitWidth(180);
        view.setPreserveRatio(true);
        card.setGraphic(view);
        card.setPadding(new Insets(-5, -5, -5, -5));
    }
    
    /**
     * Nimmt eine neue zufällige und mögliche Karte aus dem Deck.
     */
    public void setRandomCard() {
        int tmp = Menu.randInt(0, 5);
        value = tmp;
        if(PicturePoker.addPossibleCard(tmp)) setHidden(isHidden);
        else setRandomCard();
    }
}