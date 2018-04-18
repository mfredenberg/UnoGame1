package edu.up.cs301.Uno;

import edu.up.cs301.Uno.actionMsg.ColorAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.util.Log;


/**
 * Created by Mason Fredenberg on 4/15/2018.
 *
 * This class houses the automation of play for
 * the dumb computer player.
 *
 * @author Chris Fishback
 * @author Stelios Popoutsakis
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class UnoComputerPlayer extends GameComputerPlayer {


    public UnoComputerPlayer(String name) {
        super(name);

    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            UnoGameState gameState = (UnoGameState) info;
            if (gameState.getTurn() == this.playerNum) {
                this.sleep(1000);
                boolean didPlace = false;
                //loops through computer player's hand
                for (int i = 0; i < gameState.getCurrentPlayerHand().size(); i++) {
                    if (gameState.getCurrentPlayerHand().get(i).getColor() == gameState.getCurrentColor()
                            || gameState.getCurrentPlayerHand().get(i).getType() == gameState.getDiscardPile().getTopCard().getType()) {
                        //plays first card that matches the top card of discard
                        Log.i("Computer Player: ", "Computer Placed the Card: " + gameState.getCurrentPlayerHand().get(0).getColor()
                                + gameState.getCurrentPlayerHand().get(0).getType() + "----------------------------------------------------------//");
                        this.game.sendAction(new PlaceCardAction(this, i));
                        if (gameState.getCurrentPlayerHand().get(i).getType() == Type.WILD
                                || gameState.getCurrentPlayerHand().get(i).getType() == Type.WILDDRAW4) {
                            this.game.sendAction(new ColorAction(this, Color.RED));

                        }
                        didPlace = true;
                        break;


                    }
                }
                //if the loop wasn't force ended (no card was played)

                if (!didPlace) this.game.sendAction(new SkipTurnAction(this));

            }
        }
    }

    public int getPlayerID() {
        return this.playerNum;
    }
}
