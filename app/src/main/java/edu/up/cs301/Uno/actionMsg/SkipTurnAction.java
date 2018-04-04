package edu.up.cs301.Uno.actionMsg;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 4/3/2018.
 */

public class SkipTurnAction  extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SkipTurnAction(GamePlayer player) {
        super(player);
    }
}
