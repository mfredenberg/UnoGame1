package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.ColorAction;
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
    *Ctor called at begining of game that initializes the game state to a new game
     */
    public UnoLocalGame(int numPlayers) {
        this.currentGameState = new UnoGameState(numPlayers);
    }

    /*
    * method sends the new state of the game to the current player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        if (p instanceof UnoHumanPlayer) {
            UnoHumanPlayer human = (UnoHumanPlayer) p;
            UnoGameState copy = new UnoGameState(this.currentGameState, human.getPlayerID());
            human.sendInfo(copy);
        } else if (p instanceof UnoComputerPlayer) {
            UnoComputerPlayer cpuDumb = (UnoComputerPlayer) p;
            UnoGameState copy = new UnoGameState(this.currentGameState, cpuDumb.getPlayerID());
            cpuDumb.sendInfo(copy);

        }


    }

    /*
    * method checks if it's the players turn
    */
    @Override
    protected boolean canMove(int playerIdx) {
        return this.currentGameState.getTurn() == playerIdx;
    }

    /*
    * method checks if the game is over
    */
    @Override
    protected String checkIfGameOver() {
        if (this.currentGameState.getCurrentPlayerHand().size() == 0) {
            return "Player " + this.currentGameState.getTurn() + " has won";
        }
        return null;
    }

    /*
    * method checks which action to take
    */
    @Override
    protected boolean makeMove(GameAction action) {
        GamePlayer p = action.getPlayer();
        int playerID = -1;
        if (p instanceof UnoHumanPlayer) {
            UnoHumanPlayer human = (UnoHumanPlayer) p;
            playerID = human.getPlayerID();
        } else if (p instanceof UnoComputerPlayer) {
            UnoComputerPlayer cpuDumb = (UnoComputerPlayer) p;
            playerID = cpuDumb.getPlayerID();
        }

        //actions
        if (action instanceof Quit) {
            quit();
        } else if (action instanceof SkipTurnAction) {
            return skipTurn(playerID);
        } else if (action instanceof HasUnoAction) {
            return hasUno(playerID);
        } else if (action instanceof PlaceCardAction) {
            PlaceCardAction place = (PlaceCardAction) action;
            return placeCard(playerID, place.getCardIndex());
        } else if (action instanceof ColorAction){
            ColorAction color = (ColorAction) action;
            changeColor(color.getWildColor());
        }
        return false;
    }

    /*
    * checks to see if the card selected by the player can
    * place it onto discard pile and places it
    */

    public boolean placeCard(int playerID, int cardIndex) {

        boolean didPlace = false;
        Card toPlace = this.currentGameState.getCurrentPlayerHand().get(cardIndex);

        /*if (canMove(playerID)) { //check if the player can make a mover
            Card card = this.currentGameState.getCurrentPlayerHand().get(0);

            //place card on top of discard pile, and make it the next players turn

                this.currentGameState.getCurrentPlayerHand().remove(0);
                this.currentGameState.getDiscardPile().put(card);
                this.currentGameState.setCurrentColor(card.getColor());
                this.currentGameState.setNextTurn(1);
                didPlace = true;


            //say that the placement happened

        }*/

        if (canMove(playerID)) { //check if the player can make a mover

            //Check if selected card is a wild card ------------------------------------------------------\\
            if (toPlace.getType() == Type.WILD || toPlace.getType() == Type.WILDDRAW4) {

                if (toPlace.getType() == Type.WILD) {//ifcardiswild

                    //Get new color from user
                    //this.currentGameState.setCurrentColor(card.getColor());

                    //placethecard
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card
                    this.currentGameState.setNextTurn(1);

                    didPlace = true;

                    return true;
                } else if (toPlace.getType() == Type.WILDDRAW4) {//ifcardisawilddraw4card

                    //Get new color from user


                    //have the next player up draw 4 cards
                    for (int i = 0; i < 4; i++)
                        drawCard(this.currentGameState.getTurn() + 1 % this.currentGameState.getNumPlayers());

                    //placecard
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card
                    this.currentGameState.setNextTurn(1);

                    didPlace = true;
                }
            }

            //Check if selected card is not a wild card but valid------------------------------------------------------\\
            if (toPlace.getType() == currentGameState.getDiscardPile().getCardAt(0).getType()
                    || toPlace.getColor() == currentGameState.getDiscardPile().getCardAt(0).getColor()) {

                if (toPlace.getType() == Type.ZERO || toPlace.getType() == Type.ONE ||
                        toPlace.getType() == Type.TWO || toPlace.getType() == Type.THREE ||
                        toPlace.getType() == Type.FOUR || toPlace.getType() == Type.FIVE ||
                        toPlace.getType() == Type.SIX || toPlace.getType() == Type.SEVEN ||
                        toPlace.getType() == Type.EIGHT || toPlace.getType() == Type.NINE) {

                    //placethecard
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card
                    this.currentGameState.setNextTurn(1);

                    didPlace = true;

                } else if (toPlace.getType() == Type.SKIP) {

                    //add card to discard pile
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card

                    //skip next players turn
                    if (this.currentGameState.getNumPlayers() == 2) //if two players
                    {
                        this.currentGameState.setNextTurn(this.currentGameState.getTurn());
                    } else if (this.currentGameState.getNumPlayers() == 3 ||
                            this.currentGameState.getNumPlayers() == 4) { //if three or four players
                        this.currentGameState.setNextTurn(this.currentGameState.getTurn() + 2);
                    }

                    didPlace = true;

                } else if (toPlace.getType() == Type.REVERSE) {
                    //change game direction
                    this.currentGameState.setGameDirection(!this.currentGameState.getGameDirection());

                    //place card
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card

                    didPlace = true;

                } else if (toPlace.getType() == Type.PLUS2) {

                    //place card
                    this.currentGameState.getCurrentPlayerHand().remove(toPlace); //remove card from players hand
                    this.currentGameState.getDiscardPile().put(toPlace); //place card

                    //next player draws 2 cards
                    for (int i = 0; i < 2; i++)
                        drawCard(this.currentGameState.getTurn() + 1);

                    didPlace = true;

                }
            }
        }
        return didPlace;  //double check this!  -- Nux

    }

    /*
    * draws a card for the current player
    */
    private boolean drawCard(int playerID) {
        if (canMove(playerID)) { //make sure it's the players turn

            //take a card from the draw pile and put it on the players hand
            this.currentGameState.getPlayerHandAt(playerID).add(this.currentGameState.getDrawPile().take());

            return true;
        }
        return false;

    }

    /*
    * skips turn and draws a card for current player
    */
    public boolean skipTurn(int playerID) {
        boolean draw = drawCard(playerID);

        if (draw) { //if card is drawable

            //make it the next turn
            this.currentGameState.setNextTurn(1);

            return true;
        }
        return false;

    }

    /*
    * quits system
    */
    public void quit() {
        System.exit(0);
    }

    /*
    * checks if the player has uno
    */
    public boolean hasUno(int playerID) {
        return this.currentGameState.hasUno(playerID);
    }

    //getters and setters
    public UnoGameState getCurrentGameState() {
        return currentGameState;
    }

    public Color changeColor( Color colorChange){
        currentGameState.setCurrentColor(colorChange);
        return colorChange;
    }
}