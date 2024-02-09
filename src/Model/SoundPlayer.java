package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class SoundPlayer {

    private static SoundPlayer instance;
    private boolean mute;
    private Clip clip; // Declare the Clip variable
   
    private SoundPlayer() {
        // Private constructor to prevent instantiation
    }
    public void toggleMute() {
        mute = !mute;
        if (mute) {
            stopBackgroundMusic();
        } else {
            playBackgroundMusic();
        }
    }

    public float getVolume() {
        if (clip != null) {
            return ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).getValue();
        }
        return 0;
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }
    }
    public static synchronized SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    private void playSound(String soundPath) {
        try {
            // Get the URL of the sound file within the source folder
            URL soundFileUrl = SoundPlayer.class.getResource(soundPath);
            if (soundFileUrl != null) {
                // Convert URL to File
                File localSoundFile = new File(soundFileUrl.toURI());

                try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(localSoundFile)) {
                    clip = AudioSystem.getClip(); // Initialize the Clip
                    clip.open(audioIn);
                    if (clip.isOpen()) {
                        clip.start();
                    }
                }
            } else {
                System.out.println("Sound file not found.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException ex) {
            ex.printStackTrace();
            // Handle exceptions appropriately (e.g., show an error message)
        }
    }
    
    private void playBackgroundMusic(String musicPath) {
        try {
            URL musicFileUrl = SoundPlayer.class.getResource(musicPath);
            if (musicFileUrl != null) {
                // Convert URL to File
                File localMusicFile = new File(musicFileUrl.toURI());

                try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(localMusicFile)) {
                    clip = AudioSystem.getClip(); // Initialize the Clip
                    clip.open(audioIn);
                    if (clip.isOpen()) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
            } else {
                System.out.println("Background music file not found.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException ex) {
            ex.printStackTrace();
            // Handle exceptions appropriately (e.g., show an error message)
        }
    }
    
    
    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    
    public void playBackgroundMusic() {
        playBackgroundMusic("/Assest/background.wav");
    }
    
    public void playSoundHover() {
        playSound("/Assest/hover.wav");
    }

    public void playSoundClick() {
        playSound("/Assest/clicks.wav");
    }

    public void playSoundMarks() {
        playSound("/Assest/coin.wav");
    }

    public void error() {
        playSound("/Assest/error.wav");
    }

    public void failed() {
        playSound("/Assest/Game Over.wav");
    }

    public void wrong() {
        playSound("/Assest/wrong.wav");
    }
    
    public void selectmain() {
        playSound("/Assest/selectmain.wav");
    }
    
    public void timer() {
        playSound("/Assest/timerg.wav");
    }
    
    public void counter() {
        playSound("/Assest/counter.wav");
    }
}
