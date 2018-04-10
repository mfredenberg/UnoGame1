package edu.up.cs301.Uno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.HashMap;

import edu.up.cs301.game.R;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoGameView extends SurfaceView {

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
        intHash();
    }

    @Override
    public void onDraw(Canvas canvas) {


    }


    public void intHash() {
        Bitmap blueRev = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.REVERSE, blueRev);
    }




}
