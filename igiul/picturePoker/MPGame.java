package picturePoker;

import java.awt.*;
import javax.swing.*;
import main.*;

public class MPGame extends main.MPGame {
    private boolean isServer;
    private MPServer seCli;
    
    public MPGame(main.MainFrame owner, boolean isServer, String opponent, int startCoins) {
        this.isServer = isServer;
    }

    public void setServer(MPServer seCli) {
        this.seCli = seCli;
    }
    
    public void createGUI() {
    }

    public void recieveMsg(String msg) {

    }
}