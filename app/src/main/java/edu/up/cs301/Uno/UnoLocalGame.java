package edu.up.cs301.Uno;

import edu.up.cs301.Uno.actionMsg.HasUnoAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoLocalGame extends LocalGame {


    private UnoGameState currentGameState; // current state

    /*
    Ctor called at begining of game that   initializes the game state to a new game
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
        if (action instanceof SkipTurnAction) {
            boolean move = skipTurn(this.currentGameState.getTurn());
            this.currentGameState.setTurn(
                    (this.currentGameState.getTurn() + 1 % this.currentGameState.getNumPlayers()));
            return move;
        } else if (action instanceof HasUnoAction) {
            hasUno(0); // assumes human player is 0
            return true;
        }
        else if(action instanceof PlaceCardAction)
        {
            PlaceCardAction place = (PlaceCardAction)action;
            boolean move = placeCard(this.currentGameState.getTurn(),place.getCard());
            this.currentGameState.setTurn(
                    (this.currentGameState.getTurn() + 1 % this.currentGameState.getNumPlayers()));
            return move;
        }
        return false;
    }

    public boolean placeCard(int playerID, Card toPlace) {
        return false;
    }

    public boolean drawCard(int playerID, int count) {
        if (canMove(playerID)) {
            for (int i = 0; i < count; i++)
                this.currentGameState.getCurrentPlayerHand().add(this.currentGameState.getDrawPile().take());
            return true;
        }
        return false;

    }

    public boolean skipTurn(int playerID) {
        boolean draw = drawCard(playerID, 1);
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
