package pl.pg.tools.marth.voice;

import java.io.File;

/**
 *
 * @author gorskip
 */
public class Voice {

    private final File audioFile;
    private final long durationInMillis;

    public Voice(File audioFile, long durationInMillis) {
        this.audioFile = audioFile;
        this.durationInMillis = durationInMillis;
    }

    public File getAudioFile() {
        return audioFile;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

}
