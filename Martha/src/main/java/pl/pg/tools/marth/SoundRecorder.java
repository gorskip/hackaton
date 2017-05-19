package pl.pg.tools.marth;

import java.io.*;
import javax.sound.sampled.*;
import pl.pg.tools.marth.audio.AudioConverter;
import pl.pg.tools.marth.audio.AudioFormatProvider;

/**
 * A sample program is to demonstrate how to record sound in Java author:
 * www.codejava.net
 */
public class SoundRecorder {

    private final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private TargetDataLine line;

    public File record() {
        return record(new File(FileName.OUTPUT_FLAC_FILE.getFilePath()), 8000);
    }

    public File record(long recordTimeInMillis) {
        return record(new File(FileName.OUTPUT_FLAC_FILE.getFilePath()), recordTimeInMillis);
    }

    public File record(File outputFile, final long recordTimeInMillis) {
        Thread stopper = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(recordTimeInMillis);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                finish();
            }
        });
        stopper.start();
        File tempWavFile = new File(FileName.TEMP_WAVE_FILE.getFilePath());
        start(tempWavFile);
        return AudioConverter.toFlac(tempWavFile, outputFile);
    }

    private void start(File outputFile) {
        try {
            AudioFormat format = AudioFormatProvider.getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");
            AudioSystem.write(ais, fileType, outputFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    private void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }

}
