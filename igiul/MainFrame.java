import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private int width, height;
    private JPanel viewPanel;
    
    public MainFrame(String title) {
        super(title);
        width = 1920;
        height = 1080;
        createGUI();
    }
    
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
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}