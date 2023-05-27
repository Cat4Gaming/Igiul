package main;

import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;

public class MainFrame extends JFrame {
    private int screenWidth, screenHeight;
    private JPanel viewPanel;
    public SoundPlayer backgroundPlayer;
    
    /**
     * @param appName       Name der Anwendung
     */
    public MainFrame(String appName) {
        super(appName);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        createGUI();
        backgroundPlayer = new SoundPlayer("assets/sfx/backgroundMusic/" + randInt(0, 3) + ".wav", true);
        backgroundPlayer.start();
    }
    
    private void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setMinimumSize(new Dimension(screenWidth, screenHeight));
        setUndecorated(true);
        viewPanel = new JPanel(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        showView(new Menu(this));
        pack();
    }
    
    /**
     * @param filePath      zu löschende(-r) Datei(-pfad)
     */
    public void deleteFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param newViewPanel  zu anzeigendes JPanel
     */
    public void showView(JPanel newViewPanel) {
        viewPanel.removeAll();
        viewPanel.add(newViewPanel, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }
    
    /**
     * @param min           Kleinster Wert der zufälligen Zahl
     * @param max           Größter Wert der zufälligen Zahl
     * @return              zufällige Zahl zwischen 'min' und 'max'
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * @return              Bildschirmbreite
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @return              Bildschirmhöhe
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    public ImageIcon resizedImageIcon(String filePath, int imageWidth, int imageHeight) {
        ImageIcon ii = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT));
        return ii;
    }
}