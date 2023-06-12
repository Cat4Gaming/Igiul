package picturePoker;

import java.awt.*;
import javax.swing.*;
import main.*;
import java.io.*;

public class MPMenu extends JPanel {
    final private MainFrame owner;
    private Font font;
    private int port;
    private String ip, playerName;
    private JLabel errorText, errorTextL2;
    
    public MPMenu(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }

    private void createGUI() {
        loadData();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        setBackground(new Color(0, 90, 0));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
        errorPanel.setOpaque(false);

        JPanel portPanel = new JPanel(new BorderLayout());
        portPanel.setOpaque(false);
        portPanel.setMaximumSize(new Dimension(300, 50));
        portPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JPanel ipPanel = new JPanel(new BorderLayout());
        ipPanel.setOpaque(false);
        ipPanel.setMaximumSize(new Dimension(300, 50));
        ipPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JPanel userNameInputPanel = new JPanel(new BorderLayout());
        userNameInputPanel.setOpaque(false);
        userNameInputPanel.setMaximumSize(new Dimension(300, 50));
        userNameInputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel titleText = new JLabel("Igiul - Multiplayer", SwingConstants.CENTER);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}

        JTextField playerNameField = new JTextField(playerName);
        playerNameField.setFont(font);
        playerNameField.setBackground(new Color(0, 60, 0));
        playerNameField.setForeground(Color.WHITE);

        JTextField portAsServer = new JTextField("" + port);
        portAsServer.setFont(font);
        portAsServer.setBackground(new Color(0, 60, 0));
        portAsServer.setForeground(Color.WHITE);

        JTextField ipField = new JTextField(ip);
        ipField.setFont(font);
        ipField.setBackground(new Color(0, 60, 0));
        ipField.setForeground(Color.WHITE);

        JLabel ipText = new JLabel("IP: ");
        ipText.setFont(font);
        ipText.setForeground(Color.WHITE); 

        JLabel portText = new JLabel("Port: ");
        portText.setFont(font);
        portText.setForeground(Color.WHITE); 

        JLabel nameText = new JLabel("Name: ");
        nameText.setFont(font);
        nameText.setForeground(Color.WHITE); 

        JLabel noteText = new JLabel("Note: The IPAddress is irrelevant for hosting a game.");
        noteText.setFont(font);
        noteText.setForeground(Color.WHITE); 
        noteText.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        
        errorText = new JLabel("");
        errorText.setFont(font);
        errorText.setForeground(new Color(255, 102, 120)); 
        errorText.setBorder(BorderFactory.createEmptyBorder(0, 5, -5, 5));

        errorTextL2 = new JLabel("");
        errorTextL2.setFont(font);
        errorTextL2.setForeground(new Color(255, 102, 120));
        errorTextL2.setBorder(BorderFactory.createEmptyBorder(-10, 5, 0, 5));

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        backButton.setContentAreaFilled(false);
        backButton.setFocusable(false);
        backButton.setVerticalTextPosition(SwingConstants.CENTER);
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
        backButton.setFont(font);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        backButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new menu.Menu(owner)));
        });
        
        JButton startAsServer = new JButton("Host Game");
        startAsServer.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsServer.setFocusable(false);
        startAsServer.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        startAsServer.setBorder(BorderFactory.createEmptyBorder());
        startAsServer.setVerticalTextPosition(SwingConstants.CENTER);
        startAsServer.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsServer.setFont(font);
        startAsServer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startAsServer.setContentAreaFilled(false);
        startAsServer.addActionListener(event -> {
            playerName = playerNameField.getText();
            ip = ipField.getText();
            port = Integer.parseInt(portAsServer.getText());
            saveData();
            SwingUtilities.invokeLater(() -> owner.showView(new MPRoom(owner, true)));
        });

        JButton startAsClient = new JButton("Join Game");
        startAsClient.setAlignmentX(Component.CENTER_ALIGNMENT);
        startAsClient.setFocusable(false);
        startAsClient.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        startAsClient.setBorder(BorderFactory.createEmptyBorder());
        startAsClient.setVerticalTextPosition(SwingConstants.CENTER);
        startAsClient.setHorizontalTextPosition(SwingConstants.CENTER);
        startAsClient.setFont(font);
        startAsClient.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startAsClient.setContentAreaFilled(false);
        startAsClient.addActionListener(event -> {
            playerName = playerNameField.getText();
            ip = ipField.getText();
            port = Integer.parseInt(portAsServer.getText());
            saveData();
            SwingUtilities.invokeLater(() -> owner.showView(new MPRoom(owner, false)));
        });

        portPanel.add(portText, BorderLayout.LINE_START);
        portPanel.add(portAsServer, BorderLayout.CENTER);

        ipPanel.add(ipText, BorderLayout.LINE_START);
        ipPanel.add(ipField, BorderLayout.CENTER);

        userNameInputPanel.add(nameText, BorderLayout.LINE_START);
        userNameInputPanel.add(playerNameField, BorderLayout.CENTER);

        optionPanel.add(userNameInputPanel);
        optionPanel.add(portPanel);
        optionPanel.add(ipPanel);
        optionPanel.add(startAsServer);
        optionPanel.add(startAsClient);
        optionPanel.add(backButton);

        errorPanel.add(errorText);
        errorPanel.add(errorTextL2);
        errorPanel.add(noteText);

        add(titleText, BorderLayout.PAGE_START);
        add(optionPanel, BorderLayout.CENTER);
        add(errorPanel, BorderLayout.PAGE_END);
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream("saves/options.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            menu.Data dStor = new menu.Data();
            dStor.setPlayerName(playerName);
            dStor.setPIpAddr(ip);
            dStor.setPortAddr(port);
            oos.writeObject(dStor);
            oos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadData() {
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

    public void errorCode(String msg) {
        switch(msg) {
            case "e501":
                errorText.setText("Can't start server on port: " + port);
                errorTextL2.setText("Please try using a diffrent port or try restarting the game.");
                break;
            case "e502":
                errorText.setText("Lost connection with other player.");
                errorTextL2.setText("Please check your internet connection and try again.");
                break;
            case "e503":
                errorText.setText("Can not connect to host.");
                errorTextL2.setText("Please check the port, the ip-Adress and you internet connection and try again.");
        }
    }
}