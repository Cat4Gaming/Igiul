package menu;

import java.awt.*;
import javax.swing.*;

import main.MainFrame;

import java.io.*;

public class Options extends JPanel {
    final private MainFrame owner;
    private Font font;
    private String playerName;
    
    public Options(MainFrame owner) {
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

        JPanel userNameInputPanel = new JPanel(new BorderLayout());
        userNameInputPanel.setOpaque(false);
        userNameInputPanel.setMaximumSize(new Dimension(300, 50));
        userNameInputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel titleText = new JLabel("Igiul - Options", SwingConstants.CENTER);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}

        JLabel nameText = new JLabel("Name: ");
        nameText.setFont(font);
        nameText.setForeground(Color.WHITE); 

        JTextField playerNameField = new JTextField(playerName);
        playerNameField.setFont(font);
        playerNameField.setBackground(new Color(0, 60, 0));
        playerNameField.setForeground(Color.WHITE);

        JButton acceptButton = new JButton("Apply Changes");
        acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setFocusable(false);
        acceptButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        acceptButton.setBorder(BorderFactory.createEmptyBorder());
        acceptButton.setVerticalTextPosition(SwingConstants.CENTER);
        acceptButton.setHorizontalTextPosition(SwingConstants.CENTER);
        acceptButton.setFont(font);
        acceptButton.setContentAreaFilled(false);
        acceptButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acceptButton.addActionListener(event -> {
            playerName = playerNameField.getText();
            saveData();
        });

        JButton resetButton = new JButton("Reset Stats");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setFocusable(false);
        resetButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        resetButton.setBorder(BorderFactory.createEmptyBorder());
        resetButton.setVerticalTextPosition(SwingConstants.CENTER);
        resetButton.setHorizontalTextPosition(SwingConstants.CENTER);
        resetButton.setFont(font);
        resetButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        resetButton.setContentAreaFilled(false);
        resetButton.addActionListener(event -> {
            owner.deleteFile("saves/PicturePoker/save.dat");
        });

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
            SwingUtilities.invokeLater(() -> owner.showView(new Menu(owner)));
        });

        optionPanel.add(resetButton);
        optionPanel.add(backButton);

        add(titleText, BorderLayout.PAGE_START);
        add(optionPanel);
    }

    public void saveData() {
        try {
            FileOutputStream fos = new FileOutputStream("saves/options.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            Data dStor = new Data();
            dStor.playerName = playerName;
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
            Data dStor = (Data)ois.readObject();
            playerName = dStor.playerName;
            ois.close();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(playerName == null) playerName = "Player";
    }
}