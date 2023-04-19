import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

/**
 * Die Klasse 'PPCard' ist eine Erweiterung der Klasse 'Button', die es erleichtern soll mit den Spielkarten für das PicturePoker-Spiel zu arbeiten.
 */
public class PPCard extends Button {
    private ImageView view;
    private int value;
    private boolean isHidden;
    
    /**
     * Setzt den Wert der Karte und zeigt sofort die Vorderseite der Karte.
     * 
     * @param    value       Wert der Karte
     */
    public PPCard(int value) {
        super();
        this.value = value;
        setHidden(false);
        super.setPadding(new Insets(-4, -4, -4, -4));
    }
    
    /**
     * Setzt den Wert der Karte und die Sichtbarkeit der Vorderseite.
     * 
     * @param   value       Wert der Karte
     * @param   isHidden    Bei verdeckter Karte 'true', 
     *                      und bei sichtbarer Vorderseite 'false'.
     */
    public PPCard(int value, boolean isHidden) {
        this(value);
        setHidden(isHidden);
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
    public boolean isHidden(){
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
        if(isHidden) view = new ImageView(new Image("assets/gfx/cards/hidden.png"));
        else view = new ImageView(new Image("assets/gfx/cards/" + value + ".png"));
        view.setFitHeight(200);
        view.setPreserveRatio(true);
        this.setGraphic(view);
    }
}