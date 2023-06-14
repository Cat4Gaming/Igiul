package picturePoker;

import java.awt.*;
import javax.swing.*;
import main.*;
import java.io.*;

public class MPGame extends main.MPGame {
    final private MainFrame owner;
    private boolean isServer;
    private MPServer seCli;
    private Font font;
    private JLabel coinsLabel, starLabel, betCoinsLabel;
    private int width, height, selectedCards, coins, stars, betCoins;
    private static int[] deck, playerValue, computerValue; 
    private Card[] playerHand, computerHand;
    private JButton drawButton;
    private JButton betButton;
    
    public MPGame(main.MainFrame owner, boolean isServer, String opponent, int startCoins, MPServer seCli) {
        this.isServer = isServer;
        this.seCli = seCli;
        this.owner = owner;
        this.width = owner.getScreenWidth();
        this.height = owner.getScreenHeight();
        createGUI();
    }
    
    public void createGUI() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}
        computerValue = new int[6];
        playerValue = new int[6];
        deck = new int[6];
        computerHand = new Card[5];
        playerHand = new Card[5];
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
        coinsLabel.setIcon(MainFrame.resizedImageIcon("assets/gfx/coin.png", 45, 45));
        coinsLabel.setFont(font);
        coinsLabel.setForeground(Color.WHITE); 
    
        starLabel = new JLabel("" + stars);
        starLabel.setIcon(MainFrame.resizedImageIcon("assets/gfx/coin.png", 45, 45));
        starLabel.setFont(font);
        starLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        starLabel.setForeground(Color.WHITE); 
    
        JLabel cardvalue = new JLabel();
        cardvalue.setIcon(MainFrame.resizedImageIcon("assets/gfx/cardvalue.png", 640, 90));
    
        JLabel cardcombovalue = new JLabel();
        cardcombovalue.setIcon(MainFrame.resizedImageIcon("assets/gfx/cardcombovalue.png", 640, 720));
    
        betButton = new JButton("");
        betButton.setFocusable(false);
        betButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/bet.png", 100, 100));
        betButton.setBorder(BorderFactory.createEmptyBorder());
        betButton.setContentAreaFilled(false);
        betButton.addActionListener(event -> {
            
        });
    
        JButton menuButton = new JButton("Menu");
        menuButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 120, 50));
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusable(false);
        menuButton.setVerticalTextPosition(SwingConstants.CENTER);
        menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        menuButton.setFont(font);
        menuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new menu.Menu(owner)));
        });
    
        drawButton = new JButton("Hold");
        drawButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 190, 60));
        drawButton.setBorder(BorderFactory.createEmptyBorder());
        drawButton.setContentAreaFilled(false);
        drawButton.setFocusable(false);
        drawButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        drawButton.setFont(font);
        drawButton.setVerticalTextPosition(SwingConstants.CENTER);
        drawButton.setHorizontalTextPosition(SwingConstants.CENTER);
        drawButton.addActionListener(event -> {
            
        });
    
        for(int i = 0; i < 5; i++) {
            
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
    
        instructionPanel.add(cardvalue, BorderLayout.PAGE_START);
        instructionPanel.add(cardcombovalue, BorderLayout.CENTER);
    
        gamePanel.add(playerHandPanel, BorderLayout.PAGE_END);
        gamePanel.add(drawButtonPanel, BorderLayout.CENTER);
        gamePanel.add(computerHandPanel, BorderLayout.PAGE_START);
    
        add(centerPanel, BorderLayout.CENTER);
        add(instructionPanel, BorderLayout.LINE_START);
        add(gamePanel, BorderLayout.LINE_END);

    }

    public void recieveMsg(String msg) {

    }
}