package edu.up.cs301.Uno;

import java.io.Serializable;

/**
 * Created by Stelios Popoutsakis on 3/27/2018.
 *
 * This class creates the enumerators for the type of cards
 *
 * @author Chris Fishback
 * @author Stelios Popoutsakis
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

//enumerator for the type of the cards
public enum Type implements Serializable {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    SKIP,
    PLUS2,
    WILD,
    REVERSE,
    WILDDRAW4;
}
