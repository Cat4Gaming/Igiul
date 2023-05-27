package main;

import javax.sound.sampled.*;
import java.io.*;

public class SoundPlayer extends Thread{
    private Clip clip;
    private File musicPath;
    private boolean loop;

    public SoundPlayer(String musicPath) {
        this.musicPath = new File(musicPath);
    }

    public SoundPlayer(String musicPath, boolean loop) {
        this(musicPath);
        this.loop = loop;
    }
    
    public void playMusic() {
        try {
            if(musicPath.exists()) { 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                if(loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
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

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}