package edu.up.cs301.Uno;

import junit.framework.TestCase;

/**
 * Created by chrisfishback on 4/4/18.
 */
public class UnoLocalGameTest extends TestCase {
    public void testSendUpdatedStateTo() throws Exception {

    }

    public void testCanMove() throws Exception {
        UnoGameState uno = new UnoGameState();
        UnoLocalGame local = new UnoLocalGame();
        assertTrue(local.canMove(uno.getTurn()));
        uno.setNextTurn(1);
        assertFalse(local.canMove(uno.getTurn() - 1));

    }

    public void testCheckIfGameOver() throws Exception {
        UnoGameState uno = new UnoGameState();
        UnoLocalGame local = new UnoLocalGame();

        //check if an one-card hand returns true for if uno
        uno.getCurrentPlayerHand().clear();
        uno.getCurrentPlayerHand().add(new Card(null,null));
        assertTrue(local.hasUno(uno.getTurn()));

    }

    public void testMakeMove() throws Exception {

    }

    public void testPlaceCard() throws Exception {

    }

    public void testDrawCard() throws Exception {

    }

    public void testSkipTurn() throws Exception {

    }

    public void testHasUno() throws Exception {

    }

}