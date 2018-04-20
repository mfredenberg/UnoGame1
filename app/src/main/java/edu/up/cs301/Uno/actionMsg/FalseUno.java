package edu.up.cs301.Uno.actionMsg;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by papoutsa20 on 4/15/2018.
 */

public class FalseUno extends GameAction  {
    private static final long serialVersionUID = 423444324L;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public FalseUno(GamePlayer player) {
        super(player);
    }
}
