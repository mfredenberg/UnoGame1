package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.HasUnoAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.Quit;
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
        if (action instanceof Quit) {
            quit();
        } else if (action instanceof SkipTurnAction) {
            return skipTurn(this.currentGameState.getTurn());

        } else if (action instanceof HasUnoAction) {
            return hasUno(this.currentGameState.getTurn());
        } else if (action instanceof PlaceCardAction) {
            PlaceCardAction place = (PlaceCardAction) action;
            return placeCard(this.currentGameState.getTurn(), place.getCard());
        }
        return false;
    }

    public boolean placeCard(int playerID, Card toPlace) {

        //Check if selected card is awildcard
        if (toPlace.getType() == Type.WILD || toPlace.getType() == Type.WILDDRAW4) {

            if (toPlace.getType() == Type.WILD) {//ifcardiswild


                //Getnewcolorfromuser

                //placethecard

                return true;
            } else if (toPlace.getType() == Type.WILDDRAW4) {//ifcardisawilddraw4card

                //Getnewcolorfromuser

                //havethenextplayerupdraw4cards

                //placecard

                return true;
            }
        }

        //Checkforallothercardtypesorcolors
        if (toPlace.getType() == currentGameState.getDiscardPile().getCardAt(0).getType()
                || toPlace.getColor() == currentGameState.getDiscardPile().getCardAt(0).getColor()) {

            if (toPlace.getType() == Type.ZERO || toPlace.getType() == Type.ONE ||
                    toPlace.getType() == Type.TWO || toPlace.getType() == Type.THREE ||
                    toPlace.getType() == Type.FOUR || toPlace.getType() == Type.FIVE ||
                    toPlace.getType() == Type.SIX || toPlace.getType() == Type.SEVEN ||
                    toPlace.getType() == Type.EIGHT || toPlace.getType() == Type.NINE) {

                //placethecard
                return true;
            } else if (toPlace.getType() == Type.SKIP) {

                //addturntonextplayer
                //placecard
                return true;

            } else if (toPlace.getType() == Type.REVERSE) {
                //changegamedirectionSetGameDirection(!gameDirection);
                //placecard
                return true;
            } else if (toPlace.getType() == Type.PLUS2) {
                //nextplayerdraws2cards
                //placecard
                return true;
            }

            return false;
        }

        return false;  //double check this!  -- Nux
    }


    private boolean drawCard(int playerID) {
        if (canMove(playerID)) {
            this.currentGameState.getCurrentPlayerHand().add(this.currentGameState.getDrawPile().take());
            return true;
        }
        return false;

    }

    public boolean skipTurn(int playerID) {
        Boolean draw = drawCard(playerID);
        if (draw) {
            this.currentGameState.setNextTurn(1);
            return true;
        }
        return false;

    }

    public void quit() {
        System.exit(0);
    }

    public boolean hasUno(int playerID) {
        return this.currentGameState.hasUno(playerID);
    }


}