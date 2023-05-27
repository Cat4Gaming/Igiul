package picturePoker;

import java.awt.*;
import javax.swing.*;

public class MPGame extends Game {
    private boolean isServer;
    
    public MPGame(main.MainFrame owner, boolean isServer) {
        super(owner);
        this.isServer = isServer;
    }
    
    @Override
    public void createGUI() {
        super.createGUI();
        JPanel panel = new JPanel(new BorderLayout());
        JButton text = new JButton("text");
        panel.add(text);
        if(isServer) {
            
        }
        super.setCenterTopBarPanel(panel);
    }
}