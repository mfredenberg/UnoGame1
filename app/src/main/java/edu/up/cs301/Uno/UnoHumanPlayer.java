package edu.up.cs301.Uno;

import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener
        , View.OnDragListener {

    public UnoHumanPlayer(String name) {
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

    public void onClick(View view) {


        //get which card is pressed

        //if discard pile is pressed and the card can be
        //placed, then place card and move on to next player


    }

    public boolean onDrag(View view, DragEvent dragEvent) {
        return false;
    }
}
