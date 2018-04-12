package edu.up.cs301.Uno;

import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import android.util.Log;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoComputerPlayer extends GameComputerPlayer {
    private int playerID;

    public UnoComputerPlayer(String name) {
        super(name);
        this.playerID = this.playerNum +1;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            UnoGameState gameState = (UnoGameState) info;
            if (gameState.getTurn() == this.playerID ) {

                this.sleep(2000);
                int i;
                //loops through computer player's hand
                for(i = 0; i < gameState.getCurrentPlayerHand().size(); i++){
                    if(gameState.getCurrentPlayerHand().get(i).getColor() == gameState.getDiscardPile().getTopCard().getColor()
                            || gameState.getCurrentPlayerHand().get(0).getType() == gameState.getDiscardPile().getTopCard().getType()) {
                //plays first card that matches the top card of discard
                        Log.i("Computer Player: ","Computer Placed the Card: " + gameState.getCurrentPlayerHand().get(0).getColor()
                                + gameState.getCurrentPlayerHand().get(0).getType() + "----------------------------------------------------------//");

                        this.game.sendAction(new PlaceCardAction(this, i));
                        //force ends loop
                        i = gameState.getCurrentPlayerHand().size() + 1;

                }

                }
                //if the loop wasn't force ended (no card was played)
                if(i != gameState.getCurrentPlayerHand().size() + 1)
                    this.game.sendAction(new SkipTurnAction(this));


            }
        }

    }

    public int getPlayerID() {
        return playerID;
    }
}
