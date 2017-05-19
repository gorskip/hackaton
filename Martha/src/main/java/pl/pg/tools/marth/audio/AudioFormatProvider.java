package pl.pg.tools.marth.audio;

import javax.sound.sampled.AudioFormat;

/**
 *
 * @author gorskip
 */
public class AudioFormatProvider {

    public static AudioFormat getAudioFormat() {
        float sampleRate = 24000;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }
}
