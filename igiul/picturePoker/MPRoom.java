package picturePoker;

import main.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class MPRoom extends main.MPGame{
    final private MainFrame owner;
    private MPServer seCli;
    private Font font;
    private String playerName, ip;
    private int port, startBetCoins;
    private boolean isServer;
    private JButton opponent, start;
    
    public MPRoom(MainFrame owner, boolean isServer) {
        this.owner = owner;
        this.isServer = isServer;
        startBetCoins = 10;
        loadGame();
        createGUI();
        if(isServer) {seCli = new MPServer(port, this);}
        else {seCli = new MPClient(ip, port, this);}
        seCli.start();
    }

    @Override
    public void recieveMsg(String msg) {
        System.out.println("in>" + msg);
        String sData = "";
        if(msg.contains("pName:")) {
            sData = msg.replace("pName:", "");
            msg = "pName:";
        }
        if(msg.contains("sbc:")) {
            sData = msg.replace("sbc:", "");
            msg = "sbc:";
        }
        switch(msg) {
            case "e501":
            case "e502":
            case "e503":
                MPMenu menu = new MPMenu(owner);
                menu.errorCode(msg);
                SwingUtilities.invokeLater(() -> owner.showView(menu));
                break;
            case "connSuccess":
                if(isServer == false) seCli.sendMessage(msg);
                seCli.sendMessage("pName:" + playerName);
                break;
            case "pName:":
                opponent.setText(sData);
                opponent.setForeground(Color.WHITE);
                start.setEnabled(true);
                break;
            case "startGame":
                MPGame game = new MPGame(owner, isServer, opponent.getText(), startBetCoins, seCli);
                seCli.setOwner(game);
                SwingUtilities.invokeLater(() -> owner.showView(game));
                break;
            case "sbc:":
                startBetCoins = Integer.parseInt(sData);
            default: 
                break;
        }
    }

    public void createGUI() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        setLayout(new BorderLayout());
        setBackground(new Color(0, 90, 0));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);

        JLabel titleText = new JLabel("Igiul - Multiplayer", SwingConstants.CENTER);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}

        JButton nameLabel = new JButton("" + this.playerName);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setContentAreaFilled(false);
        nameLabel.setFocusable(false);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBorder(BorderFactory.createEmptyBorder());

        opponent = new JButton("'Waiting for player to join...'");
        opponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        opponent.setContentAreaFilled(false);
        opponent.setFocusable(false);
        opponent.setFont(font);
        opponent.setForeground(new Color(255, 102, 120));
        opponent.setBorder(BorderFactory.createEmptyBorder(-15, 0, 0, 0));

        JButton portLabel = new JButton("Port: " + port);
        portLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        portLabel.setContentAreaFilled(false);
        portLabel.setFocusable(false);
        portLabel.setFont(font);
        portLabel.setForeground(Color.WHITE);
        portLabel.setBorder(BorderFactory.createEmptyBorder());

        JButton ipLabel = new JButton("IP: " + ip);
        ipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ipLabel.setContentAreaFilled(false);
        ipLabel.setFocusable(false);
        ipLabel.setFont(font);
        ipLabel.setForeground(Color.WHITE);
        ipLabel.setBorder(BorderFactory.createEmptyBorder());
        ipLabel.setBorder(BorderFactory.createEmptyBorder(-15, 0, 0, 0));
        
        start = new JButton("Start Game");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        start.setContentAreaFilled(false);
        start.setFocusable(false);
        start.setVerticalTextPosition(SwingConstants.CENTER);
        start.setHorizontalTextPosition(SwingConstants.CENTER);
        start.setFont(font);
        start.setEnabled(false);
        start.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        start.addActionListener(event -> {
            seCli.sendMessage("sbc:" + startBetCoins);
            MPGame game = new MPGame(owner, isServer, opponent.getText(), startBetCoins, seCli);
            seCli.setOwner(game);
            seCli.sendMessage("startGame");
            SwingUtilities.invokeLater(() -> owner.showView(game));
        });

        JButton backButton = new JButton("Leave");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        backButton.setContentAreaFilled(false);
        backButton.setFocusable(false);
        backButton.setVerticalTextPosition(SwingConstants.CENTER);
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
        backButton.setFont(font);
        backButton.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        backButton.addActionListener(event -> {
            if(opponent.getText() != "'Waiting for player to join...'") {seCli.sendMessage("exit");}
            seCli.stopSeCli();
            SwingUtilities.invokeLater(() -> owner.showView(new MPMenu(owner)));
        });
        
        optionPanel.add(nameLabel);
        optionPanel.add(opponent);
        optionPanel.add(portLabel);

        if(!isServer) {
            opponent.setText("'Connecting to host...'");
            optionPanel.add(ipLabel);
        } else {
            optionPanel.add(start);
        }

        optionPanel.add(backButton);

        add(titleText, BorderLayout.PAGE_START);
        add(optionPanel, BorderLayout.CENTER);
    }

    public void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("saves/options.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            menu.Data dStor = (menu.Data)ois.readObject();
            playerName = dStor.getPlayerName();
            ip = dStor.getIpAddr();
            port = dStor.getPortAddr();
            ois.close();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(playerName == null) playerName = "Player";
        if(ip == null) ip = "0";
        if(port == 0) port = 19256;
    }
    
    public void saveGame(){}
}