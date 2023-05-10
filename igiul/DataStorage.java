import java.io.Serializable;

public class DataStorage implements Serializable {
    int PPcoins, PPStars;
        
    /**
     * Setzt die Anzahl der Münzen für das PicturePoker Spiel.
     * 
     * @param   stars       Münzanzahl
     */
    public void setCoins(int coins) {
        PPcoins = coins;
    }
        
    /**
     * Setzt die Anzahl der Sterne für das PicturePoker Spiel.
     * 
     * @param   stars       Sternenanzahl
     */
    public void setStars(int stars) {
        PPStars = stars;
    }
        
    /**
     * Gibt die Anzahl der Münzen für das PicturePoker zurück.
     * 
     * @return              Münzanzahl
     */ 
    public int getCoins() {
        return PPcoins;
    }
       
    /**
     * Gibt die Anzahl der Sterne für das PicturePoker zurück.
     * 
     * @return              Sternenanzahl
     */ 
    public int getStars() {
        return PPStars;
    }
}