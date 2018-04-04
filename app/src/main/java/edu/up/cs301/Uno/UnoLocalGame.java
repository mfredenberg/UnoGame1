package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoLocalGame extends LocalGame {


    private UnoGameState currentGameState; // current state

    /*
    Ctor called at begining of game that initializes the game state to a new game
     */
    public UnoLocalGame() {
        this.currentGameState = new UnoGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerIdx) {
        return this.currentGameState.getTurn() == playerIdx;
    }

    @Override
    protected String checkIfGameOver() {
        if (this.currentGameState.getCurrentPlayerHand().size() == 0) {
            return "Player " + this.currentGameState.getTurn() + "has won";
        }
        return "";
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }

    public boolean placeCard(int playerID, Card toPlace) {

        //Check if selected card is a wild card ​
        if (toPlace.getType() == Type.WILD || toPlace.getType() == Type.WILDDRAW4) {

            if (toPlace.getType() == Type.WILD) { ​//if card is wild

                //Get new color from user ​

                //place the card

                return true; ​
            } else if (toPlace.getType() == Type.WILDDRAW4) { // if card is a wild draw 4 card

                //Get new color from user ​

                //have the next player up draw 4 cards

                //place card

                return true; ​
            }
        }

        // Check for all other card types or colors ​
        if (toPlace.getType() == currentGameState.getDiscardPile().getCardAt(0).getType()
                || toPlace.getColor() == currentGameState.getDiscardPile().getCardAt(0).getColor()) {

            if (toPlace.getType() == Type.ZERO || toPlace.getType() == Type.ONE ||
                    toPlace.getType() == Type.TWO || toPlace.getType() == Type.THREE ||
                    toPlace.getType() == Type.FOUR || toPlace.getType() == Type.FIVE ||
                    toPlace.getType() == Type.SIX || toPlace.getType() == Type.SEVEN ||
                    toPlace.getType() == Type.EIGHT || toPlace.getType() == Type.NINE) {
                //place the card ​
                return true; ​
            } else if (toPlace.getType() == Type.SKIP) { ​

                //add turn to next player ​

                //place card ​

                return true; ​
            } else if (toPlace.getType() == Type.REVERSE) {

                //change game direction SetGameDirection(!gameDirection); ​

                //place card

                return true; ​
            } else if (toPlace.getType() == Type.PLUS2) { ​

                //next player draws 2 cards

                //place card

                return true; ​
            }
        }

        return false;
    }

    public boolean drawCard(int playerID) {
        if (canMove(playerID)) {
            this.currentGameState.getCurrentPlayerHand().add(this.currentGameState.getDrawPile().take());
            return true;
        }
        return false;

    }

    public boolean skipTurn(int playerID) {
        Boolean draw = drawCard(playerID);
        if (draw) {
            this.currentGameState.setTurn((playerID + 1 % this.currentGameState.getNumPlayers()));
            return true;
        }
        return false;

    }

    public void quit() {
        System.exit(0);
    }

    public boolean hasUno(int playerID) {
        return this.currentGameState.getPlayerHandSize(playerID) == 1;
    }


}
