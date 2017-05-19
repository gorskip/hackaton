package pl.pg.tools.marth;

import com.google.cloud.speech.spi.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gorskip
 */
public class SpeechGoogleClient {

    public List<SpeechRecognitionAlternative> speech(File recordedFile, String language) throws IOException, Exception {

        List<SpeechRecognitionAlternative> resultAlternatives = new ArrayList<>();
        try (SpeechClient speech = SpeechClient.create()) {
            RecognitionConfig config = buildRecognitionConfig(language);
            RecognitionAudio audio = buildRecognitionAudio(recordedFile.toPath());
            RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                List<SpeechRecognitionAlternative> alternatives = result.getAlternativesList();
                resultAlternatives.addAll(alternatives);
                for (SpeechRecognitionAlternative alternative : alternatives) {
                    System.out.printf("Transcription: %s%n", alternative.getTranscript());
                }
            }
        }
        return resultAlternatives;
    }

    private static RecognitionAudio buildRecognitionAudio(Path filePath) throws IOException {
        byte[] data = Files.readAllBytes(filePath);
        return RecognitionAudio.newBuilder()
                .setContent(ByteString.copyFrom(data))
                .build();
    }

    private RecognitionConfig buildRecognitionConfig(String language) {
        return RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.FLAC)
                .setSampleRateHertz(16000)
                .setLanguageCode(language)
                .build();
    }
}
