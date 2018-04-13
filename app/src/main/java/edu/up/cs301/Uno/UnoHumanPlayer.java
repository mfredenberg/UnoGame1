package edu.up.cs301.Uno;

import android.graphics.*;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.ColorAction;
import edu.up.cs301.Uno.actionMsg.HasUnoAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.Quit;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener,
        View.OnTouchListener {

    // the android activity that we are running
    private GameMainActivity myActivity;

    private Button quitButton;
    private Button hasUnoButton;
    private Button skipTurnButton;
    private UnoGameView unoSurface;
    private TextView playerName;
    private Button redButton;
    private Button greenButton;
    private Button yellowButton;
    private Button blueButton;
    private Button playCardButton;

    /*
    *Ctor
    *
    * @param name
    */
    public UnoHumanPlayer(String name) {
        super(name);

    }

    /*
    * method sets the gui for the human player
    */
    public void setAsGui(GameMainActivity activity) {

        this.myActivity = activity;

        /// sets gui to uno main gui
        this.myActivity.setContentView(R.layout.uno_main_gui);
        this.quitButton = (Button) activity.findViewById(R.id.quitButton);
        this.skipTurnButton = (Button) activity.findViewById(R.id.skipTurnButton);
        this.hasUnoButton = (Button) activity.findViewById(R.id.hasUnoButton);
        this.unoSurface = (UnoGameView) activity.findViewById(R.id.unoSurface);
        this.playerName = (TextView) activity.findViewById(R.id.playerName);
        //no longer crashing yeet
        this.redButton = (Button) activity.findViewById(R.id.red_wild_button);
        this.greenButton = (Button) activity.findViewById(R.id.green_wild_button);
        this.yellowButton = (Button) activity.findViewById(R.id.yellow_wild_button);
        this.blueButton = (Button) activity.findViewById(R.id.blue_wild_button);
        this.playCardButton = (Button) activity.findViewById(R.id.play_card_button);


        this.quitButton.setOnClickListener(this);
        this.hasUnoButton.setOnClickListener(this);
        this.skipTurnButton.setOnClickListener(this);
        this.unoSurface.setOnTouchListener(this);
        //no longer crashing booya
        this.playCardButton.setOnClickListener(this);
        this.redButton.setOnClickListener(this);
        this.greenButton.setOnClickListener(this);
        this.yellowButton.setOnClickListener(this);
        this.blueButton.setOnClickListener(this);


        this.playerName.setText(this.playerName.getText() + "\n" + this.name);
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.unoSurface);
    }

    /*
    * receives the all necessary info about the game
    */
    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            UnoGameState state = (UnoGameState) info;
            this.unoSurface.setHand(state.getPlayerHandAt(this.playerNum));
            this.unoSurface.setTopCard(state.getDiscardPile().getTopCard());
            if(this.unoSurface.getTopCard().getType() == Type.WILD ||
                    this.unoSurface.getTopCard().getType() == Type.WILDDRAW4){
                redButton.setVisibility(View.VISIBLE);
                greenButton.setVisibility(View.VISIBLE);
                blueButton.setVisibility(View.VISIBLE);
                yellowButton.setVisibility(View.VISIBLE);
            }
            else{
                redButton.setVisibility(View.INVISIBLE);
                greenButton.setVisibility(View.INVISIBLE);
                blueButton.setVisibility(View.INVISIBLE);
                yellowButton.setVisibility(View.INVISIBLE);
            }
            this.unoSurface.invalidate();

        }
    }


    /*
    * method listens to the users button clicks and responds accordingly
    */
    public void onClick(View view) {
        if (view.getId() == R.id.quitButton) {
            this.game.sendAction(new Quit(this));
        } else if (view.getId() == R.id.hasUnoButton) {
            this.game.sendAction(new HasUnoAction(this));
        } else if (view.getId() == R.id.skipTurnButton) {
            this.game.sendAction(new SkipTurnAction(this));
        } else if(view.getId() == R.id.play_card_button){
            this.game.sendAction(new PlaceCardAction(this,
                    unoSurface.getCardIndex()));
        }
        //get which card is pressed

        //if discard pile is pressed and the card can be
        //placed, then place card and move on to next player

        //if skip button is pressed, move turn to next player
    }

    /*
    * method responds to a touch from the user
    *
    * @param View, MotionEvent
    * @return boolean
    */
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int index;
        if (view.getId() == R.id.unoSurface && motionEvent.getAction() == motionEvent.ACTION_DOWN) {

            //checks if the spot touched is a card, if it is card is selected
            index = unoSurface.checkSelectedCard((int) motionEvent.getX(), (int) motionEvent.getY());
            if (index != -1) {
                this.unoSurface.invalidate();
            }

        }
        return true;

    }


    public int getPlayerID() {
        return playerNum;
    }


}

