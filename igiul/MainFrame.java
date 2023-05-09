import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private int width, height;
    private JPanel viewPanel;
    
    /**
     * Erstellt das sogenannte 'MainFrame' mit einen Titel, den man hier festlegen muss. Zudem wird die Breite und Höhe des Bildschirms ermittelt.
     * 
     * @param   title   Titel des Programms
     */
    public MainFrame(String title) {
        super(title);
        width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        createGUI();
    }
    
    /**
     * Legt die erste Startszene (hier: 'Menu') fest, es werden auch wichtige Elemente für einen funktionierenden Vollbildmodus festgelegt.
     */
    public void createGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setMinimumSize(new Dimension(width, height));
        setUndecorated(true);
        viewPanel = new JPanel(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        showView(new Menu(this));
        pack();
    }
    
    /**
     * Diese Funktion kann verwendet werden, um eine Szene zu wechseln oder auch zu 'updaten', wenn die ausführende Klasse, welche im vorgesehenen Fall ein 'JPanel' ist, sich selbst als Parameter übergitbt.
     * 
     * @param   panel   Szene eines Programms
     */
    public void showView(JPanel panel) {
        viewPanel.removeAll();
        viewPanel.add(panel, BorderLayout.CENTER);
        viewPanel.revalidate();
        viewPanel.repaint();
    }
    
    /**
     * Gibt eine zufällige Zahl (als int) zwischen zwei Werten, die man mit 'min' und 'max' festlegen kann, aus.
     * 
     * @param   min     kleinste Zahl, die ausgegeben werden kann
     * @param   max     größte Zahl, die ausgegeben werden kann
     * @return          zufällig generierte Zahl (int)
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     * Gibt die Breite des Bildschirms, auf den das Programm läuft, aus.
     * 
     * @return          Bildschirmbreite
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gibt die Höhe des Bildschirms, auf den das Programm läuft, aus.
     * 
     * @return          Bildschirmhöhe
     */
    public int getHeight() {
        return height;
    }
}