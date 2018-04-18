package edu.up.cs301.Uno.actionMsg;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 4/3/2018.
 */

public class HasUnoAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 31221233L;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */

    public HasUnoAction(GamePlayer player) {
        super(player);


    }
}
