package edu.up.cs301.Uno;

import android.view.View;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoHumanPlayer extends GameHumanPlayer {

    public UnoHumanPlayer(String name){
        super(name);
    }

    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }
}
