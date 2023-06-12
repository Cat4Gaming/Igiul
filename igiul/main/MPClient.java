package main;

import java.io.*;
import java.net.*;

public class MPClient extends MPServer{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message, ip;
    private main.MPGame game;
    private int port;
    private boolean running;

    public MPClient(String ip, int port, main.MPGame game){
        super(port, game);
        this.ip = ip;
        this.port = port;
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        init();
    }

    @Override
    public void init() {
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            do {
                try {
                    message = (String)in.readObject();
                    game.recieveMsg(message);  
                    if(message == "exit") {
                        stopSeCli();    
                    }
                } catch(ClassNotFoundException e) {
                    System.out.println(e);
                }
            } while(running);
        } catch (IOException e) {
            System.out.println(e);
            if(e.getMessage().indexOf("Connection reset") != -1) game.recieveMsg("e502");
            if(e.getMessage().indexOf("Connection refused: connect") != -1) game.recieveMsg(("e503"));
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void stopSeCli() {
        running = false;
    }
    
    @Override
    public void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("out>" + msg);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void setOwner(main.MPGame owner) {
        game = owner;
    }
}