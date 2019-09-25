/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPvZ;

import java.io.*;
import javax.sound.sampled.*;

/**
 * plays sounds in game
 *
 * @author Parham
 */
public class SoundPlayer {

    private Clip clip;

    public SoundPlayer(String action) {
        try {
            // Open an audio input stream.
            File file = new File("sounds//" + action + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void repetivePlay() {
        clip.start();
        clip.loop(100);
    }

    public void stop() {
        clip.stop();
    }

    public boolean isPlaying() {
        return clip.isRunning();

    }
}
