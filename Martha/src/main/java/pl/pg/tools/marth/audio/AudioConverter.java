package pl.pg.tools.marth.audio;

import java.io.File;
import javaFlacEncoder.EncodingConfiguration;
import javaFlacEncoder.FLAC_FileEncoder;

/**
 *
 * @author gorskip
 */
public class AudioConverter {

    public static File toFlac(File inputFile, File outputFile) {
        FLAC_FileEncoder flacEncoder = new FLAC_FileEncoder();
        EncodingConfiguration ec = new EncodingConfiguration();
        ec.setChannelConfig(EncodingConfiguration.ChannelConfig.MID_SIDE);
        flacEncoder.setEncodingConfig(ec);
        flacEncoder.encode(inputFile, outputFile);
        return outputFile;
    }
}
