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

    //one master array list holds all the player names
    private ArrayList<String> playerNames;

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
    public UnoGameState() {
        // creating array of hands and makeing the decks
        this.playerHands = new ArrayList<ArrayList<Card>>();
        this.hasUno = new ArrayList<Boolean>();
        this.hasUno.add(false);
        this.hasUno.add(false);
        this.drawPile = new Deck();
        this.discardPile = new Deck();
        this.drawPile.add108();

        //adds two players
        this.playerHands.add(new ArrayList<Card>());
        this.playerHands.add(new ArrayList<Card>());


        // adding 7 cards to each player
        for (int i = 0; i < 7; i++) {
            this.playerHands.get(0).add(this.drawPile.take());
            this.playerHands.get(1).add(this.drawPile.take());
        }

        //setting all of the integer-based info
        this.turn = 0;

        //setting game direction clockwise
        this.gameDirection = true;

        //moving the top card from drawPile to discardPile
        this.discardPile.put(drawPile.take());

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
        this.turn = playerID;

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
        for (Card card : masterGameState.getCurrentPlayerHand())
        {
            this.playerHands.get(playerID).set(i,new Card(card.getColor(), card.getType()));
            i++;

        }

        //copying color
        this.currentColor = discardPile.getCardAt(0).getColor();

        //copying gameDirection
        this.gameDirection = masterGameState.gameDirection;

    }

    /*
    * method converts all variables into strings
    */
    @Override
    public String toString() {
        String str = "# cards in draw pile: " + drawPile.getDeckSize();
        str += "\n";

        str += "Player1 #cards: " + this.playerHands.get(0).size();
        str += "\n";

        str += "Player2 #cards: " + this.playerHands.get(1).size();
        str += "\n";


        str += "current player: " + this.turn;
        str += "\n";
        str += "card Val: ";
        for (Card card : this.playerHands.get(this.turn)) {
            str += " " + card.getType() + " " + card.getColor();
        }
        str += "\n";

        str += "Top card in discard pile: " + this.getDiscardPile().take().getType() + " " + this.getDiscardPile().take().getColor();
        str += "\n";
        str += "Game direction: " + this.gameDirection;
        str += "\n";
        str += "Current color: " + this.currentColor;

        str += "\n";
        str += "\n";
        str += "\n";


        return str;
    }


    //getters and setters

    public Deck getDrawPile() {
        return this.drawPile;
    }

    public int getTurn() {
        return turn;
    }

    public void setNextTurn(int numTurns) {

        if (this.gameDirection)
            this.turn = (this.turn + numTurns % this.playerHands.size());
        else
            this.turn = (this.turn - numTurns % this.playerHands.size());

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

    public String getPlayerNames(int playerID) {
        return this.playerNames.get(playerID);
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


}

