package menu;

import java.awt.*;
import javax.swing.*;
import main.MainFrame;
import java.io.*;

public class Menu extends JPanel {
    final private MainFrame owner;
    private Font font;
    
    public Menu(MainFrame owner) {
        super();
        this.owner = owner;
        createGUI();
    }
    
    private void createGUI() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        setBounds(0, 0, owner.getScreenWidth(), owner.getScreenHeight());
        setLayout(new BorderLayout());
        setBackground(new Color(0, 90, 0));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setOpaque(false);

        JLabel titleText = new JLabel("Igiul", SwingConstants.CENTER);
        titleText.setFont(font);
        titleText.setForeground(Color.WHITE); 
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 5, 200, 5));
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(32f);
        } catch(IOException| FontFormatException e) {}

        JButton startButton = new JButton("Singleplayer");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFocusable(false);
        startButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setVerticalTextPosition(SwingConstants.CENTER);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setFont(font);
        startButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new picturePoker.Game(owner)));
        });

        JButton startMultiButton = new JButton("Multiplayer");
        startMultiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startMultiButton.setFocusable(false);
        startMultiButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        startMultiButton.setBorder(BorderFactory.createEmptyBorder());
        startMultiButton.setVerticalTextPosition(SwingConstants.CENTER);
        startMultiButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startMultiButton.setFont(font);
        startMultiButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startMultiButton.setContentAreaFilled(false);
        startMultiButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new picturePoker.MPMenu(owner)));
        });

        JButton resetButton = new JButton("Options");
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
            SwingUtilities.invokeLater(() -> owner.showView(new Options(owner)));
        });

        JButton closeButton = new JButton("Close");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setFocusable(false);
        closeButton.setIcon(MainFrame.resizedImageIcon("assets/gfx/middlebutton.png", 300, 50));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setVerticalTextPosition(SwingConstants.CENTER);
        closeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        closeButton.setFont(font);
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(event -> {
            owner.backgroundPlayer.stopMusic();
            owner.dispose();
            System.exit(0);
        });

        optionPanel.add(startButton);
        optionPanel.add(startMultiButton);
        optionPanel.add(resetButton);
        optionPanel.add(closeButton);

        add(titleText, BorderLayout.PAGE_START);
        add(optionPanel);
    }
}