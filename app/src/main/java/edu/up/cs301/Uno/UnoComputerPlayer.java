package edu.up.cs301.Uno;

import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoComputerPlayer extends GameComputerPlayer {

    public UnoComputerPlayer(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof UnoGameState)
        {
            this.game.sendAction(new PlaceCardAction(this));
        }

    }
}
