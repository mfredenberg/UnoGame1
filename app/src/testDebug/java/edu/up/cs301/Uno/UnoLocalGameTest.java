package edu.up.cs301.Uno;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fredenbe20 on 4/10/2018.
 */
public class UnoLocalGameTest {
    @Test
    public void sendUpdatedStateTo() throws Exception {

    }

    @Test
    public void canMove() throws Exception {

    }

    @Test
    public void checkIfGameOver() throws Exception {

    }

    @Test
    public void makeMove() throws Exception {

    }

    @Test
    public void placeCard() throws Exception {
        UnoLocalGame local = new UnoLocalGame();
        UnoGameState state = local.getCurrentGameState();
        //assertTrue(local.placeCard(state.getTurn(), null));
        local.placeCard(state.getTurn(), null);

        assertTrue(state.getCurrentPlayerHand().size()==7);

        assertEquals(state.getPlayerHandAt(0).size(), 6);
        assertEquals(state.getDiscardPile().getDeckSize(), 2);

        assertEquals(state.getDiscardPile().getTopCard().getType(), Type.ZERO);
    }

    @Test
    public void skipTurn() throws Exception {

    }

    @Test
    public void quit() throws Exception {

    }

    @Test
    public void hasUno() throws Exception {

    }

}