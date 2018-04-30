package edu.up.cs301.Uno;

import org.junit.Test;

import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
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
        UnoLocalGame local = new UnoLocalGame();
        UnoGameState gameState = local.getCurrentGameState();
        assertTrue(local.canMove(0));
        assertFalse(local.canMove(1));
        gameState.setTurn(1);
        assertFalse(local.canMove(0));

    }

    @Test
    public void checkIfGameOver() throws Exception {
        UnoLocalGame game = new UnoLocalGame();
        UnoGameState gameState = game.getCurrentGameState();
        for (int i = 0; i < gameState.getNumPlayers(); i++) {
            for (int j = 0; j < 7; j++) {
                gameState.getPlayerHandAt(i).add(gameState.getDrawPile().take());
            }
        }
        assertFalse(game.checkIfGameOver() != null);
        for (int i = 0; i < 7; i++) {
            gameState.getCurrentPlayerHand().remove(0);
        }
        assertTrue(game.checkIfGameOver() != null);

    }

    @Test
    public void makeMove() throws Exception {


    }

    @Test
    public void placeCard() throws Exception {
        UnoLocalGame local = new UnoLocalGame();
        UnoGameState gameState = local.getCurrentGameState();
        for (int i = 0; i < gameState.getNumPlayers(); i++) {
            for (int j = 0; j < 7; j++) {
                gameState.getPlayerHandAt(i).add(new Card(Color.BLUE, Type.EIGHT));
            }
        }
        gameState.getDiscardPile().put(new Card(Color.BLUE, Type.EIGHT));
        assertFalse(local.placeCard(1, 2));
        assertTrue(local.placeCard(0, 4));
        assertTrue(gameState.getPlayerHandAt(0).size() == 6);
        assertTrue(local.placeCard(1, 3));

    }

    @Test
    public void skipTurn() throws Exception {
        UnoLocalGame local = new UnoLocalGame();
        UnoGameState gameState = local.getCurrentGameState();
        assertEquals(gameState.getTurn(),0);
        local.skipTurn(0);
        assertEquals(gameState.getTurn(),1);
        gameState.setGameDirection(false);
        local.skipTurn(1);
        assertEquals(gameState.getTurn(),0);


    }



    @Test
    public void hasUno() throws Exception {
        UnoLocalGame local = new UnoLocalGame();
        UnoGameState gameState = local.getCurrentGameState();
        assertFalse(local.hasUno(gameState.getTurn()));
        gameState.getCurrentPlayerHand().add(new Card(Color.BLUE,Type.EIGHT));
        gameState.getCurrentPlayerHand().add(new Card(Color.BLUE,Type.EIGHT));
        assertTrue(local.hasUno(gameState.getTurn()));
        gameState.getCurrentPlayerHand().add(new Card(Color.BLUE,Type.EIGHT));
        assertFalse(local.hasUno(gameState.getTurn()));
        assertFalse(local.hasUno(local.getNextTurn(1)));

    }

    @Test
    public void getNextTurn() throws Exception {
        UnoLocalGame game = new UnoLocalGame();
        UnoGameState gamme = game.getCurrentGameState();
        assertEquals(game.getNextTurn(1), 1);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 2);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 3);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 0);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(2), 2);
        gamme.setTurn(game.getNextTurn(2));

        gamme.setGameDirection(false);
        assertEquals(game.getNextTurn(1), 1);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 0);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 3);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(1), 2);
        gamme.setTurn(game.getNextTurn(1));
        assertEquals(game.getNextTurn(2), 0);
        gamme.setTurn(game.getNextTurn(2));

        UnoLocalGame gameee = new UnoLocalGame();
        UnoGameState gaaaa = gameee.getCurrentGameState();
        gaaaa.setNumPlayers(2);
        gaaaa.setTurn(gameee.getNextTurn(1));
        assertEquals(gameee.getNextTurn(4), 1);


    }

}