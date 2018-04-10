package edu.up.cs301.Uno;

import android.widget.Button;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;

import static edu.up.cs301.game.R.id.hasUnoButton;
import static edu.up.cs301.game.R.id.quitButton;
import static edu.up.cs301.game.R.id.skipTurnButton;

public class UnoMainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame() {return null;}

    //copied Vegdahl's format
    private void initWidgets(){
        //quit button
        Button qButton=(Button) findViewById(quitButton);
        //has uno button
        Button huButton=(Button) findViewById(hasUnoButton);
        //skip button
        Button sButton=(Button) findViewById(skipTurnButton);
    }
}
