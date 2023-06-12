package main;

import java.io.*;
import java.net.*;

public class MPServer extends Thread{
    private ServerSocket serverSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;
    private main.MPGame game;
    private int port;
    private boolean running;

    public MPServer(int port, main.MPGame game) {
        this.game = game;
        this.port = port;
    }

    @Override
    public void run() {
        running = true;
        init();
    }
    
    public void init() {
        try {
            serverSocket = new ServerSocket(port);
            Socket connection = serverSocket.accept();
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("connSuccess");
            do {
                try {
                    message = (String)in.readObject();
                    game.recieveMsg(message);   
                    if(message == "exit") stopSeCli();            
                } catch(ClassNotFoundException e) {}
              
            } while(running);
        } catch (IOException e) {
            System.out.println(e);
            if(e.getMessage().indexOf("Address already in use: bind") != -1) game.recieveMsg("e501");
            if(e.getMessage().indexOf("Connection reset") != -1) game.recieveMsg("e502");
        } finally {
            try {    
                in.close();
                out.close();
                serverSocket.close();
            } catch(IOException e) {}
        }
    }
    
    public void stopSeCli() {
        running = false;
    }
    
    public void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("out>" + msg);
        } catch(IOException e) {
        }
    }
    
    public void setOwner(main.MPGame owner) {
        game = owner;
    }
}