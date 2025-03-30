package View;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Objects;

public class CustomButton extends JButton {
    private static Clip clickSound; // Shared across all buttons

    // Static initializer to load sound once
    static {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    CustomButton.class.getResource("/Assets/sounds/buttonPress.wav"));
            clickSound = AudioSystem.getClip();
            clickSound.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }

    public CustomButton(String text) {
        super(text);
        setupButton();
        addSoundEffect();
    }

    private void setupButton() {
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(Color.GRAY);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.GRAY);
            }
        });
    }

    private void addSoundEffect() {
        addActionListener(e -> playClickSound());
    }

    private void playClickSound() {
        System.out.println("aaaaaaaaaaaaaaa");
        if (clickSound != null) {
            System.out.println("bbbbbbbbbb");
            clickSound.setFramePosition(0); // Rewind to start
            clickSound.start();
        }
    }
}