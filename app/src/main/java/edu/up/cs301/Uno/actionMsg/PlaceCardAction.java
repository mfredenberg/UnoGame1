package edu.up.cs301.Uno.actionMsg;

import edu.up.cs301.Uno.Card;
import edu.up.cs301.Uno.Color;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class PlaceCardAction extends GameAction {
    private int cardIndex;
    private Color newColor = null;

    public PlaceCardAction(GamePlayer player, int index, Color newColor) {
        super(player);
        cardIndex = index;
        this.newColor = newColor;


    }

    public PlaceCardAction(GamePlayer player, int index) {
        super(player);
        cardIndex = index;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Color getNewColor() {
        return this.newColor;
    }

    ;
}
