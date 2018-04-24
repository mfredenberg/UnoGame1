package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.ColorAction;
import edu.up.cs301.Uno.actionMsg.FalseUno;
import edu.up.cs301.Uno.actionMsg.HasUnoAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.Quit;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.ProxyPlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Mason Fredenberg on 3/27/2018.
 * <p>
 * The local game class houses all necessary actions and rules
 * for uno to work
 *
 * @author Chris Fishback
 * @author Stelios Popoutsakis
 * @author Alli Jacobs
 * @author Mason Fredenberg
 *
 */

public class UnoLocalGame extends LocalGame {

    private UnoGameState currentGameState; // current state


    /*
     *Constructor called at beginning of game that initializes the game state to a new game
     */
    public UnoLocalGame() {
        super();
        this.currentGameState = new UnoGameState();
    }

    /*
     * method sends the new state of the game to the current player
     *
     * @param p
     *          the gameplayer the update is being sent to
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        if (ifStart()) {
            /**
             External Citation
             Date: April 18 2018
             Problem: Couldn't figure out how to initialize array to correctly
             reflect thee number of players in the game
             Resource:
             Nux
             Solution: I used what nux said and set the number of players outside the local game constructor
             */
            // sets numplayers to correct length
            this.currentGameState.setNumPlayers(this.players.length);

            // draws cards for players
            for (int i = 0; i < this.currentGameState.getNumPlayers(); i++) {
                this.currentGameState.getNames().add(this.playerNames[i]);
                for (int j = 0; j < 7; j++) {
                    this.currentGameState.getPlayerHandAt(i).add(this.currentGameState.getDrawPile().take());
                }
            }
        }

        if (p instanceof ProxyPlayer) {
            ProxyPlayer humanProxy = (ProxyPlayer) p;
            int i = 0;
            while(i < this.players.length)
            {
                if(this.players[i] == humanProxy) break;
                i++;
            }
            UnoGameState copy = new UnoGameState(this.currentGameState, i);
            humanProxy.sendInfo(copy);
        } else if (p instanceof UnoHumanPlayer) {
            UnoHumanPlayer human = (UnoHumanPlayer) p;
            UnoGameState copy = new UnoGameState(this.currentGameState, human.getPlayerID());
            human.sendInfo(copy);
        } else if (p instanceof UnoComputerPlayer) {
            UnoComputerPlayer cpuDumb = (UnoComputerPlayer) p;
            UnoGameState copy = new UnoGameState(this.currentGameState, cpuDumb.getPlayerID());
            cpuDumb.sendInfo(copy);

        } else if (p instanceof UnoSmartComputerPlayer) {
            UnoSmartComputerPlayer cpuSmart = (UnoSmartComputerPlayer) p;
            UnoGameState copy = new UnoGameState(this.currentGameState, cpuSmart.getPlayerID());
            cpuSmart.sendInfo(copy);

        }


    }

    /*
    * method checks if it's the players turn
    *
    * @param playerIdx
    *           numerical representation of player
    * @return boolean
    *           true if the can move, false if they can't
    */
    @Override
    protected boolean canMove(int playerIdx) {
        return this.currentGameState.getTurn() == playerIdx;
    }

    /*
    * method checks if the game is over
    *
    * @return String
    *           message declaring the player who won
    */
    @Override
    protected String checkIfGameOver() {
        for (int i = 0; i < this.currentGameState.getNumPlayers(); i++) {
            if (this.currentGameState.getPlayerHandAt(i).size() == 0) {
                int playerId = i;
                // This is for unit testing, as I cannot fill the playerNames array on the JUNIT testing
                if(this.playerNames == null) return "TESTING";
                return this.playerNames[playerId] + " has won";
            }
        }
        return null;
    }

    /*
    * method checks which action to take
    *
    * @param action
    *           the action sent by the current player
    * @return boolean
    *           returns true if action is real and is carried out, false if something went wrong
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
            hasUno(cpuDumb.getPlayerID());
            playerID = cpuDumb.getPlayerID();

        } else if (p instanceof UnoSmartComputerPlayer) {
            UnoSmartComputerPlayer cpuSmart = (UnoSmartComputerPlayer) p;
            hasUno(cpuSmart.getPlayerID());
            playerID = cpuSmart.getPlayerID();
        }
        else if(p instanceof ProxyPlayer)
        {
            ProxyPlayer proxy = (ProxyPlayer)p;
            int i = 0;
            while(i < this.players.length)
            {
                if(this.players[i] == proxy) break;
                i++;
            }
            playerID = i;
            hasUno(i);
        }


        //actions
        if (action instanceof SkipTurnAction) {
            return skipTurn(playerID);
        } else if (action instanceof HasUnoAction) {
            return hasUno(playerID);
        } else if (action instanceof PlaceCardAction) {
            PlaceCardAction place = (PlaceCardAction) action;
            return placeCard(playerID, place.getCardIndex());
        } else if (action instanceof ColorAction) {
            ColorAction color = (ColorAction) action;
            return changeColor(playerID, color.getWildColor());
        } else if (action instanceof FalseUno) {
            drawCard(playerID);
            return drawCard(playerID);

        }
        return false;
    }


    /*
    * method checks to see if draw is empty
    *
    * @return boolean
    *           true if draw is empty
    *           false if not
    */
    public boolean isDrawEmpty() {
        if (currentGameState.getDrawPile().getDeckSize() == 0) {
            return true;
        }
        return false;
    }

    /*
    * method checks to see if card selected can be placed,
    * will place it if it can, and proceeds to do the events
    * needed based on placed card. ie: draw 2
    *
    * @param playerID
    *           the player attempting to play
    * @param cardIndex
    *           index of card selected to play
    * @return boolean
    *           true if completed, false if not
    */
    public boolean placeCard(int playerID, int cardIndex) {

        //find the card that is being placed
        Card toPlace = this.currentGameState.getCurrentPlayerHand().get(cardIndex);

        boolean isPlaced = false;

        if (canMove(playerID)) { //check if the player can make a mover

            //Check if selected card is a wild card ------------------------------------------------------\\
            if (toPlace.getType() == Type.WILD || toPlace.getType() == Type.WILDDRAW4) {

                if (toPlace.getType() == Type.WILD) { //if card is wild

                    //place the card
                    placeCardDown(toPlace);

                    isPlaced = true;
                } else if (toPlace.getType() == Type.WILDDRAW4) { //if card is a wild draw 4 card

                    //place card
                    placeCardDown(toPlace);

                    for (int i = 0; i < 4; i++)
                        drawCard(getNextTurn(1));

                    isPlaced = true;
                }
            }
            //checks to see if the card is playable and will place card if possible
            if (placeNotWildCard(toPlace)) {
                isPlaced = true;
            }

            //change the color of the game
            if (this.currentGameState.getDiscardPile().getTopCard().getType() != Type.WILDDRAW4
                    && this.currentGameState.getDiscardPile().getTopCard().getType() != Type.WILD)
                currentGameState.setCurrentColor(currentGameState.getDiscardPile().getTopCard().getColor());
        }

        //if the card doesn't match any other card so it cannot be played
        return isPlaced;

    }

    /*
    * method draws a card for the current player
    *
    * @param playerID
    *           player attempting to draw card
    * @return boolean
    *           true if completed, false if something went wrong
    */
    private boolean drawCard(int playerID) {
        //checks if draw is empty, if so refills-
        if (this.isDrawEmpty()) {
            currentGameState.getDrawPile().drawEmpty(currentGameState.getDiscardPile());
            currentGameState.getDrawPile().suffle();
        }

        //take a card from the draw pile and put it on the players hand
        this.currentGameState.getPlayerHandAt(playerID).add(this.currentGameState.getDrawPile().take());

        return true;
    }


    /*
    * method skips turn and draws a card for current player
    *
    * @param playerID
    *           player attempting to skip turn
    * @return boolean
    *           true if completed, false if something went wrong
    */
    public boolean skipTurn(int playerID) {
        if (canMove(playerID)) { //make sure it's the players turn
            {
                // if greater than 18, puts card in the beginning so you cna actually ss the new card
                if (this.currentGameState.getCurrentPlayerHand().size() > 18) {
                    this.currentGameState.getPlayerHandAt(playerID).add(0, this.currentGameState.getDrawPile().take());
                } else {
                    drawCard(playerID);
                }
                //make it the next turn
                this.currentGameState.setTurn(getNextTurn(1));

                return true;
            }
        }
        return false;

    }

    /*
    * method checks if the player has uno
    * @param int
    *           player being checked for uno
    * @return boolean
    *           true if they have 1 card
    *           false if they have any other number
    */
    public boolean hasUno(int playerID) {
        this.currentGameState.setHasUno(playerID);
        return this.currentGameState.hasUno(playerID);
    }

    //getters and setters---------------------------------------------\\
    public UnoGameState getCurrentGameState() {
        return currentGameState;
    }

    public int getNextTurn(int numTurns) {
        int turn = this.currentGameState.getTurn();

        while (numTurns != 0) {
            if (this.currentGameState.getGameDirection()) {
                turn += 1;
                if (turn == this.currentGameState.getNumPlayers()) turn = 0;
            } else {
                turn -= 1;
                if (turn < 0) turn = this.currentGameState.getNumPlayers() - 1;
            }
            numTurns--;
        }

        return turn;


    }

    public boolean changeColor(int playerID, Color colorChange) {
        if (canMove(playerID)) {
            currentGameState.setCurrentColor(colorChange);
            this.currentGameState.setTurn(getNextTurn(1));
            return true;
        }
        return false;
    }


    //helper methods--------------------------------------------------------\\
    /*
    *This helper method places the players card onto the discard deck
    *
    * @param placeCard
    *           card to be played down
    */
    public void placeCardDown(Card placeCard) {

        this.currentGameState.getCurrentPlayerHand().remove(placeCard); //remove card from players hand
        this.currentGameState.getDiscardPile().put(placeCard); //place card

    }

    /*
    * helper method for place card
    * This method places the players card - which will always be a non wild card
    * @param placeCard
    *           card to be placed
    * @returns boolean
    *           true if action is carried out
    *           false if not
    */
    public boolean placeNotWildCard(Card placeCard) {

        if (placeCard.getType() == currentGameState.getDiscardPile().getCardAt(0).getType()
                || placeCard.getColor() == currentGameState.getCurrentColor()) {

            if (placeCard.getType() == Type.ZERO || placeCard.getType() == Type.ONE ||
                    placeCard.getType() == Type.TWO || placeCard.getType() == Type.THREE ||
                    placeCard.getType() == Type.FOUR || placeCard.getType() == Type.FIVE ||
                    placeCard.getType() == Type.SIX || placeCard.getType() == Type.SEVEN ||
                    placeCard.getType() == Type.EIGHT || placeCard.getType() == Type.NINE) { //if the card is a number card

                //place the card
                placeCardDown(placeCard);

                //change turn
                this.currentGameState.setTurn(getNextTurn(1));

                return true;

            } else if (placeCard.getType() == Type.SKIP) {

                //add card to discard pile
                placeCardDown(placeCard);

                //change turn
                this.currentGameState.setTurn(getNextTurn(2));

                return true;

            } else if (placeCard.getType() == Type.REVERSE) {
                //change game direction
                this.currentGameState.setGameDirection(!this.currentGameState.getGameDirection());

                //place card
                placeCardDown(placeCard);

                if (this.currentGameState.getNumPlayers() == 2)
                    this.currentGameState.setTurn(getNextTurn(2));
                else
                    this.currentGameState.setTurn(getNextTurn(1));

                return true;

            } else if (placeCard.getType() == Type.PLUS2) {

                //place card
                placeCardDown(placeCard);

                drawCard(getNextTurn(1));
                drawCard(getNextTurn(1));
                this.currentGameState.setTurn(getNextTurn(1));

                return true;

            }
        }
        return false;
    }


    /*
   * method that checks if all the arrayLists are empty, which means it is the start of the game
   * @returns boolean
   *            true if it is the start of the game
   *            false if not
   */
    public boolean ifStart() {
        for (int i = 0; i < this.currentGameState.getNumPlayers(); i++) {
            if (!(this.currentGameState.getPlayerHandAt(i).isEmpty()))
                return false;
        }
        return true;
    }

}