import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new main.MainFrame("Igiul").setVisible(true);
        });
    }
}