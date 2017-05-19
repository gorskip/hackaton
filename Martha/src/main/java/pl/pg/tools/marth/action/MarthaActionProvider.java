package pl.pg.tools.marth.action;

import pl.pg.tools.marth.action.impl.AccountBalanceAction;
import pl.pg.tools.marth.action.impl.CommandNotRecognizedAction;
import pl.pg.tools.marth.action.impl.StartAction;
import pl.pg.tools.marth.action.impl.TransferAction;
import pl.pg.tools.marth.command.CommandType;

/**
 *
 * @author gorskip
 */
public class MarthaActionProvider {

    public MarthaAction getAction(CommandType commandType) {
        switch (commandType) {
            case MARTHA:
                return new StartAction();
            case ACCOUNT_BALANCE:
                return new AccountBalanceAction();
            case TRANSFER:
                return new TransferAction();
            default:
                return new CommandNotRecognizedAction();
        }
    }

}
