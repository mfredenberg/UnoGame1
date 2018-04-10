package edu.up.cs301.Uno.actionMsg;

import edu.up.cs301.Uno.Card;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class PlaceCardAction  extends GameAction {
    private Card card;

    public PlaceCardAction(GamePlayer player){
        super(player);
        //card = newCard;
    }

    public Card getCard() {
        return card;
    }
}
