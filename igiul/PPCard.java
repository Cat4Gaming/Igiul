import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PPCard {
    private int value;
    private boolean isHidden, selected;
    private PicturePoker PP;
    private JButton card;
    
    /**
     * Erstellen einer zufälligen Karte, die verdeckt sein kann.
     * 
     * @param isHidden      Bei verdeckter Karte 'true', 
     *                      und bei sichtbarer Vorderseite 'false'.
     * @param PP            PicturePoker-Klasse
     */
    public PPCard(boolean isHidden, PicturePoker PP) {
        this.isHidden = isHidden;
        this.PP = PP;
        card = new JButton();
        card.addActionListener(event -> {if(!isHidden) {
                if(selected) {
                    selected = false;
                    PP.delSelCards();
                } else {
                    if(PP.getSelCards() != -1) {
                        selected = true;
                        PP.addSelCards();
                    }
                }
                setHidden(this.isHidden);
            }
        });
        setRandomCard();
        setHidden(isHidden);
    }
    
    /**
     * Gibt die Spielkarte aus.
     * 
     * @return              Spielkarte (PPCard)
     */
    public JButton getPPCard(){
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
        if(isHidden) card.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cards/hidden.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
        else card.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cards/" + selected + "/" + value + ".png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
        card.setBorder(BorderFactory.createEmptyBorder());
        card.setContentAreaFilled(false);
    }
    
    /**
     * Nimmt eine neue zufällige und mögliche Karte aus dem Deck.
     */
    public void setRandomCard() {
        int tmp = PP.getMainFrame().randInt(0, 5);
        value = tmp;
        if(!PP.addPossibleCard(tmp)) {
            setRandomCard();
        }
        selected = false;
        setHidden(isHidden);
    }
}