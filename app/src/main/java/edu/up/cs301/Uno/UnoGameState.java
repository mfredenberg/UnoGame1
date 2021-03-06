package edu.up.cs301.Uno;


import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by Mason Fredenberg on 2/25/2018.
 * <p>
 * The UnoGameState class holds all info on the
 * current game state
 * <p>
 * The class also has getters and setters to change the state of the game
 * <p>
 * A deep copy can be made based on a playerID,
 * This in turn makes all the other hands null for that player
 *
 * @author Stelios Papoutsakis
 * @author Chris Fishback
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class UnoGameState extends GameState implements Serializable {


    //serial number for serializable
    private static final long serialVersionUID = 417201802L;

    //one master array list that holds all the player hands
    private ArrayList<ArrayList<Card>> playerHands;

    // Array of boolean values if a player has uno
    private ArrayList<Boolean> hasUno;

    //current turn, starting at 0
    private int turn;

    // color of the center card(top of discard)
    private Color currentColor;


    //game direction
    private boolean gameDirection; //true = clockwise; false = counterclockwise

    //Deck drawpile and discardpile
    private Deck drawPile;
    private Deck discardPile;

    //list of player names
    private ArrayList<String> names;


    /*
    * Constructor
    */
    public UnoGameState() {
        // creating array of hands and makeing the decks
        this.playerHands = new ArrayList<ArrayList<Card>>();
        this.hasUno = new ArrayList<Boolean>();
        this.drawPile = new Deck();
        this.discardPile = new Deck();
        this.drawPile.add108();
        this.names = new ArrayList<String>();

        // adding 7 cards to each player, and adding false to the hasUno ArrayList
        for (int i = 0; i < 4; i++) {
            this.playerHands.add(new ArrayList<Card>());
            this.hasUno.add(false);
//            for (int j = 0; j < 7; j++) {
//                this.playerHands.get(i).add(this.drawPile.take());
//
//            }
        }


        //turn always starts at player zero
        this.turn = 0;

        //setting game direction clockwise
        this.gameDirection = true;

        //moving the top card from drawPile to discardPile
        this.discardPile.put(drawPile.take());


        // if the top card is wild, take another till it isn't
        while (this.discardPile.getTopCard().getType() == Type.WILD ||
                this.discardPile.getTopCard().getType() == Type.WILDDRAW4) {
            this.discardPile.put(this.drawPile.take());
        }

        this.setCurrentColor(this.discardPile.getTopCard().getColor());


    }

    /*
    * Constructor is used to make a deep copy of the game state based on a playerID
    */
    public UnoGameState(UnoGameState masterGameState, int playerID) {


        this.playerHands = new ArrayList<ArrayList<Card>>();
        this.hasUno = new ArrayList<Boolean>();
        this.names = new ArrayList<String>();
        int nameIndex = 0;
        for (boolean hasUnoBool : masterGameState.hasUno) {
            this.hasUno.add(hasUnoBool);
            this.names.add(masterGameState.getNames().get(nameIndex));
            nameIndex++;
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
        this.currentColor = masterGameState.getCurrentColor();

        //copying gameDirection
        this.gameDirection = masterGameState.gameDirection;

    }

/*
Appropriate Getters and setters for the Uno game state.
 */

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


    /*
    Method that sets the num of players once game is initialized
     @return void
     @Param number of players
     */
    public void setNumPlayers(int players) {
        while (players < getNumPlayers()) {
            this.playerHands.remove(this.playerHands.size() - 1);
            this.hasUno.remove(this.playerHands.size() - 1);
        }
    }


    public ArrayList<Card> getPlayerHandAt(int index) {
        return this.playerHands.get(index);
    }

    public boolean hasUno(int playerID) {
        return this.hasUno.get(playerID);
    }

    public void setUno(int playerID, boolean uno) {
        this.hasUno.set(playerID, uno);
    }


    /*
    Method that sets a boolean to see if the player will have an uno if they play a card
     @return void
     @Param int playerID
     */

    public void setHasUno(int playerID) {
        this.hasUno.set(playerID, this.getPlayerHandAt(playerID).size() - 1 == 1);
    }


    public void setGameDirection(boolean dir) {
        gameDirection = dir;
    }

    public boolean getGameDirection() {
        return gameDirection;
    }

    public ArrayList<String> getNames() {
        return names;
    }

}

