import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

public class Player extends Thread{
    private Clip clip;
    
    public void playMusic(String musicLoc) {
        try {
            File musicPath = new File(musicLoc);
            if(musicPath.exists()) { 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void stopMusic() {
        clip.stop();
    }
    
    @Override
    public void run() {
        playMusic("assets/sfx/backgroundMusic/1.wav");
        while(true) {
            if(clip.isRunning() == false) {
                playMusic("assets/sfx/backgroundMusic/1.wav");
            }
        }
    }
}