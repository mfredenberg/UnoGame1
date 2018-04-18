package edu.up.cs301.Uno;

import android.widget.Button;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

import static edu.up.cs301.game.R.id.hasUnoButton;
import static edu.up.cs301.game.R.id.quitButton;
import static edu.up.cs301.game.R.id.skipTurnButton;

public class UnoMainActivity extends GameMainActivity {

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 4498;



    // copied from pig lab
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new UnoHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Dumb Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new UnoComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new UnoSmartComputerPlayer(name);
            }
        });


        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Uno", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);
        return defaultConfig;


    }

    @Override
    public LocalGame createLocalGame() {
        return new UnoLocalGame();
    }

}
