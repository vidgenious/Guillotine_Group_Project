package edu.up.cs301.guillotine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.Collections;

import edu.up.cs301.game.GameFramework.utilities.FlashSurfaceView;
import edu.up.cs301.game.R;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Beta November 2020
 */

/**
 *  This class draws the gamebaord and acts as GUI creator
 */
public class DrawBoard extends FlashSurfaceView {

    private GuillotineState state;
    private Paint grey = new Paint();
    private Paint black = new Paint();
    private Paint choice = new Paint();
    private int humanPlayerLoc;

    public DrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        grey.setColor(Color.WHITE);
        black.setColor(Color.BLACK);
        black.setTextSize(50.0f);
        choice.setColor(Color.RED);
        choice.setTextSize(400.0f);

    }

    public void setState(GuillotineState state) {
        this.state = state;
    }

    //sets the instance var to equal the human player number
    public void setHumanPlayerLoc(int human){
        this.humanPlayerLoc = human;
    }


    @Override
    public void onDraw(Canvas canvas) {

        Bitmap draw;

        if (state == null) {
            return;
        }

        for (int i = 0; i < state.getP0Field().size(); i++) {
            if (state.getP0Field().get(i).getId().equals("Callous")) {
                canvas.drawRect(10.0f, 670.0f, 200.0f, 770.0f, grey);
                canvas.drawText("Discard", 10.0f, 715.0f, black);
                canvas.drawText("Callous", 10.0f, 766.0f, black);
            }
        }


        canvas.drawRect(10.0f, 970.0f, 170.0f, 1070.0f, grey);
        canvas.drawText("Skip", 30.0f, 1030.0f, black);
        canvas.drawRect(10.0f, 860.0f, 170.0f, 960.0f, grey);
        canvas.drawText("Accept", 10.0f, 930.0f, black);

        grey.setTextSize(50.0f);
        canvas.drawText("Day: " + state.getDayNum(), 10.0f, 400.0f, grey);
        canvas.drawText("Player 1: " + state.getP1Score(), 10.0f, 200.0f, grey);
        canvas.drawText("Player 0: " + state.getP0Score(), 10.0f, 300.0f, grey);


        grey.setTextSize(50.0f);
        canvas.drawText("GUILLOTINE GAME ", 10.0f, 100.0f, grey);

        //checks if human is player 0

        //Hand Arrow
        if (state.getP0Hand().size() > 7) {
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 100, 100, true);
            canvas.drawBitmap(draw, 250.0f, 890.0f, null);
        }

        //p0field arrow
        if (state.getP0Field().size() > 12) {
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
            canvas.drawBitmap(draw, 300.0f, 675.0f, null);
        }

        //p1field arrow
        if (state.getP1Field().size() > 12) {
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
            canvas.drawBitmap(draw, 300.0f, 175.0f, null);
        }

        //P0 Hand
        float left = 1700;
        for (int i = 0; i < state.getP0Hand().size() && i < 7; i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 800.0f, null); //800
            left -= 220.0f;
        }

        //P0 field
        left = 1800;
        for (int i = 0; i < state.getP0Field().size() && i < 12; i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 650.0f, null); // 650
            left -= 120;
        }

        //Noble line
        left = 1700;
        Collections.reverse(state.getNobleLine());
        for (int i = 0; i < state.getNobleLine().size(); i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getNobleLine().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 350.0f, null); //350
            left -= 100;
        }

        //P1 field
        left = 1800;
        for (int i = 0; i < state.getP1Field().size() && i < 12; i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getP1Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 150.0f, null); //150
            left -= 120;
        }

        //Action Deck
        left = 10;
        for (int i = 0; i < state.getDeckAction().size(); i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getDeckAction().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 710.0f, null);
            //left -= 10;
        }

        //discard deck
        for (int i = 0; i < state.getDeckDiscard().size(); i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getDeckDiscard().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 575.0f, null);
            //left -= 10;
        }
        //noble deck
        for (int i = 0; i < state.getDeckNoble().size(); i++) {
            draw = BitmapFactory.decodeResource(getResources(), state.getDeckNoble().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 425.0f, null);
            //left -= 100;
        }


        if (state.getTurnPhase() == 0) {
            canvas.drawText("Action Card Phase:", 800.0f, 50.0f, grey);
            canvas.drawText("Play an action card", 800.0f, 115.0f, grey);
        }

        if (state.getTurnPhase() == 1) {
            canvas.drawText("Take Noble Phase", 800.0f, 50.0f, grey);
            canvas.drawText("Take a noble card from noble line deck", 800.0f, 115.0f, grey);
        }
        if (state.getTurnPhase() == 2) {
            canvas.drawText("Draw card Phase", 800.0f, 50.0f, grey);
            canvas.drawText("Draw a card from the deck", 800.0f, 115.0f, grey);
        }
        if (state.getTurnPhase() == 3) {
            canvas.drawText("Select Noble in Line", 800.0f, 50.0f, grey);
            canvas.drawText("Select a noble card from the noble line", 800.0f, 115.0f, grey);
        }

        if (state.getTurnPhase() == 4) {
            canvas.drawText("Select ", 800.0f, 50.0f, grey);
            canvas.drawText("(Select 1 or 2 on the screen)", 800.0f, 40.0f, grey);
            canvas.drawText("1", 50.0f, 400.0f, choice);
            canvas.drawText("2", 1050.0f, 400.0f, choice);
        }

        if (state.getTurnPhase() == 5) {
            canvas.drawText("Select", 800.0f, 50.0f, grey);
            canvas.drawText("(Select 1 or 2 or 3 on the screen)", 800.0f, 40.0f, grey);
            if (state.getArrival()) {
                left = 50.0f;
                for (int i = 0; i < state.getDeckNoble().size() && i < 3; i++) {
                    draw = BitmapFactory.decodeResource(getResources(), state.getDeckNoble().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 400, 560, true);
                    canvas.drawBitmap(draw, left, 120.0f, null); //800
                    left += 700.0f;
                }
            } else {
                canvas.drawText("1", 50.0f, 400.0f, choice);
                canvas.drawText("2", 720.0f, 400.0f, choice);
                canvas.drawText("3", 1375.0f, 400.0f, choice);
            }
        }

        if (state.getTurnPhase() == 6) {
            canvas.drawText("Select Card in Hand", 800.0f, 50.0f, grey);
            canvas.drawText("(Select a card in player's hand)", 800.0f, 30.0f, grey);
        }
    }
}
