package edu.up.cs301.Uno.actionMsg;

import edu.up.cs301.Uno.Card;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class PlaceCardAction  extends GameAction {
    private int cardIndex;

    public PlaceCardAction(GamePlayer player){
        super(player);

    }
    public PlaceCardAction(GamePlayer player, int index){
        super(player);
        cardIndex = index;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}
