package edu.up.cs301.Uno;

import java.io.Serializable;

/**
 * Created by Mason Fredenberg on 2/25/2018.
 * <p>
 * The card class is an object for an uno card with a specific value,
 * type, and color.
 *
 * @author Stelios Papoutsakis
 * @author Chris Fishback
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class Card implements Serializable {

    private static final long serialVersionUID = 417201801L;

    //description variables for the card
    private Color color; //1-9: numbers, 10: skip, 11: reverse,
    //12: draw2, 13: wild, 14: wild draw 4
    private Type type;

    public Card(Color color, Type initType) {
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

    public void setColor(Color color) {
        if (this.type == Type.WILD || this.type == Type.WILDDRAW4)
            this.color = color;
    }


}
