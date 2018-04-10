package edu.up.cs301.Uno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;

import edu.up.cs301.game.R;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoGameView extends SurfaceView {

    private ArrayList<Card> handtoDraw;
    private Card topCard;
    private HashMap<String, Bitmap> cardPics;

    public UnoGameView(Context context) {
        super(context);
        startUp();
    }

    public UnoGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        startUp();
    }

    public UnoGameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        startUp();
    }

    private void startUp() {
        setWillNotDraw(false);
        this.cardPics = new HashMap<String, Bitmap>();
        initHash();
    }

    @Override
    public void onDraw(Canvas canvas) {


        int numCard = 1;

        canvas.drawBitmap(this.cardPics.get("" + Color.BLUE + Type.REVERSE),
                (int) (getWidth() * .5), (int) (getHeight() * .3), null);

        if (this.handtoDraw == null) return;
        for (Card card : this.handtoDraw) {
            if (numCard < 11) {

                canvas.drawBitmap(this.cardPics.get("" + Color.BLUE + Type.REVERSE),
                        (int) (getWidth() * (.1 * numCard) - 25), (int) (getHeight() * .7), null);

            } else if (numCard > 10) {

                canvas.drawBitmap(this.cardPics.get("" + Color.BLUE + Type.REVERSE),
                        (int) (getWidth() * (.1 * numCard) - 25), (int) (getHeight() * .8), null);

            }
            numCard++;
        }


    }


    public void initHash() {

        Bitmap redZero = BitmapFactory.decodeResource(getResources(), R.drawable.red_zero);
        this.cardPics.put("" + Color.RED + Type.ZERO, redZero);
        Bitmap redOne = BitmapFactory.decodeResource(getResources(), R.drawable.red_one);
        this.cardPics.put("" + Color.RED + Type.ONE, redOne);
        Bitmap redTwo = BitmapFactory.decodeResource(getResources(), R.drawable.red_two);
        this.cardPics.put("" + Color.RED + Type.TWO, redTwo);
        Bitmap redThree = BitmapFactory.decodeResource(getResources(), R.drawable.red_three);
        this.cardPics.put("" + Color.RED + Type.THREE, redThree);
        Bitmap redFour = BitmapFactory.decodeResource(getResources(), R.drawable.red_four);
        this.cardPics.put("" + Color.RED + Type.FOUR, redFour);
        Bitmap redFive = BitmapFactory.decodeResource(getResources(), R.drawable.red_five);
        this.cardPics.put("" + Color.RED + Type.FIVE, redFive);
        Bitmap redRev = BitmapFactory.decodeResource(getResources(), R.drawable.red_reverse);
        this.cardPics.put("" + Color.RED + Type.REVERSE, redRev);

        Bitmap blueRev = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.REVERSE, blueRev);
        Bitmap blueOne = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.ONE, blueRev);
        Bitmap blueTwo = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.TWO, blueRev);
        Bitmap blueThree = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.THREE, blueRev);
        Bitmap blueFour = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.FOUR, blueRev);
    }

    public void setHand(ArrayList<Card> hand) {
        this.handtoDraw = hand;
    }


    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }
}
