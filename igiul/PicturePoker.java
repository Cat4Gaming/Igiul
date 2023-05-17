import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class PicturePoker extends JPanel {
    final private MainFrame owner;
    private int width, height, selectedCards, coins, stars, betCoins;
    private static int[] deck, playerValue, computerValue; 
    private PPCard[] playerHand, computerHand;
    private JButton drawButton;
    private JLabel coinsLabel, starLabel, betCoinsLabel;
    private Font font;
    private boolean firstTime;
    
    /**
     * Hier wird das PicturePoker-Spiel erzeugt und der 'Besitzer' wird festgelegt, sowie die Auflösung des Bildschirms, und somit die Skalierfähigkeit der einzelnen Bildelemente.
     * 
     * @param   MainFrame   MainFrame (Besitzer)
     */
    public PicturePoker(MainFrame owner) {
        super();
        this.owner = owner;
        this.width = owner.getWidth();
        this.height = owner.getHeight();
        createGUI();
    }
    private void createGUI() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(26f);
        } catch(IOException| FontFormatException e) {}
        loadGame();
        if(firstTime == false) {
            firstTime = true;
            coins = 10;
            stars = 0;
        }
        computerValue = new int[6];
        playerValue = new int[6];
        setBackground(new Color(0, 153, 0));
        setBounds(0, 0, width, height);
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(0, 153, 0));
        centerPanel.setLayout(new BorderLayout());
            JPanel centerTopPanel = new JPanel();
            centerTopPanel.setLayout(new BorderLayout());
            centerTopPanel.setBackground(new Color(0, 153, 155));
                    betCoinsLabel = new JLabel("Bet Coins: " + betCoins, SwingConstants.CENTER);
                    betCoinsLabel.setFont(font);
                    betCoinsLabel.setBackground(new Color(0, 153, 0));
                    centerTopPanel.add(betCoinsLabel, BorderLayout.LINE_START);
                JPanel betPanel = new JPanel();
                betPanel.setBackground(new Color(0, 153, 0));
                    JButton betButton = new JButton("Bet");
                    betButton.setFont(font);
                    betButton.addActionListener(event -> {
                        if(betCoins != 5) {
                            if(coins > 0) {
                                coins--;
                                coinsLabel.setText("Coins: " + coins + "         ");
                                betCoins++;
                                betCoinsLabel.setText("Bet Coins: " + betCoins);
                            }
                        }
                    });
                    betPanel.add(betButton);
                centerTopPanel.add(betPanel, BorderLayout.PAGE_END);
            centerPanel.add(centerTopPanel, BorderLayout.PAGE_START);
            
            JLabel winStat = new JLabel("", SwingConstants.CENTER);
            centerPanel.add(winStat, BorderLayout.CENTER);
            
        add(centerPanel, BorderLayout.CENTER);
        JPanel topBarPanel = new JPanel();
        topBarPanel.setBackground(new Color(0, 153, 0));
        topBarPanel.setLayout(new BorderLayout());
            JButton menuButton = new JButton("Menu");
            menuButton.setFont(font);
            menuButton.addActionListener(event -> {
                SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
            });
            topBarPanel.add(menuButton, BorderLayout.LINE_START);
            JPanel centerTop = new JPanel();
            centerTop.setLayout(new BorderLayout());
            centerTop.setBackground(new Color(0, 153, 0));
                coinsLabel = new JLabel("Coins: " + coins + "         ");
                coinsLabel.setFont(font);
                centerTop.add(coinsLabel, BorderLayout.LINE_START);
                starLabel = new JLabel("Stars: " + stars);
                starLabel.setFont(font);
                centerTop.add(starLabel, BorderLayout.CENTER);
            topBarPanel.add(centerTop, BorderLayout.CENTER);
            drawButton = new JButton("Hold");
            drawButton.setFont(font);
            winStat.setFont(font);
            drawButton.addActionListener(event -> {
                if(selectedCards == -1) {
                    resetPlayingField();
                    winStat.setText("");
                } else {
                    if(selectedCards != 0) {
                        changeSelectedCards();
                    }
                    saveGame();
                    createCardLists();
                    replaceComputerCards();
                    sortCards();
                    selectedCards = -1;
                    drawButton.setText("New Round");
                    for(int i = 0; i < 5; i++) {
                        computerHand[i].setHidden(false);
                    }
                    if(compareHands() == 1) {
                        winStat.setText("You won!");
                        cashoutCoins();
                        stars++;
                    }
                    else if(compareHands() == -1) {
                        winStat.setText("You lost!");
                        if(stars != 0) stars--;
                    } else {
                        winStat.setText("Draw!");
                        coins = coins + betCoins;
                    }
                    starLabel.setText("Stars: " + stars);
                    saveGame();
                    betCoinsLabel.setText("");
                }
            });
            topBarPanel.add(drawButton, BorderLayout.LINE_END);
        add(topBarPanel, BorderLayout.PAGE_START);
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(new Color(0, 153, 0));
        instructionPanel.setLayout(new BorderLayout());
            JLabel cardvalue = new JLabel();
            cardvalue.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cardvalue.png").getImage().getScaledInstance(width/3, height/12, Image.SCALE_DEFAULT)));
            instructionPanel.add(cardvalue, BorderLayout.PAGE_START);
            JLabel cardcombovalue = new JLabel();
            cardcombovalue.setIcon(new ImageIcon(new ImageIcon("assets/gfx/cardcombovalue.png").getImage().getScaledInstance(width/3, (int)(height/1.5), Image.SCALE_DEFAULT)));
            instructionPanel.add(cardcombovalue, BorderLayout.CENTER);
        add(instructionPanel, BorderLayout.LINE_START);
        deck = new int[6];
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
            computerValue[i] = 0;
            playerValue[i] = 0;
        }
        minBetCoins();
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(new Color(0, 153, 0));
        gamePanel.setLayout(new BorderLayout());
            JPanel computerHandPanel = new JPanel();
            computerHandPanel.setBackground(new Color(0, 153, 0));
            JPanel playerHandPanel = new JPanel();
            playerHandPanel.setBackground(new Color(0, 153, 0));
                for(int i = 0; i < 5; i++) {
                    computerHand[i] = new PPCard(true, this);
                    computerHandPanel.add(computerHand[i].getPPCard());
                    playerHand[i] = new PPCard(false, this);
                    playerHandPanel.add(playerHand[i].getPPCard());
                }
            gamePanel.add(computerHandPanel, BorderLayout.PAGE_START);
            gamePanel.add(playerHandPanel, BorderLayout.PAGE_END);
            
        add(gamePanel, BorderLayout.LINE_END);
    }
    
    /**
     * Setzt die minimale Anzahl an Münzen.
     */
    public void minBetCoins() {
        betCoins = (stars / 5) + 1;
        coins = coins - betCoins;
        coinsLabel.setText("Coins: " + coins + "         ");
        if(betCoins > 5) betCoins = 5;
        betCoinsLabel.setText("Bet Coins: " + betCoins);
        betCoinsLabel.setFont(font);
    }
    
    /**
     * Zahlt die Münzen aus.
     */
    public void cashoutCoins() {
        coins = coins + (betCoins * handValue(playerValue));
        coinsLabel.setText("Coins: " + coins + "         ");
    }
    
    /**
     * Setzt das Spielfeld zum Anfang zurück, wobei die bisher gewonnene Münzanzahl und Sternanzahl nicht beeinträchtigt wird.
     */
    public void resetPlayingField() {
        selectedCards = 0;
        drawButton.setText("Hold");
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
        }
        for(int i = 0; i < 5; i++){
            computerHand[i].setHidden(true);
            computerHand[i].setRandomCard();
            playerHand[i].setRandomCard();
        }
        minBetCoins();
    }
    
    /**
     * Erstellt Listen der Kartenhäufigkeiten beider Spielerhände, diese werden in diversen Funktionen benötigt.
     */
    public void createCardLists() {
        for(int i = 0; i < 6; i++) {
            playerValue[i] = 0;
            computerValue[i] = 0;
        }
        for(int i = 0; i < 5; i++) {
            playerValue[playerHand[i].getValue()]++;
            computerValue[computerHand[i].getValue()]++;
        }
    }
    
    /**
     * Sortiert die Karten nach ihrer Häufigkeit und ihrer Wertigkeit, wobei links das Beste ist.
     */
    public void sortCards() {
        int tmp = 0;
        for(int i = 5; i > 0; i--) {
            for(int y = 5; y >= 0; y--) {
                if(playerValue[y] == i) {
                    for(int j = 0; j < i; j++) {
                        playerHand[tmp].setValue(y);
                        tmp++;
                    }
                }
            }
        }
        tmp = 0;
        for(int i = 5; i > 0; i--) {
            for(int y = 5; y >= 0; y--) {
                if(computerValue[y] == i) {
                    for(int j = 0; j < i; j++) {
                        computerHand[tmp].setValue(y);
                        tmp++;
                    }
                }
            }
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
        if(handValue(playerValue) > handValue(computerValue)) return 1;
        if(handValue(playerValue) < handValue(computerValue)) return -1;
        if(handValue(playerValue) >= 4) {
            if(playerHand[0].getValue() > computerHand[0].getValue()) return 1;
            return -1;
        }
        if(playerHand[0].getValue() > computerHand[0].getValue()) return 1;
        if(playerHand[0].getValue() < computerHand[0].getValue()) return -1;
        if(playerHand[3].getValue() > computerHand[3].getValue() && handValue(playerValue) != 2) return 1;
        if(playerHand[3].getValue() < computerHand[3].getValue() && handValue(playerValue) != 2) return -1;
        return 0;
    }
    
    /**
     * Berechnet den Wert der übergebenen 'Hand' und gibt diesen aus.
     * 
     * @param   value       Array einer 'Hand', dessen Wert ausgegeben werden soll.
     * @return              Wert der zuvor übergebenen 'Hand'
     */
    public int handValue(int[] value) {
        for(int i = 0; i < 6; i++) {
            if(value[i] == 5) return 16;
            if(value[i] == 4) return 8;
        }
        for(int i = 0; i < 6; i++) {
            for(int y = 0; y < 6; y++) {
                if(value[i] == 3 && value[y] == 2) return 6;
            }
        }
        for(int i = 0; i < 6; i++) {
            for(int y = 0; y < 6; y++) {
                if(value[i] == 2 && value[y] == 2 && i != y) return 3;
            }
        }
        for(int i = 0; i < 6; i++) {
            if(value[i] == 3) return 4;
            if(value[i] == 2) return 2;
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
            if(playerHand[i].getSelected()) {
                deck[playerHand[i].getValue()]++;
                playerHand[i].setRandomCard();
            }
        }
    }
    
    /**
     * Gibt den MainFrame, also den 'Besitzer' aus
     * 
     * @return              MainFrame (Besitzer)
     */
    public MainFrame getMainFrame() {
        return owner;
    }
    
    /**
     * Erhöht, falls gerade nicht verhindert ('selectedCards'-Wert gleich -1), die Anzahl der ausgewählten Karten.
     */
    public void addSelCards() {
        if(selectedCards != -1) {
            selectedCards++;
            drawButton.setText("Draw");
        }
    }
    
    /**
     * Erniedrigt, falls gerade nicht verhindert ('selectedCards'-Wert gleich -1), die Anzahl der ausgewählten Karten.
     */
    public void delSelCards() {
        if(selectedCards != -1) {
            selectedCards--;
            if(selectedCards == 0) drawButton.setText("Hold");
        }
    }
    
    /**
     * Gibt die Anzahl der ausgewählten Karten des Spielers aus.
     * 
     * @return              Anzahl der ausgewählten Karten des Spielers
     */
    public int getSelCards() {
        return selectedCards;
    }
    
    /**
     * Ersetzt maximal 4 Karten des Computer-Spielers, wenn von einer jeweiligen Kartenart nur eine Vorhanden ist, wodurch sein Spiel eine klare und durchaus simple Strategie folgt.
     */
    public void replaceComputerCards() {
        int[] computerValue = new int[6];
        for(int i = 0; i < 5; i++) {
            int tmp = computerHand[i].getValue();
            computerValue[tmp] = computerValue[tmp] + 1;
        }
        int rep = 0;
        for(int i = 0; i < 5 && rep < 4; i++) {
            if(computerValue[i] == 1) {
                deck[computerHand[i].getValue()]++;
                computerHand[i].setRandomCard();
                rep++;
            }
        }
    }
    
    /**
     * Speichert alle benötigten Variablen in die 'save.dat'-Datei.
     */
    public void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("saves/save.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            DataStorage dStor = new DataStorage();
            dStor.PPcoins = coins;
            dStor.PPStars = stars;
            dStor.PPfirstTime = firstTime;
            oos.writeObject(dStor);
            oos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Lädt alle benötigten Variablen von der 'save.dat'-Datei.
     */
    public void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("saves/save.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            DataStorage dStor = (DataStorage)ois.readObject();
            coins = dStor.PPcoins;
            stars = dStor.PPStars;
            firstTime = dStor.PPfirstTime;
            ois.close();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}