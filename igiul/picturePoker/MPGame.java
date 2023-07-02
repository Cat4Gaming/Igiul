package picturePoker;

import java.awt.*;
import javax.swing.*;

public class MPGame extends main.MPGame {
    private boolean isServer;

    public MPGame(main.MainFrame owner, boolean isServer, String opponent, int startCoins, main.MPServer seCli) {
        this.isServer = isServer;
    }
    
    @Override
    public void recieveMsg(String msg){
    
    }

    @Override
    public void createGUI() {
        
    }
}