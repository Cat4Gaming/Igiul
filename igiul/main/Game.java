package main;

import javax.swing.JPanel;

public abstract class Game extends JPanel {
    public abstract void recieveMsg(String msg);
    public abstract void createGUI();
    public abstract void saveGame();
    public abstract void loadGame();
}