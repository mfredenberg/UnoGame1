package edu.up.cs301.Uno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.HashMap;


/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoGameView extends SurfaceView {

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
    }

    @Override
    public void onDraw(Canvas canvas) {

    }




}
