package edu.up.cs301.Uno;


import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by fredenbe20 on 2/25/2018.
 * <p>
 * The UnoGameState class holds all info on the
 * current game state, and houses methods for
 * all options for a players actions.
 *
 * @author Stelios Papoutsakis
 * @author Chris Fishback
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class UnoGameState extends GameState {


    //one master array list that holds all the player hands
    private ArrayList<ArrayList<Card>> playerHands;

    private ArrayList<Boolean> hasUno;

    //current turn, starting at 0
    private int turn;

    // color of the center card
    private Color currentColor;


    //game direction
    private boolean gameDirection; //true = clockwise; false = counterclockwise

    //Deck drawpile and discardpile
    private Deck drawPile;
    private Deck discardPile;


    /*
    * regular constructor
    */
    public UnoGameState(int numPlayers) {
        // creating array of hands and makeing the decks
        this.playerHands = new ArrayList<ArrayList<Card>>();
        this.hasUno = new ArrayList<Boolean>();
        this.drawPile = new Deck();
        this.discardPile = new Deck();
        this.drawPile.add108();
        for (int i = 0; i < numPlayers; i++) {
            this.playerHands.add(new ArrayList<Card>());
            this.hasUno.add(false);
            for (int j = 0; j < 7; j++) {
                this.playerHands.get(i).add(this.drawPile.take());

            }
        }


        // adding 7 cards to each player


        //setting all of the integer-based info
        this.turn = 0;

        //setting game direction clockwise
        this.gameDirection = true;

        //moving the top card from drawPile to discardPile
        this.discardPile.put(drawPile.take());

        if (this.discardPile.getTopCard().getType() == Type.WILD ||
                this.discardPile.getTopCard().getType() == Type.WILDDRAW4) {
            this.discardPile.put(this.drawPile.take());
        }

        this.setCurrentColor(this.discardPile.getTopCard().getColor());


    }

    /*
    * this constructor is used to make a deep copy of the game state
    */
    public UnoGameState(UnoGameState masterGameState, int playerID) {


        this.playerHands = new ArrayList<ArrayList<Card>>();
        this.hasUno = new ArrayList<Boolean>();
        for (boolean hasUnoBool : masterGameState.hasUno) {
            this.hasUno.add(hasUnoBool);
        }

        // telling the game state whose turn it is
        this.turn = masterGameState.getTurn();

        // copying decks
        this.drawPile = new Deck(masterGameState.getDrawPile());
        this.discardPile = new Deck(masterGameState.getDiscardPile());

        //copying other players hand, filling the values in the master array with nulls
        for (int i = 0; i < masterGameState.getNumPlayers(); i++) {
            this.playerHands.add(new ArrayList<Card>());
            for (int j = 0; j < masterGameState.getPlayerHandSize(i); j++) {
                this.playerHands.get(i).add(null);
            }
        }

        // copying the current players hand

        int i = 0;
        for (Card card : masterGameState.getPlayerHandAt(playerID)) {
            this.playerHands.get(playerID).set(i, new Card(card.getColor(), card.getType()));
            i++;

        }

        //copying color
        this.currentColor = discardPile.getCardAt(0).getColor();

        //copying gameDirection
        this.gameDirection = masterGameState.gameDirection;

    }


    //getters and setters

    public Deck getDrawPile() {
        return this.drawPile;
    }

    public int getTurn() {
        return turn;
    }


    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Deck getDiscardPile() {
        return discardPile;
    }


    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int getPlayerHandSize(int playerID) {
        return this.playerHands.get(playerID).size();
    }

    public ArrayList<Card> getCurrentPlayerHand() {
        return this.playerHands.get(this.turn);
    }

    public int getNumPlayers() {
        return this.playerHands.size();
    }

    public ArrayList<Card> getPlayerHandAt(int index) {
        return this.playerHands.get(index);
    }

    public boolean hasUno(int playerID) {
        return this.hasUno.get(playerID);
    }

    public void setHasUno(int playerID) {
        this.hasUno.set(playerID, this.getPlayerHandAt(playerID).size() == 1);
    }


    public void setGameDirection(boolean dir) {
        gameDirection = dir;
    }

    public boolean getGameDirection() {
        return gameDirection;
    }

}

