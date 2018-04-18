package edu.up.cs301.Uno.actionMsg;


import java.io.Serializable;

import edu.up.cs301.Uno.Color;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by jacobsa20 on 4/11/2018.
 */

public class ColorAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 78474844L;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    private Color wildColor;
    public ColorAction(GamePlayer player, Color color) {

        super(player);
        wildColor = color;
    }

    public Color getWildColor() {return wildColor;}
}
