package edu.up.cs301.Uno.actionMsg;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by steli on 4/3/2018.
 */

public class Quit extends GameAction implements Serializable {
    private static final long serialVersionUID = 124123213L;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Quit(GamePlayer player) {
        super(player);
    }
}
