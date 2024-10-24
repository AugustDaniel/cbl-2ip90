package game.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Utility class used to play a sound.
 * Because of Java API limitations only supports .WAV files.
 */
public final class SoundPlayer {

    /**
     * Play a sound from a file.
     * File should have .WAV extension.
     * @param file file to play sound from
     */
    public static void playSound(File file) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.loop(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
