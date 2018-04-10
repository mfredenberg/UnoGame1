package edu.up.cs301.Uno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    // the android activity that we are running
    private GameMainActivity myActivity;

    private Button quitButton;
    private Button hasUnoButton;
    private Button skipTurnButton;
    private UnoGameView unoSurface;
    private HashMap<String, Bitmap> cardPics;


    public UnoHumanPlayer(String name) {
        super(name);
        this.cardPics = new HashMap<String, Bitmap>();
        intHash();
    }


    public void setAsGui(GameMainActivity activity) {

        this.myActivity = activity;

        /// sets gui to uno main gui
        this.myActivity.setContentView(R.layout.uno_main_gui);
        this.quitButton = (Button) activity.findViewById(R.id.quitButton);
        this.skipTurnButton = (Button) activity.findViewById(R.id.skipTurnButton);
        this.hasUnoButton = (Button) activity.findViewById(R.id.hasUnoButton);
        this.unoSurface = (UnoGameView) activity.findViewById(R.id.unoSurface);


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


    public void intHash() {
        Bitmap blueRev = BitmapFactory.decodeResource(this.myActivity.getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.REVERSE, blueRev);
    }
}
