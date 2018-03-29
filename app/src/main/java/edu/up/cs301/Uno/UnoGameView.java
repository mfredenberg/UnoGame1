package edu.up.cs301.Uno;

import android.content.Context;
import android.util.AttributeSet;
import edu.up.cs301.animation.AnimationSurface;

/**
 * Created by fredenbe20 on 3/27/2018.
 */

public class UnoGameView extends AnimationSurface {

    public UnoGameView(Context context){
        super(context);
        setWillNotDraw(false);
    }
    public UnoGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }



}
