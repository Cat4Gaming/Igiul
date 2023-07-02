package main;

import javax.swing.JPanel;

public abstract class MPGame extends JPanel {
    public abstract void recieveMsg(String msg);
    public abstract void createGUI();
}