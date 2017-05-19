package pl.pg.tools.marth;

import java.io.File;
import pl.pg.tools.marth.audio.AudioPlayer;
import pl.pg.tools.marth.voice.Voice;

/**
 *
 * @author gorskip
 */
public class Main {

    public static void main(String[] args) throws Exception {
        recordVoice();
//        audioToText();
//        playTempVoice();
    }

    private static void recordVoice() {
        final SoundRecorder recorder = new SoundRecorder();
        File recordFile = new File(FileName.OUTPUT_FLAC_FILE.getFilePath());
        recorder.record(recordFile, 5000);
    }

    private static void playTempVoice() {
        Voice voice = new Voice(new File(FileName.TEMP_WAVE_FILE.getFilePath()), 8000);
        AudioPlayer.playSound(voice);
    }

    private static void audioToText() throws Exception {
        new SpeechGoogleClient().speech(new File(FileName.OUTPUT_FLAC_FILE.getFilePath()), "pl-PL");
    }
}
