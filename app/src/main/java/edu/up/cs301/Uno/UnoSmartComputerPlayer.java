package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by fredenbe20 on 4/15/2018.
 */

public class UnoSmartComputerPlayer extends GameComputerPlayer {
    ArrayList<Card> playableCards;


    public UnoSmartComputerPlayer(String name){
        super(name);
        playableCards = new ArrayList<Card>();
    }

    protected void receiveInfo(GameInfo info) {
        if(info instanceof UnoGameState){
            UnoGameState state = (UnoGameState) info;
            if(state.getTurn() == this.playerNum){
                this.sleep(1000);
                //the smart AI cycles through its hand and selects all cards that can be played in the turn
                for(int i = 0; i < state.getCurrentPlayerHand().size(); i++){
                    if(state.getCurrentPlayerHand().get(i).getColor() == state.getCurrentColor()
                            || state.getCurrentPlayerHand().get(i).getType() == state.getDiscardPile().getTopCard().getType()
                            || state.getCurrentPlayerHand().get(i).getType() == Type.WILD
                            || state.getCurrentPlayerHand().get(i).getType() == Type.WILDDRAW4){
                        playableCards.add(state.getCurrentPlayerHand().get(i));
                    }
                    else{
                        playableCards.add(null);
                    }
                }

                /*
                plays a card based on the number of cards in the other players hand
                 */

                //will play a wild draw 4 if it has one and the other player is below 3 cards
                if(state.getPlayerHandSize(state.getTurn() + 1) < 3){
                    for (int i = 0; i < playableCards.size(); i++){
                        if(playableCards.get(i).getType() == Type.WILDDRAW4){
                                    this.game.sendAction(new PlaceCardAction(this, i));
                        }
                    }
                    //will play a skip, plus 2, or reverse if they have less than 5 cards
                } else if(state.getPlayerHandSize(state.getTurn() + 1) < 5){
                    for (int i = 0; i < playableCards.size(); i++){
                        if(playableCards.get(i).getType() == Type.SKIP
                                || playableCards.get(i).getType() == Type.PLUS2
                                || playableCards.get(i).getType() == Type.REVERSE){
                            this.game.sendAction(new PlaceCardAction(this, i));
                        }
                    }
                }
            }
        }
    }
}
