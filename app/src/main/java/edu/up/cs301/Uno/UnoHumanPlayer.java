package edu.up.cs301.Uno;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

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


    public UnoHumanPlayer(String name) {
        super(name);


    }


    public void setAsGui(GameMainActivity activity) {

        this.myActivity = activity;

        /// sets gui to uno main gui
        this.myActivity.setContentView(R.layout.uno_main_gui);
        this.quitButton = (Button) activity.findViewById(R.id.quitButton);
        this.skipTurnButton = (Button) activity.findViewById(R.id.skipTurnButton);
        this.hasUnoButton = (Button) activity.findViewById(R.id.hasUnoButton);
        this.unoSurface = (UnoGameView) activity.findViewById(R.id.unoSurface);

        this.quitButton.setOnClickListener(this);
        this.hasUnoButton.setOnClickListener(this);
        this.skipTurnButton.setOnClickListener(this);
        this.unoSurface.setOnTouchListener(this);


    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.unoSurface);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            UnoGameState state = (UnoGameState) info;
            if (state.getTurn() == 0) {
                this.unoSurface.setHand(state.getPlayerHandAt(0));
                this.unoSurface.setTopCard(state.getDiscardPile().getTopCard());
                this.unoSurface.invalidate();
            }
            else if(state.getTurn() == 1)
            {
                this.unoSurface.setTopCard(state.getDiscardPile().getTopCard());
                this.unoSurface.invalidate();
            }
        }


    }

    public void onClick(View view) {
        if (view.getId() == R.id.quitButton) {
            this.game.sendAction(new Quit(this));
        } else if (view.getId() == R.id.hasUnoButton) {
            this.game.sendAction(new HasUnoAction(this));
        } else if (view.getId() == R.id.skipTurnButton) {
            this.game.sendAction(new SkipTurnAction(this));
        }


        //get which card is pressed

        //if discard pile is pressed and the card can be
        //placed, then place card and move on to next player

        //if skip button is pressed, move turn to next player

    }


    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (view.getId() == R.id.unoSurface) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.game.sendAction(new PlaceCardAction(this));
                    return true;

            }

        }
        return false;
    }
}
