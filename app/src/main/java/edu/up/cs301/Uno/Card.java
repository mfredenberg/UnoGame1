package edu.up.cs301.Uno;

/**
 * Created by fredenbe20 on 2/25/2018.
 *
 * The card class is an object for an uno card with a specific value,
 * type, and color.
 *
 * @author Stelios Papoutsakis
 * @author Chris Fishback
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class Card {



    //description variables for the card
    private Color color; //1-9: numbers, 10: skip, 11: reverse,
    //12: draw2, 13: wild, 14: wild draw 4
    private Type type;

    //1st is top, second is left, 3rd is bottom, 4th is right
    private int[] coodinates = new int[4];

    public Card(Color color, Type initType)
    {
        this.color = color;
        this.type = initType;
    }

    //getters
    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }



}
