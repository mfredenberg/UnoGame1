package edu.up.cs301.Uno;

import edu.up.cs301.Uno.actionMsg.HasUnoAction;
import edu.up.cs301.game.Game;

/**
 * Created by steli on 4/16/2018.
 */

public class ThreadHasUno extends Thread {
    private Game game;
    private HasUnoAction action;

    public ThreadHasUno(Game game, HasUnoAction action) {
        this.game = game;
        this.action = action;
    }

    @Override
    public void run() {
        this.game.sendAction(action);
    }
}
