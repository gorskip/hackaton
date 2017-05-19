package pl.pg.tools.marth.action.impl;

import pl.pg.tools.marth.action.MarthaAction;

/**
 *
 * @author gorskip
 */
public class StartAction implements MarthaAction {

    @Override
    public void execute() {
        System.out.println("How can I help you?");
    }

}
