import java.awt.*;
import javax.swing.*;
import java.io.*;

public class PicturePoker extends JPanel {
    final private MainFrame owner;
    private int width, height, selectedCards, coins, stars, betCoins;
    private static int[] deck, playerValue, computerValue; 
    private PPCard[] playerHand, computerHand;
    private JButton drawButton;
    private JLabel coinsLabel, starLabel, betCoinsLabel;
    private Font font;
    private boolean firstTime;
    private JButton betButton;
    
    public PicturePoker(MainFrame owner) {
        super();
        this.owner = owner;
        this.width = owner.getScreenWidth();
        this.height = owner.getScreenHeight();
        createGUI();
    }
    
    private void createGUI() {
        loadGame();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}
        computerValue = new int[6];
        playerValue = new int[6];
        deck = new int[6];
        computerHand = new PPCard[5];
        playerHand = new PPCard[5];
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
            computerValue[i] = 0;
            playerValue[i] = 0;
        }
    
        setBackground(new Color(0, 90, 0));
        setBounds(0, 0, width, height);
        setLayout(new BorderLayout());
    
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());
    
        JPanel centerTopPanel = new JPanel();
        centerTopPanel.setOpaque(false);
        centerTopPanel.setLayout(new BorderLayout());
    
        JPanel betPanel = new JPanel();
        betPanel.setOpaque(false);
    
        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());
    
        JPanel coinsStarsPanel = new JPanel();
        coinsStarsPanel.setOpaque(false);
        coinsStarsPanel.setPreferredSize(new Dimension(525, 50));
        coinsStarsPanel.setLayout(new BorderLayout());
    
        JPanel instructionPanel = new JPanel();
        instructionPanel.setOpaque(false);
        instructionPanel.setLayout(new BorderLayout());
    
        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BorderLayout());
    
        JPanel computerHandPanel = new JPanel();
        computerHandPanel.setOpaque(false);
    
        JPanel playerHandPanel = new JPanel();
        playerHandPanel.setOpaque(false);
    
        JPanel computerContainerPanel = new JPanel(new BorderLayout());
        computerContainerPanel.setOpaque(false);
    
        Box centerBox = Box.createVerticalBox();
    
        Box drawButtonPanel = new Box(BoxLayout.Y_AXIS);
        drawButtonPanel.setOpaque(false);
    
        betCoinsLabel = new JLabel("Bet Coins: " + betCoins, SwingConstants.CENTER);
        betCoinsLabel.setFont(font);
        betCoinsLabel.setForeground(Color.WHITE); 
    
        JLabel winStat = new JLabel("", SwingConstants.CENTER);
        winStat.setFont(font);
        winStat.setForeground(Color.WHITE); 
    
        coinsLabel = new JLabel("" + coins);
        coinsLabel.setIcon(owner.resizedImageIcon("assets/gfx/coin.png", 45, 45));
        coinsLabel.setFont(font);
        coinsLabel.setForeground(Color.WHITE); 
    
        starLabel = new JLabel("" + stars);
        starLabel.setIcon(owner.resizedImageIcon("assets/gfx/star.png", 50, 50));
        starLabel.setFont(font);
        starLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        starLabel.setForeground(Color.WHITE); 
    
        JLabel cardvalue = new JLabel();
        cardvalue.setIcon(owner.resizedImageIcon("assets/gfx/cardvalue.png", 640, 90));
    
        JLabel cardcombovalue = new JLabel();
        cardcombovalue.setIcon(owner.resizedImageIcon("assets/gfx/cardcombovalue.png", 640, 720));
    
        betButton = new JButton("");
        betButton.setFocusable(false);
        betButton.setIcon(owner.resizedImageIcon("assets/gfx/bet.png", 100, 100));
        betButton.setBorder(BorderFactory.createEmptyBorder());
        betButton.setContentAreaFilled(false);
        betButton.addActionListener(event -> {
            betButtonClickAction();
        });
    
        JButton menuButton = new JButton("Menu");
        menuButton.setIcon(owner.resizedImageIcon("assets/gfx/middlebutton.png", 120, 50));
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusable(false);
        menuButton.setVerticalTextPosition(SwingConstants.CENTER);
        menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        menuButton.setFont(font);
        menuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
        });
    
        drawButton = new JButton("Hold");
        drawButton.setIcon(owner.resizedImageIcon("assets/gfx/middlebutton.png", 190, 60));
        drawButton.setBorder(BorderFactory.createEmptyBorder());
        drawButton.setContentAreaFilled(false);
        drawButton.setFocusable(false);
        drawButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        drawButton.setFont(font);
        drawButton.setVerticalTextPosition(SwingConstants.CENTER);
        drawButton.setHorizontalTextPosition(SwingConstants.CENTER);
    
        drawButton.addActionListener(event -> {
            drawButtonClickAction(winStat);
            starLabel.setText("" + stars);
            betCoinsLabel.setText("Bet Coins: " + betCoins);
        });
    
        minBetCoins();
    
        for(int i = 0; i < 5; i++) {
            computerHand[i] = new PPCard(true, this);
            computerHandPanel.add(computerHand[i]);
            playerHand[i] = new PPCard(false, this);
            playerHandPanel.add(playerHand[i]);
        }
    
        centerBox.add(Box.createVerticalGlue());
        centerBox.add(coinsStarsPanel);
        centerBox.add(Box.createVerticalStrut(20));
        centerBox.add(centerTopPanel);
    
        coinsStarsPanel.add(coinsLabel, BorderLayout.CENTER);
        coinsStarsPanel.add(starLabel, BorderLayout.LINE_END);
    
        drawButtonPanel.add(Box.createVerticalGlue());
        drawButtonPanel.add(drawButton);
        drawButtonPanel.add(Box.createVerticalGlue());
    
        betPanel.add(betButton);
    
        centerTopPanel.add(betCoinsLabel, BorderLayout.PAGE_START);
        centerTopPanel.add(betPanel, BorderLayout.PAGE_END);
    
        centerPanel.add(centerBox, BorderLayout.PAGE_START);
        centerPanel.add(winStat, BorderLayout.CENTER);
    
        topBarPanel.add(coinsStarsPanel, BorderLayout.LINE_END);
        topBarPanel.add(menuButton, BorderLayout.LINE_START);
    
        instructionPanel.add(cardvalue, BorderLayout.PAGE_START);
        instructionPanel.add(cardcombovalue, BorderLayout.CENTER);
    
        gamePanel.add(playerHandPanel, BorderLayout.PAGE_END);
        gamePanel.add(drawButtonPanel, BorderLayout.CENTER);
        gamePanel.add(computerHandPanel, BorderLayout.PAGE_START);
    
        add(centerPanel, BorderLayout.CENTER);
        add(instructionPanel, BorderLayout.LINE_START);
        add(topBarPanel, BorderLayout.PAGE_START);
        add(gamePanel, BorderLayout.LINE_END);
    }

    private void betButtonClickAction() {
        if(betCoins != 5 && coins >0) {
            coins--;
            coinsLabel.setText("" + coins);
            betCoins++;
            betCoinsLabel.setText("Bet Coins: " + betCoins);
        }
    }

    private void drawButtonClickAction(JLabel winStat) {
        if(selectedCards == -1) {
            resetPlayingField();
            winStat.setText("");
            betButton.setEnabled(true);
            return;
        }
        if(selectedCards != 0) {
            changeSelectedCards();
        }
        createCardLists();
        replaceComputerCards();
        sortHands();
        selectedCards = -1;
        drawButton.setText("New Round");
        betButton.setEnabled(false);
        for(int i = 0; i < 5; i++) {
            computerHand[i].setHidden(false);
        }
        if(compareHands() == 1) {
            winStat.setText("You won!");
            cashoutCoins();
            stars++;
            saveGame();
            return;
        }
        if(compareHands() == -1) {
            winStat.setText("You lost!");
            if(stars != 0) stars--;
            saveGame();
            return;
        }
        winStat.setText("Draw!");
        coins = coins + betCoins;
        betCoins = 0;
    }
    
    public void minBetCoins() {
        betCoins = (stars / 5) + 1;
        coins = coins - betCoins;
        coinsLabel.setText("" + coins);
        if(betCoins > 5) betCoins = 5;
        betCoinsLabel.setText("Bet Coins: " + betCoins);
    }
    
    public void cashoutCoins() {
        coins = coins + (betCoins * handValue(playerValue)) + betCoins;
        coinsLabel.setText("" + coins);
    }
    
    public void resetPlayingField() {
        selectedCards = 0;
        drawButton.setText("Hold");
        for(int i = 0; i < 6; i++) {
            deck[i] = 5;
        }
        for(int i = 0; i < 5; i++){
            computerHand[i].setHidden(true);
            computerHand[i].setNewRandomCard();
            playerHand[i].setNewRandomCard();
        }
        minBetCoins();
    }
    
    public void createCardLists() {
        for(int i = 0; i < 6; i++) {
            playerValue[i] = 0;
            computerValue[i] = 0;
        }
        for(int i = 0; i < 5; i++) {
            playerValue[playerHand[i].getCardValue()]++;
            computerValue[computerHand[i].getCardValue()]++;
        }
    }
    
    public void sortHands() {
        int pTmp = 0;
        int cTmp = 0;
        for(int i = 5; i > 0; i--) {
            for(int y = 5; y >= 0; y--) {
                if(playerValue[y] == i) {
                    for(int j = 0; j < i; j++) {
                        playerHand[pTmp].setValue(y);
                        pTmp++;
                    }
                }
                if(computerValue[y] == i) {
                    for(int j = 0; j < i; j++) {
                        computerHand[cTmp].setValue(y);
                        cTmp++;
                    }
                }
            }
        }
    }
    
    /**
     * @return              bei 1: Spieler gewinnt;
     *                      bei 0: Unentschieden;
     *                      bei -1: Computer gewinnt;
     */
    public int compareHands(){
        if(handValue(playerValue) > handValue(computerValue)) return 1;
        if(handValue(playerValue) < handValue(computerValue)) return -1;
        if(handValue(playerValue) >= 4) {
            if(playerHand[0].getCardValue() > computerHand[0].getCardValue()) return 1;
            return -1;
        }
        if(playerHand[0].getCardValue() > computerHand[0].getCardValue()) return 1;
        if(playerHand[0].getCardValue() < computerHand[0].getCardValue()) return -1;
        if(playerHand[3].getCardValue() > computerHand[3].getCardValue() && handValue(playerValue) != 2) return 1;
        if(playerHand[3].getCardValue() < computerHand[3].getCardValue() && handValue(playerValue) != 2) return -1;
        return 0;
    }
    
    /**
     * 
     * @param value         zu überprüfende Karten, welche zuvor schon gezählt wurden
     * @return              Wertigkeit des Kartendecks
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
     * @param cardValue     Kartenwert, der aus dem Kartenstapel genommen werden soll
     * @return              ob diese Karte noch im Kartenstapel existiert
     */
    public boolean addPossibleCard(int cardValue) {
        if(deck[cardValue] == 0) return false;
        else {
            deck[cardValue] = deck[cardValue] -1;
            return true;
        }
    }

    public MainFrame getMainFrame() {
        return owner;
    }
    
    public void changeSelectedCards() {
        for(int i = 0; i < 5; i++) {
            if(playerHand[i].getSelectedStatus()) {
                deck[playerHand[i].getCardValue()]++;
                playerHand[i].setNewRandomCard();
            }
        }
    }
    
    
    public void addSelCards() {
        if(selectedCards != -1) {
            selectedCards++;
            drawButton.setText("Draw");
        }
    }
    
    public void delSelCards() {
        if(selectedCards != -1) {
            selectedCards--;
            if(selectedCards == 0) drawButton.setText("Hold");
        }
    }
    
    /**
     * @return              Anzahl der gerade ausgewählten Karten
     */
    public int getSelCards() {
        return selectedCards;
    }
    
    /**
     * Ersetzt gewisse Karten des Computer-Spielers
     */
    public void replaceComputerCards() {
        int[] computerValue = new int[6];
        for(int i = 0; i < 5; i++) {
            int tmp = computerHand[i].getCardValue();
            computerValue[tmp] = computerValue[tmp] + 1;
        }
        int rep = 0;
        for(int i = 0; i < 5 && rep < 4; i++) {
            if(computerValue[i] == 1) {
                deck[computerHand[i].getCardValue()]++;
                computerHand[i].setNewRandomCard();
                rep++;
            }
        }
    }

    public MainFrame getOwner() {
        return owner;
    }
    
    /**
     * Speichert gewisse Daten, wie die Sternanzahl oder die Münzanzahl in einer '.dat'-Datei
     */
    public void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("saves/PicturePoker/save.dat");
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
        betCoins = 0;
    }
    
    /**
     * Lädt die gespeicherten Daten aus einer '.dat'-Datei
     */
    public void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("saves/PicturePoker/save.dat");
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
        if(firstTime == false) {
            firstTime = true;
            coins = 10;
            stars = 0;
        }
    }
}