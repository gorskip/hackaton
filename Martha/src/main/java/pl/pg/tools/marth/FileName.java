package pl.pg.tools.marth;

/**
 *
 * @author gorskip
 */
public enum FileName {

    TEMP_WAVE_FILE("C:/development/temp.wav"),
    OUTPUT_FLAC_FILE("C:/development/recordOutput.flac");

    private final String filePath;

    private FileName(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

}
