import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;

public class Menu extends JPanel {
    final private MainFrame owner;
    private int width, height;
    
    /**
     * Hier wird das Menü erzeugt und der 'Besitzer' wird festgelegt, sowie die Auflösung des Bildschirms, und somit die Skalierfähigkeit der einzelnen Bildelemente.
     * 
     * @param   MainFrame   MainFrame (Besitzer)
     */
    public Menu(MainFrame owner) {
        super();
        this.owner = owner;
        this.width = owner.getWidth();
        this.height = owner.getHeight();
        createGUI();
    }
    
    private void createGUI() {
        setBounds(0, 0, width, height);
        setLayout(new BorderLayout());
            JLabel title = new JLabel("Igiul", SwingConstants.CENTER);
            add(title, BorderLayout.PAGE_START);
            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
                JButton start = new JButton("Start Game");
                start.setAlignmentX(Component.CENTER_ALIGNMENT);
                start.addActionListener(event -> {
                    SwingUtilities.invokeLater(() -> owner.showView(new PicturePoker(owner)));
                });
                optionPanel.add(start);
                JButton settings = new JButton("Reset Game");
                settings.setAlignmentX(Component.CENTER_ALIGNMENT);
                settings.addActionListener(event -> {
                    deleteFile("saves/save.dat");
                });
                optionPanel.add(settings);
                JButton close = new JButton("Close Game");
                close.setAlignmentX(Component.CENTER_ALIGNMENT);
                close.addActionListener(event -> {
                    owner.dispose();
                });
                optionPanel.add(close);
            add(optionPanel);
    }
    
    private void deleteFile(String file_path) {
        Path path = Paths.get(file_path);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}