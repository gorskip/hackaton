package pl.pg.tools.marth.command;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gorskip
 */
public class CommandTypeProvider {

    public CommandType getCommandType(List<SpeechRecognitionAlternative> recognitions) {
        if (recognitions.isEmpty()) {
            return CommandType.UNKNOWN;
        }
        return resolveCommandType(getRecognitionWithHighestConfidence(recognitions));
    }

    private SpeechRecognitionAlternative getRecognitionWithHighestConfidence(List<SpeechRecognitionAlternative> recognitions) {
        Comparator<SpeechRecognitionAlternative> byConfidence = (r1, r2) -> Float.compare(
                r1.getConfidence(), r2.getConfidence());
        return recognitions.stream()
                .sorted(byConfidence)
                .collect(Collectors.toList())
                .get(0);
    }

    private CommandType resolveCommandType(SpeechRecognitionAlternative recognition) {
        String transcript = recognition.getTranscript();
        if (matchAny(transcript, CommandKeyword.getAccountBalanceKeywords())) {
            return CommandType.ACCOUNT_BALANCE;
        }
        if (matchAny(transcript, CommandKeyword.getTransferKeywords())) {
            return CommandType.TRANSFER;
        }
        if (matchAny(transcript, CommandKeyword.getMarthaKeywords())) {
            return CommandType.MARTHA;
        }
        return CommandType.UNKNOWN;
    }

    private boolean matchAny(String transcript, String[] keywords) {
        for (String keyword : keywords) {
            if (transcript.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
