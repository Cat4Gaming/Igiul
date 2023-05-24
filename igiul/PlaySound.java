import javax.sound.sampled.*;
import java.io.*;

public class PlaySound extends Thread{
    private Clip clip;
    private File musicPath;

    public PlaySound(String musicPath) {
        this.musicPath = new File(musicPath);
    }
    
    public void playMusic() {
        try {
            if(musicPath.exists()) { 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(99999);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void stopMusic() {
        clip.stop();
    }
    
    @Override
    public void run() {
        playMusic();
    }
}