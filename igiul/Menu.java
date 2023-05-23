import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Menu extends JPanel {
    final private MainFrame owner;
    private int screenWidth, screenHeight;
    private Font font;
    
    public Menu(MainFrame owner) {
        super();
        this.owner = owner;
        this.screenWidth = owner.getScreenWidth();
        this.screenHeight = owner.getScreenHeight();
        createGUI();
    }
    
    private void createGUI() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Darumadrop_One/DarumadropOne-Regular.ttf")).deriveFont(128f);
        } catch(IOException| FontFormatException e) {}
        setBounds(0, 0, screenWidth, screenHeight);
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
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFocusable(false);
        startButton.setIcon(owner.resizedImageIcon("assets/gfx/middlebutton.png", 200, 50));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setVerticalTextPosition(SwingConstants.CENTER);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setFont(font);
        startButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(event -> {
            SwingUtilities.invokeLater(() -> owner.showView(new PicturePoker(owner)));
        });

        JButton resetButton = new JButton("Reset Game");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setFocusable(false);
        resetButton.setIcon(owner.resizedImageIcon("assets/gfx/middlebutton.png", 200, 50));
        resetButton.setBorder(BorderFactory.createEmptyBorder());
        resetButton.setVerticalTextPosition(SwingConstants.CENTER);
        resetButton.setHorizontalTextPosition(SwingConstants.CENTER);
        resetButton.setFont(font);
        resetButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        resetButton.setContentAreaFilled(false);
        resetButton.addActionListener(event -> {
            owner.deleteFile("saves/PicturePoker/save.dat");
        });

        JButton closeButton = new JButton("Close Game");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setFocusable(false);
        closeButton.setIcon(owner.resizedImageIcon("assets/gfx/middlebutton.png", 200, 50));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setVerticalTextPosition(SwingConstants.CENTER);
        closeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        closeButton.setFont(font);
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(event -> {
            owner.dispose();
        });

        optionPanel.add(startButton);
        optionPanel.add(resetButton);
        optionPanel.add(closeButton);

        add(titleText, BorderLayout.PAGE_START);
        add(optionPanel);
    }
}