import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PicturePoker extends JPanel {
    final private MainFrame owner;
    private int width, height;
    private static int[] deck; 
    private PPCard[] playerHand, computerHand;
    
    public PicturePoker(MainFrame owner) {
        super();
        this.owner = owner;
        this.width = owner.getWidth();
        this.height = owner.getHeight();
        createGUI();
    }
    
    private void createGUI() {
        setBounds(0, 0, width, height);
        setLayout(new BorderLayout());
        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
            JButton menuButton = new JButton("Menu");
            menuButton.addActionListener(event -> {
                SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
            });
            topBarPanel.add(menuButton, BorderLayout.LINE_START);
            JButton drawButton = new JButton("Draw");
            drawButton.addActionListener(event -> {
                changeSelectedCards();
            });
            topBarPanel.add(drawButton, BorderLayout.CENTER);
        add(topBarPanel, BorderLayout.PAGE_START);
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BorderLayout());
            JLabel cardvalue = new JLabel();
            cardvalue.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cardvalue.png").getImage().getScaledInstance(width/3, height/12, Image.SCALE_DEFAULT)));
            instructionPanel.add(cardvalue, BorderLayout.PAGE_START);
            JLabel cardcombovalue = new JLabel();
            cardcombovalue.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cardcombovalue.png").getImage().getScaledInstance(width/3, (int)(height/1.5), Image.SCALE_DEFAULT)));
            instructionPanel.add(cardcombovalue, BorderLayout.CENTER);
        add(instructionPanel, BorderLayout.LINE_START);
        deck = new int[6];
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        resetPlayingField();
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(Color.BLUE);
            JPanel computerHandPanel = new JPanel();
            JPanel playerHandPanel = new JPanel();
                for(int i = 0; i < 5; i++) {
                    computerHandPanel.add(computerHand[i].getPPCard()); 
                    playerHandPanel.add(playerHand[i].getPPCard());
                }
            gamePanel.add(computerHandPanel, BorderLayout.PAGE_START);
            gamePanel.add(playerHandPanel, BorderLayout.PAGE_END);
        add(gamePanel, BorderLayout.LINE_END);
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
            computerHand[i] = new PPCard(true, this);
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
        if(handValue(playerValue) > handValue(computerValue)) return 1;
        if(handValue(playerValue) < handValue(computerValue)) return -1;
        for(int i = 5; i >= 0; i--) {
            if(playerValue[i] == 5 || playerValue[i] == 4 || playerValue[i] == 3) {
                if(playerValue[i] != computerValue[i]) return 1;
            }
            if(computerValue[i] == 5 || computerValue[i] == 4 || computerValue[i] == 3) {
                if(playerValue[i] != computerValue[i]) return -1;
            }
        }
        for(int i = 5; i >= 0; i--) {
            if(playerValue[i] == 2 && computerValue[i] != 2) return 1;
            if(playerValue[i] != 2 && computerValue[i] == 2) return -1;
            if(playerValue[i] == 2 && computerValue[i] == 2) {
                for(int y = 5; y >= 0; y--) {
                    if(y != i) {
                        if(playerValue[y] == 2 && computerValue[y] != 2) return 1;
                        if(playerValue[y] != 2 && computerValue[y] == 2) return -1;
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
     * Ändert alle ausgewählten Karten in neue von dem KartenDeck.
     * 
     * @param   selCards    Array von allen Karten (jeweils 'true', wenn ausgewählt)
     */
    public void changeSelectedCards() {
        for(int i = 0; i < 5; i++) {
            if(playerHand[i].getSelected()) playerHand[i].setRandomCard();
        }
    }
    
    /**
     * Gibt das Hauptmenu zurück
     * 
     * @return              Hauptmenu
     */
    public MainFrame getMainFrame() {
        return owner;
    }
}