package edu.up.cs301.Uno;

import org.junit.Test;

import edu.up.cs301.game.infoMsg.GameState;

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
        UnoLocalGame local = new UnoLocalGame(2);
        UnoGameState state = local.getCurrentGameState();
        //assertTrue(local.placeCard(state.getTurn(), null));
       // local.placeCard(state.getTurn(), null);

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

    @Test
    public void getNextTurn() throws Exception
    {
//        UnoLocalGame game = new UnoLocalGame(4);
//        UnoGameState gamme = game.getCurrentGameState();
//        assertEquals(game.getNextTurn(1), 1);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1), 2);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1), 3);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1), 0);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(2), 2);
//        gamme.setTurn(game.getNextTurn(2));

//        gamme.setGameDirection(false);
//        assertEquals(game.getNextTurn(1),1);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1),0);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1),3);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(1),2);
//        gamme.setTurn(game.getNextTurn(1));
//        assertEquals(game.getNextTurn(2),0);
//        gamme.setTurn(game.getNextTurn(2));

        UnoLocalGame gameee = new UnoLocalGame(2);
        UnoGameState gaaaa = gameee.getCurrentGameState();
        gaaaa.setTurn(gameee.getNextTurn(1));
        assertEquals(gameee.getNextTurn(4),1);




    }

}