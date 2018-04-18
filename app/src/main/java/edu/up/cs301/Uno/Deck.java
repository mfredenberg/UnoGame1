package edu.up.cs301.Uno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stelios Papoutsakis on 3/2/2018.
 * <p>
 * The deck class holds an arraylist of cards, and on
 * can create the full deck for the uno game, and
 * shuffle (shuffle) them.
 *
 * @author Stelios Papoutsakis
 * @author Chris Fishback
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class Deck implements Serializable {

    private static final long serialVersionUID = 417201801L;

    //holds the deck of cards
    private ArrayList<Card> deck = new ArrayList<Card>();

    // default constructor
    public Deck() {
    }


    // deep copy constructor
    public Deck(Deck deck) {
        for (Card card : deck.getDeck()) {
            this.deck.add(new Card(card.getColor(), card.getType()));
        }
    }

    /*
    * method adds all uno cards into the deck
    */
    public void add108() {
        for (Color color : Color.values()) {
            for (Type type : Type.values()) {
                if (type != Type.WILD && type != Type.WILDDRAW4) {
                    this.deck.add(new Card(color, type));
                    if (type != Type.ZERO) {
                        this.deck.add(new Card(color, type));
                    }

                } else
                    this.deck.add(new Card(null, type));

            }

        }

        suffle();

    }

    /*
    * method shuffles the card... -suffle- lol
    */

    public void suffle() {
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
            int index = rand.nextInt(108);
            Card card = deck.get(index);
            deck.remove(index);
            index = rand.nextInt(108);
            deck.add(index, card);
        }
    }
    //takes cards out of the Deck passed in and adds them to this deck
    public void drawEmpty(Deck discard){
        for(int i = discard.getDeckSize() - 1; i > 1; i--){
            this.put(discard.getCardAt(i));
            discard.getDeck().remove(i);
        }
    }

    /*
    * method puts a card at the beginning of the deck
    */
    public void put(Card card) {
        put(card, 0);
    }

    /*
    * method puts a card at given index
    */
    public void put(Card card, int index) {
        this.deck.add(index, card);
    }

    /*
    * method removes card from the beginning of the deck
    */
    public Card take() {
        return deck.remove(0);
    }


    /*
    * method gets a card at a given index <---- is a repeat of getCard,
    * we need to remove one of the tow
    */
    public Card getCardAt(int index) {
        return deck.get(index);
    }

    /*
    * method gets a deck
    */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /*
    * method gets deck size
    */
    public int getDeckSize() {
        return deck.size();
    }

    /*
    * method gets a top card
    */
    public Card getTopCard() {
        return deck.get(0);
    }


}

