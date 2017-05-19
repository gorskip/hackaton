package pl.pg.tools.marth.action;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import java.io.File;
import java.util.List;
import pl.pg.tools.marth.SoundRecorder;
import pl.pg.tools.marth.SpeechGoogleClient;
import pl.pg.tools.marth.action.impl.CommandNotRecognizedAction;
import pl.pg.tools.marth.command.CommandType;
import pl.pg.tools.marth.command.CommandTypeProvider;

/**
 *
 * @author gorskip
 */
public class ActionFlow {

    private static final long DEFAULT_RECORD_TIME = 10000;
    CommandTypeProvider commandTypeProvider = new CommandTypeProvider();
    MarthaActionProvider actionProvider = new MarthaActionProvider();

    public void startFlow() throws Exception {
        SoundRecorder soundRecorder = new SoundRecorder();
        final File recordedFile = soundRecorder.record();
        getAction(recordedFile).execute();
    }

    private MarthaAction getAction(File recordedFile) {
        SpeechGoogleClient speechClient = new SpeechGoogleClient();
        List<SpeechRecognitionAlternative> recognitions;
        try {
            recognitions = speechClient.speech(recordedFile, "pl-Pl");
        } catch (Exception ex) {
            return new CommandNotRecognizedAction();
        }
        return actionProvider.getAction(getCommand(recognitions));

    }

    private CommandType getCommand(final List<SpeechRecognitionAlternative> recognitions) {
        return commandTypeProvider.getCommandType(recognitions);
    }

}
