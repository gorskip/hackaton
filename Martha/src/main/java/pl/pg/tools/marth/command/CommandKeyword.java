package pl.pg.tools.marth.command;

/**
 *
 * @author gorskip
 */
public class CommandKeyword {

    public static String[] getAccountBalanceKeywords() {
        return new String[]{"stan konto", "stan konta", "środki na koncie"};
    }

    public static String[] getTransferKeywords() {
        return new String[]{"wykonaj przelew", "zrób przelew"};
    }

    static String[] getMarthaKeywords() {
        return new String[]{"Marta"};
    }
}
