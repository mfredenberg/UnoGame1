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

        // telling the game state whose turn it is
        this.turn = playerID;

        // copying decks
        this.drawPile = new Deck(masterGameState.getDrawPile());
        this.discardPile = new Deck(masterGameState.getDiscardPile());

        //copying other players hand, filling the values in the master array with nulls
        for (int i = 1; i < masterGameState.getNumPlayers(); i++) {
            this.playerHands.add(new ArrayList<Card>());
            for(int j = 0; j < masterGameState.getPlayerHandSize(playerID + i % masterGameState.getNumPlayers());j++) {
                this.playerHands.get(playerID + i % masterGameState.getNumPlayers()).add(null);
            }
        }


        // copying the current players hand
        this.playerHands.add(playerID, (ArrayList<Card>) (masterGameState.getCurrentPlayerHand().clone()));


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

    /*
    * draws a card from the deck and puts it into the players hand
    * @return true if player can draw a card
    */
//    public boolean drawCard(int playerId) {
//        //return false if there are no cards to draw from
//        if (this.drawPile.getDeckSize() < 1 || playerId != this.turn)
//            return false;
//
//        //gets the player and adds a card to his/her hand
//        this.currentPlayerHand.add(this.drawPile.take());
//        switch (playerId) {
//            case 0:
//                player1NumCards++;
//                break;
//            case 1:
//                player2NumCards++;
//                break;
//            case 2:
//                player3NumCards++;
//                break;
//            case 3:
//                player4NumCards++;
//                break;
//
//        }
//
//
//        return true;
//    }


    /* method places a card onto the discard pile
    * @return true if player can place card
//    */
//    public boolean placeCard(int playerId, Card toPlace) {
//
//        if (playerId != this.turn || this.currentPlayerHand.size() == 0) return false;
//
//        //gets the player, removes the card,
//        //and adds the card to the discard pile
//        currentPlayerHand.remove(toPlace);
//        discardPile.put(toPlace);
//        switch (playerId) {
//            case 0:
//                player1NumCards--;
//                break;
//            case 1:
//                player2NumCards--;
//                break;
//            case 2:
//                player3NumCards--;
//                break;
//            case 3:
//                player4NumCards--;
//                break;
//
//        }
//
//
//        return true;
//    }
//
//    /*
//    * method draws a card and moves turn on to the next player
//    * @return true if skip turn is possible
//    */
//    public boolean skipTurn(int playerId) {
//        if (playerId != this.turn) return false;
//
//        //draw a card
//        drawCard(playerId);
//
//        //skip turn
//        this.turn++;
//
//        return true;
//    }
//
//    /*
//    * method that quits game
//    * @return true
//    */
//    public boolean quit(int playerId) {
//        System.exit(0);
//        return true;
//    }
//
//    /*
//    * method that is called to see if player has uno
//    * @return true if player has uno, false otherwise
//    */
//    public boolean hasUno(int playerId) {
//
//        switch (playerId) {
//            case 0: //master player
//                if (this.player1NumCards == 1) return true;
//                return false;
//            case 1: //player2
//                if (this.player2NumCards == 1) return true;
//                return false;
//            case 2: //player3
//                if (this.player3NumCards == 1) return true;
//                return false;
//            case 3: //player4
//                if (this.player4NumCards == 1) return true;
//                return false;
//        }
//
//        return false;
//    }

    //getters and setters

    public Deck getDrawPile() {
        return this.drawPile;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn)
    {
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

    public ArrayList<Card> getCurrentPlayerHand()
    {
        return this.playerHands.get(this.turn);
    }

    public int getNumPlayers() {
        return this.playerHands.size();
    }

    public String getPlayerNames(int playerID) {
        return this.playerNames.get(playerID);
    }


}

