package pl.pg.tools.marth.audio;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import pl.pg.tools.marth.voice.Voice;

/**
 *
 * @author gorskip
 */
public class AudioPlayer {

    public static void playSound(Voice voice) {
        playSound(voice.getAudioFile(), voice.getDurationInMillis());
    }

    public static synchronized void playSound(final File soundFile, long playingTimeInMillis) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        sleepForPlayingTime(playingTimeInMillis);

    }

    private static void sleepForPlayingTime(long playingTimeInMillis) {
        try {
            Thread.sleep(playingTimeInMillis);
        } catch (InterruptedException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
