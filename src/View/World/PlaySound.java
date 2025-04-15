package View.World;

import Model.Farm;
import View.CustomButton;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Random;

public class PlaySound extends Thread{
    private boolean running = true;
    private static Clip cowSound; // Shared across all buttons
    private static Clip sheepSound; // Shared across all buttons
    public static int frameCount = 0;
    private Random random;
    private Farm farm;
    static {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    CustomButton.class.getResource("/Assets/sounds/cow_sound.wav"));
            cowSound = AudioSystem.getClip();
            cowSound.open(audioIn);

            audioIn = AudioSystem.getAudioInputStream(
                    CustomButton.class.getResource("/Assets/sounds/sheepSound.wav"));
            sheepSound = AudioSystem.getClip();
            sheepSound.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }
    public void pSound(Clip sound){
        if (sound != null) {
            sound.setFramePosition(0); // Rewind to start
            sound.start();
        }
    }

    public PlaySound(Farm farm){
        this.farm = farm;
        random = new Random();
    }

    @Override
    public void run() {

        while (running) {
            try {
                frameCount++;
                if(frameCount == Integer.MAX_VALUE) frameCount = 0;
                if(frameCount == 137 && random.nextDouble() < 0.2) {
                    pSound(cowSound);
                }

                if(frameCount % 97 == 0 && random.nextDouble() < 0.2) {
                    pSound(sheepSound);
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
