package menu;

import java.io.Serializable;

public class Data implements Serializable {
    String playerName, ipAddr;
    int portAddr;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setPIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public int getPortAddr() {
        return portAddr;
    }

    public void setPortAddr(int port) {
        portAddr = port;
    }
}