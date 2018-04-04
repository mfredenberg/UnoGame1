package edu.up.cs301.Uno;

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
        return false;
    }

    public boolean drawCard(int playerID) {
        if (canMove(playerID))
        {
            this.currentGameState.getCurrentPlayerHand().add(this.currentGameState.getDrawPile().take());
            return true;
        }
        return false;

    }

    public boolean skipTurn(int playerID) {
        Boolean draw = drawCard(playerID);
        if(draw){
            this.currentGameState.setTurn((playerID + 1 % this.currentGameState.getNumPlayers());
        }
    }

    public void quit() {
        System.exit(0);
    }

    public boolean hasUno(int playerID) {
        return false;
    }


}
