package pl.pg.tools.marth.action.impl;

import pl.pg.tools.marth.action.MarthaAction;

/**
 *
 * @author gorskip
 */
public class CommandNotRecognizedAction implements MarthaAction {

    @Override
    public void execute() {
        System.out.println("Cannot recognize command");
    }

}
