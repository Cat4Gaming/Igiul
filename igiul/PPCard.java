import java.awt.*;
import javax.swing.*;

public class PPCard extends JButton{
    private int cardValue;
    private boolean isHidden, isSelected;
    private PicturePoker PP;
    
    /**
     * @param isHidden      setzen ob die Karte versteckt ist
     * @param picturePoker  JPanel in dem die Karte enthalten ist
     */
    public PPCard(boolean isHidden, PicturePoker picturePoker) {
        this.isHidden = isHidden;
        this.PP = picturePoker;
        
        setNewRandomCard();
        setHidden(isHidden);
        setFocusable(false);

        addActionListener(event -> {
            buttonClickAction();
            gfxUpdate();
        });
    }
    
    private void buttonClickAction() {
        if(isHidden) return;
        if(isSelected) {
            isSelected = false;
            PP.delSelCards();
            return;
        }
        if(PP.getSelCards() != -1) {
            isSelected = true;
            PP.addSelCards();
        }
    }

    /**
     * @return              der Wert der Karte
     */
    public int getCardValue() {
        return cardValue;
    }
    
    /**
     * @return              ob die Karte ausgewählt ist oder nicht
     */
    public boolean getSelectedStatus() {
        return isSelected;    
    }
    
    /**
     * @return              ob die Karte versteckt ist oder nicht
     */
    public boolean getIsHidden(){
        return isHidden;
    }

    /**
     * @param isHidden      setzen ob die Karte versteckt ist
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
        gfxUpdate();
    }
    
    /**
     * Lässt das Bild der Karten neu laden
     */
    private void gfxUpdate() {
        if(isHidden) setIcon(new ImageIcon(new ImageIcon("assets/gfx/cards/hidden.png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
        else setIcon(new ImageIcon(new ImageIcon("assets/gfx/cards/" + isSelected + "/" + cardValue + ".png").getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }
    
    /**
     * Lässt eine zufällige Karte aus dem Kartendeck ziehen
     */
    public void setNewRandomCard() {
        cardValue = PP.getMainFrame().randInt(0, 5);
        if(PP.addPossibleCard(cardValue)) {
            isSelected = false;
            gfxUpdate();
        } else {
            setNewRandomCard();
        }
    }
    
    /**
     * @param newCardValue  zu setzender Kartenwert
     */
    public void setValue(int newCardValue) {
        cardValue = newCardValue;
        gfxUpdate();
    }
    
    /**
     * @param isSelected    Auswahlstatus der Karte setzen
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}