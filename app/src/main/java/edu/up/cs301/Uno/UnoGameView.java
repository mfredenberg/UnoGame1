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
 * Created by Mason Fredenberg on 4/15/2018.
 * <p>
 * This class sets up the view for the GUI.
 *
 * @author Chris Fishback
 * @author Stelios Popoutsakis
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class UnoGameView extends SurfaceView {

    private static final int CARD_WIDTH = 246;//constant for card width
    private static final int CARD_HEIGHT = 366;//constant for card height
    private ArrayList<ArrayList<Card>> handstoDraw; // All players hand to draw
    private ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
    private Card topCard; //card at the top of discard
    private int currPlayerID = 0;
    private HashMap<String, Bitmap> cardPics; //hastable of card bitmaps
    private ArrayList<RectF> handToSelect = new ArrayList<RectF>(); //holds the Rect objects surrounding each card, letting them be selectable
    private Color currentColor;//current color of play
    private int width;//variable used for the distance between cards drawn
    private ArrayList<String> names;
    private int currentDot = 0; //variable to hold where the "whose turn" dot


    /*
    *Constructors required by extending SurfaceView
     */
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

    /*
    *method called when any of the three constructors is called to initialize card bitmaps and
    * ensure it will draw
     */
    private void startUp() {
        setWillNotDraw(false);
        this.cardPics = new HashMap<String, Bitmap>();
        initHash();


    }

    /*
     * method draws the screen
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {
        if (this.handstoDraw != null) {

            drawCPUHands(canvas);

            double heightMul = .64;
            drawCard(canvas, this.topCard, getWidth() / 2 - 121, getHeight() / 2 - 200);
            width = 0;

            for (int i = 0; i < this.handstoDraw.get(this.currPlayerID).size(); i++) {
                if (i == 9) {
                    width = 0;
                    heightMul = .82;
                }
                if (this.isSelected.get(i)) {
                    drawCard(canvas, this.handstoDraw.get(this.currPlayerID).get(i), width, (int) (getHeight() * heightMul - 30));
                    width += 195;
                    continue;
                }


                if (i < 9) {
                    drawCard(canvas, this.handstoDraw.get(this.currPlayerID).get(i), width, (int) (getHeight() * heightMul));
                    width += 195;
                } else if (i < 18) {
                    drawCard(canvas, this.handstoDraw.get(this.currPlayerID).get(i), width, (int) (getHeight() * heightMul));
                    width += 195;
                }
            }

        }


    }

    /*
     * method draws the correct card based on color and type in the fiven location
     *
     * @param canvas
     * @param toDraw
     *          Card object of card being drawn
     * @param x
     *          x coordinate of where it will be drawn
     * @param y
     *          y coordinate of where it will be drawn
     */
    public void drawCard(Canvas canvas, Card toDraw, int x, int y) {
        Bitmap card;
        if (toDraw == null) {
            card = this.cardPics.get("cover");
            canvas.drawBitmap(card, x, y, null);
            return;
        }
        if (toDraw.getColor() == null)
            card = this.cardPics.get("" + toDraw.getType());
        else
            card = this.cardPics.get("" + toDraw.getColor() + toDraw.getType());
        if (card != null) {
            canvas.drawBitmap(card, x, y, null);
        }
    }

    /*
     * Initializes hashtable of bitmaps for cards based on their type and color
     */
    public void initHash() {

        //red cards----------------------------------------------------------------------\\
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

        //yellow cards----------------------------------------------------------------------\\
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

        //green cards----------------------------------------------------------------------\\
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

        //blue cards----------------------------------------------------------------------\\
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

        //cover card
        Bitmap coverCard = BitmapFactory.decodeResource(getResources(), R.drawable.uno_cover_card);
        this.cardPics.put("cover", coverCard);

    }

    /*
     * method initializes and syncs all three ArrayLists so the index's are aligned
     * @param hands
     *          arrayList holding each player's hand
     * @param playerID
     *          currect player's number ID
     * @param names
     *          arrayList that holds all the player names
     * @param currentColor
     *          current playable color
     */
    public void setHand(ArrayList<ArrayList<Card>> hands, int playerID, ArrayList<String> names, Color currentColor) {
        this.handstoDraw = hands;
        this.currentColor = currentColor;
        this.currPlayerID = playerID;
        this.names = names;
        this.isSelected.clear();
        this.handToSelect.clear();
        width = 0;

        for (int i = 0; i < this.handstoDraw.get(playerID).size(); i++) {
            this.isSelected.add(i, false);
            if (i == 9) width = 0;
            if (i < 9) {
                this.handToSelect.add(new RectF(width, (int) (getHeight() * .64), width + CARD_WIDTH, (int) (getHeight() * .62)
                        + CARD_HEIGHT));
                width += 195;
            } else if (i < 18) {
                this.handToSelect.add(new RectF(width, (int) (getHeight() * .82), width + CARD_WIDTH, (int) (getHeight() * .82)
                        + CARD_HEIGHT));
                width += 195;
            }

        }
    }

    /*
     * method checks the card at the given position as selected
     * @param x
     *          x coordinate of where the screen was touched
     * @param y
     *          y coordinate of where the screen was touched
     * @return
     *          the index of the card that was selected
     *          -1 if no card was selected
     */
    public int checkSelectedCard(int x, int y) {
        for (int i = 0; i < handToSelect.size(); i++) {
            if (handToSelect.get(i).contains(x, y)) {
                selectCard(i);
                return i;
            }
        }
        return -1;
    }

    /*
     * method changes the value of the boolean at the given index to be true rather than false
     * marking it as selected
     * @param index
     *          the index of the card selected according to where the player touched the screen
     */
    public void selectCard(int index) {
        if (isSelected.get(index)) {
            isSelected.set(index, false);
            return;
        }
        for (int i = 0; i < isSelected.size(); i++) isSelected.set(i, false);
        isSelected.set(index, true);
    }

    /*
     * method returns the index of the selected card
     * @return
     *          the number of the index in the ArrayList where the selected card is
     *          if there is no selected card, -1 is returned
     */
    public int getCardIndex() {
        for (int i = 0; i < isSelected.size(); i++) {
            if (isSelected.get(i)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * method checks if a card is selected
     * @return
     *          true if there is a selected card; false if no selected card
     */
    public boolean checkIsASelection() {

        for (int i = 0; i < isSelected.size(); i++) {
            if (isSelected.get(i)) {
                return true;
            }
        }
        return false;
    }

    /*
     * method draws the other players' hands
     *
     * @param canvas
     */
    public void drawCPUHands(Canvas canvas) {
        int height = 10;
        int circleXPos = 0;
        Paint currentColor = new Paint();

        for (int i = 0; i < this.handstoDraw.size(); i++) {
            if (i == this.currPlayerID) continue;
            int j = 0;
            int cpuCardCount = 0;

            for (Card c : this.handstoDraw.get(i)) {
                if (cpuCardCount == 40) break;
                drawCard(canvas, null, 10 + 5 * j, height);
                j += 5;

                //save x position for the color circle
                if (i == this.currentDot)
                    circleXPos = (10 + 5 * j);

                cpuCardCount++;
            }

            //tell the GUI how many cards the player has
            Paint cpuText = new Paint();
            cpuText.setTextSize(40);
            if (this.handstoDraw.size() == 1) cpuText.setColor(android.graphics.Color.RED);
            canvas.drawText(this.names.get(i) + " Has " +
                            this.handstoDraw.get(i).size() + " Cards in their Hand"
                    , 10, height + CARD_HEIGHT + 40, cpuText);

            // lets us know what color the top card is and updates the "current color circle"
            //to reflect that color.
            switch (this.currentColor) {
                case BLUE:
                    currentColor.setColor(android.graphics.Color.BLUE);
                    break;
                case RED:
                    currentColor.setColor(android.graphics.Color.RED);
                    break;
                case GREEN:
                    currentColor.setColor(android.graphics.Color.GREEN);
                    break;
                case YELLOW:
                    currentColor.setColor(android.graphics.Color.YELLOW);
                    break;

            }

            //place the dot based on who's turn it is and how many cards they have
            if(this.currentDot == i)
            {

                //extend the dot based on card width
                circleXPos += CARD_WIDTH + 20;

                //draw the dot
                canvas.drawCircle(circleXPos, (float) (height + CARD_HEIGHT/2), 10, currentColor);

            }

            //extend the dot based on card width
            height += CARD_HEIGHT + 50;


        }


        if (this.currentDot == this.currPlayerID) //if it is the user
           canvas.drawCircle(getWidth() / 2, getHeight() / 2 - 220, 10, currentColor);
    }


    /*
     * Appropriate getters and setters
     */
    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public Card getTopCard() {
        return this.topCard;
    }

    public ArrayList<Card> getHumanplayerHand() {
        return this.handstoDraw.get(this.currPlayerID);
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void setCurrentDot(int initDotPlayer) {
        this.currentDot = initDotPlayer;
    }

}
