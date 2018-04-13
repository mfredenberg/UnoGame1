package edu.up.cs301.Uno;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

    private static final int CARD_WIDTH = 246;//constant for card width
    private static final int CARD_HEIGHT = 366;//constant for card height
    private ArrayList<Card> handtoDraw;
    private ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
    private ArrayList<Integer> oppHands;
    private Card topCard;
    private HashMap<String, Bitmap> cardPics;
    private ArrayList<RectF> handToSelect = new ArrayList<RectF>(); //holds the Rect objects surrounding each card, letting them be selectable

    int width;

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
        if(this.oppHands != null)
        {
            Paint p = new Paint();
            p.setColor(android.graphics.Color.BLACK);
            p.setTextSize(50);
            canvas.drawText("CPU Hand" + "\n" + this.oppHands.get(0),50,50,p);
        }


        if (this.handtoDraw != null) {
            double heightMul = .5;
            drawCard(canvas, this.topCard, getWidth() / 2 - 121, getHeight() / 2 - 700);
            width = 0;
            for (int i = 0; i < this.handtoDraw.size(); i++) {
                if (i == 9) {
                    width = 0;
                    heightMul = .7;
                }
                if (this.isSelected.get(i)) {
                    drawCard(canvas, this.handtoDraw.get(i), width, (int) (getHeight() * heightMul - 30));
                    width += 195;
                    continue;
                }


                if (i < 9) {
                    drawCard(canvas, this.handtoDraw.get(i), width, (int) (getHeight() * heightMul));
                    width += 195;
                } else if (i < 15) {
                    drawCard(canvas, this.handtoDraw.get(i), width, (int) (getHeight() * heightMul));
                    width += 195;
                }
            }

        }


    }

    public void drawCard(Canvas canvas, Card toDraw, int x, int y) {
        Bitmap card;
        if (toDraw.getColor() == null)
            card = this.cardPics.get("" + toDraw.getType());
        else
            card = this.cardPics.get("" + toDraw.getColor() + toDraw.getType());
        if (card != null) {
            canvas.drawBitmap(card, x, y, null);
        }
    }


    public void initHash() {
        //red cards
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
        Bitmap redSix = BitmapFactory.decodeResource(getResources(), R.drawable.red_six);
        this.cardPics.put("" + Color.RED + Type.SIX, redSix);
        Bitmap redSeven = BitmapFactory.decodeResource(getResources(), R.drawable.red_seven);
        this.cardPics.put("" + Color.RED + Type.SEVEN, redSeven);
        Bitmap redEight = BitmapFactory.decodeResource(getResources(), R.drawable.red_eight);
        this.cardPics.put("" + Color.RED + Type.EIGHT, redEight);
        Bitmap redNine = BitmapFactory.decodeResource(getResources(), R.drawable.red_nine);
        this.cardPics.put("" + Color.RED + Type.NINE, redNine);
        Bitmap redSkip = BitmapFactory.decodeResource(getResources(), R.drawable.red_skip);
        this.cardPics.put("" + Color.RED + Type.SKIP, redSkip);
        Bitmap redDraw2 = BitmapFactory.decodeResource(getResources(), R.drawable.red_draw2);
        this.cardPics.put("" + Color.RED + Type.PLUS2, redDraw2);
        Bitmap redRev = BitmapFactory.decodeResource(getResources(), R.drawable.red_reverse);
        this.cardPics.put("" + Color.RED + Type.REVERSE, redRev);
        //yellow cards
        Bitmap yellowZero = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_zero);
        this.cardPics.put("" + Color.YELLOW + Type.ZERO, yellowZero);
        Bitmap yellowOne = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_one);
        this.cardPics.put("" + Color.YELLOW + Type.ONE, yellowOne);
        Bitmap yellowTwo = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_two);
        this.cardPics.put("" + Color.YELLOW + Type.TWO, yellowTwo);
        Bitmap yellowThree = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_three);
        this.cardPics.put("" + Color.YELLOW + Type.THREE, yellowThree);
        Bitmap yellowFour = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_four);
        this.cardPics.put("" + Color.YELLOW + Type.FOUR, yellowFour);
        Bitmap yellowFive = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_five);
        this.cardPics.put("" + Color.YELLOW + Type.FIVE, yellowFive);
        Bitmap yellowSix = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_six);
        this.cardPics.put("" + Color.YELLOW + Type.SIX, yellowSix);
        Bitmap yellowSeven = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_seven);
        this.cardPics.put("" + Color.YELLOW + Type.SEVEN, yellowSeven);
        Bitmap yellowEight = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_eight);
        this.cardPics.put("" + Color.YELLOW + Type.EIGHT, yellowEight);
        Bitmap yellowNine = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_nine);
        this.cardPics.put("" + Color.YELLOW + Type.NINE, yellowNine);
        Bitmap yellowSkip = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_skip);
        this.cardPics.put("" + Color.YELLOW + Type.SKIP, yellowSkip);
        Bitmap yellowDraw2 = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_draw2);
        this.cardPics.put("" + Color.YELLOW + Type.PLUS2, yellowDraw2);
        Bitmap yellowRev = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_reverse);
        this.cardPics.put("" + Color.YELLOW + Type.REVERSE, yellowRev);
        //green cards
        Bitmap greenZero = BitmapFactory.decodeResource(getResources(), R.drawable.green_zero);
        this.cardPics.put("" + Color.GREEN + Type.ZERO, greenZero);
        Bitmap greenOne = BitmapFactory.decodeResource(getResources(), R.drawable.green_one);
        this.cardPics.put("" + Color.GREEN + Type.ONE, greenOne);
        Bitmap greenTwo = BitmapFactory.decodeResource(getResources(), R.drawable.green_two);
        this.cardPics.put("" + Color.GREEN + Type.TWO, greenTwo);
        Bitmap greenThree = BitmapFactory.decodeResource(getResources(), R.drawable.green_three);
        this.cardPics.put("" + Color.GREEN + Type.THREE, greenThree);
        Bitmap greenFour = BitmapFactory.decodeResource(getResources(), R.drawable.green_four);
        this.cardPics.put("" + Color.GREEN + Type.FOUR, greenFour);
        Bitmap greenFive = BitmapFactory.decodeResource(getResources(), R.drawable.green_five);
        this.cardPics.put("" + Color.GREEN + Type.FIVE, greenFive);
        Bitmap greenSix = BitmapFactory.decodeResource(getResources(), R.drawable.green_six);
        this.cardPics.put("" + Color.GREEN + Type.SIX, greenSix);
        Bitmap greenSeven = BitmapFactory.decodeResource(getResources(), R.drawable.green_seven);
        this.cardPics.put("" + Color.GREEN + Type.SEVEN, greenSeven);
        Bitmap greenEight = BitmapFactory.decodeResource(getResources(), R.drawable.green_eight);
        this.cardPics.put("" + Color.GREEN + Type.EIGHT, greenEight);
        Bitmap greenNine = BitmapFactory.decodeResource(getResources(), R.drawable.green_nine);
        this.cardPics.put("" + Color.GREEN + Type.NINE, greenNine);
        Bitmap greenSkip = BitmapFactory.decodeResource(getResources(), R.drawable.green_skip);
        this.cardPics.put("" + Color.GREEN + Type.SKIP, greenSkip);
        Bitmap greenDraw2 = BitmapFactory.decodeResource(getResources(), R.drawable.green_draw2);
        this.cardPics.put("" + Color.GREEN + Type.PLUS2, greenDraw2);
        Bitmap greenRev = BitmapFactory.decodeResource(getResources(), R.drawable.green_reverse);
        this.cardPics.put("" + Color.GREEN + Type.REVERSE, greenRev);
        //blue cards
        Bitmap blueZero = BitmapFactory.decodeResource(getResources(), R.drawable.blue_zero);
        this.cardPics.put("" + Color.BLUE + Type.ZERO, blueZero);
        Bitmap blueOne = BitmapFactory.decodeResource(getResources(), R.drawable.blue_one);
        this.cardPics.put("" + Color.BLUE + Type.ONE, blueOne);
        Bitmap blueTwo = BitmapFactory.decodeResource(getResources(), R.drawable.blue_two);
        this.cardPics.put("" + Color.BLUE + Type.TWO, blueTwo);
        Bitmap blueThree = BitmapFactory.decodeResource(getResources(), R.drawable.blue_three);
        this.cardPics.put("" + Color.BLUE + Type.THREE, blueThree);
        Bitmap blueFour = BitmapFactory.decodeResource(getResources(), R.drawable.blue_four);
        this.cardPics.put("" + Color.BLUE + Type.FOUR, blueFour);
        Bitmap blueFive = BitmapFactory.decodeResource(getResources(), R.drawable.blue_five);
        this.cardPics.put("" + Color.BLUE + Type.FIVE, blueFive);
        Bitmap blueSix = BitmapFactory.decodeResource(getResources(), R.drawable.blue_six);
        this.cardPics.put("" + Color.BLUE + Type.SIX, blueSix);
        Bitmap blueSeven = BitmapFactory.decodeResource(getResources(), R.drawable.blue_seven);
        this.cardPics.put("" + Color.BLUE + Type.SEVEN, blueSeven);
        Bitmap blueEight = BitmapFactory.decodeResource(getResources(), R.drawable.blue_eight);
        this.cardPics.put("" + Color.BLUE + Type.EIGHT, blueEight);
        Bitmap blueNine = BitmapFactory.decodeResource(getResources(), R.drawable.blue_nine);
        this.cardPics.put("" + Color.BLUE + Type.NINE, blueNine);
        Bitmap blueSkip = BitmapFactory.decodeResource(getResources(), R.drawable.blue_skip);
        this.cardPics.put("" + Color.BLUE + Type.SKIP, blueSkip);
        Bitmap blueDraw2 = BitmapFactory.decodeResource(getResources(), R.drawable.blue_draw2);
        this.cardPics.put("" + Color.BLUE + Type.PLUS2, blueDraw2);
        Bitmap blueRev = BitmapFactory.decodeResource(getResources(), R.drawable.blue_reverse);
        this.cardPics.put("" + Color.BLUE + Type.REVERSE, blueRev);


        //wild cards
        Bitmap wild = BitmapFactory.decodeResource(getResources(), R.drawable.wild);
        this.cardPics.put("" + Type.WILD, wild);
        Bitmap wildDrawFour = BitmapFactory.decodeResource(getResources(), R.drawable.wild_draw_four);
        this.cardPics.put("" + Type.WILDDRAW4, wildDrawFour);
        // Bitmap nullCard=

        // Bitmap nullCard=

    }

    //syncs all three ArrayLists so the index's are aligned
    public void setHand(ArrayList<Card> hand) {
        this.handtoDraw = hand;
        this.isSelected.clear();
        this.handToSelect.clear();
        width = 0;
        for (int i = 0; i < this.handtoDraw.size(); i++) {
            this.isSelected.add(i, false);
            if (i == 9) width = 0;
            if (i < 9) {
                this.handToSelect.add(new RectF(width, (int) (getHeight() * .5), width + CARD_WIDTH, (int) (getHeight() * .5)
                        + CARD_HEIGHT));
                width += 195;
            } else if (i < 15) {
                this.handToSelect.add(new RectF(width, (int) (getHeight() * .7), width + CARD_WIDTH, (int) (getHeight() * .7)
                        + CARD_HEIGHT));
                width += 195;
            }

        }
    }


    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public Card getTopCard(){ return this.topCard; }
    public ArrayList<Card> getHumanplayerHand() {
        return this.handtoDraw;

    }

    public int checkSelectedCard(int x, int y) {
        for (int i = 0; i < handToSelect.size(); i++) {
            if (handToSelect.get(i).contains(x, y)) {
                selectCard(i);
                return i;
            }
        }
        return -1;
    }

    public void selectCard(int index) {
        if(isSelected.get(index))
        {
            isSelected.set(index,false);
            return;
        }
        for (int i = 0; i < isSelected.size(); i++) isSelected.set(i, false);
        isSelected.set(index, true);
    }

    public int getCardIndex(){
        for(int i = 0; i < isSelected.size(); i++){
            if(isSelected.get(i)){
                return i;
            }
        }
        return -1;
    }

    public void setDrawCpuHand(ArrayList<Integer> oppHands)
    {
        this.oppHands = oppHands;
    }

    public boolean checkIsASelection()
    {

        for(int i = 0; i < isSelected.size(); i++)
        {
            if (isSelected.get(i))
            {
                return true;
            }
        }
        return false;
    }
}
