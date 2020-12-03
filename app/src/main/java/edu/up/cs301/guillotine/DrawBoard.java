package edu.up.cs301.guillotine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

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

    @Override
    public void onDraw(Canvas canvas) {

        Bitmap draw;

        if(state == null){
            return;
        }

        for (int i = 0; i < state.getP0Field().size(); i++) {
            if (state.getP0Field().get(i).getId().equals("Callous")) {
                canvas.drawRect(10.0f, 670.0f, 200.0f, 770.0f, grey);
                canvas.drawText("Discard", 10.0f,715.0f, black);
                canvas.drawText("Callous", 10.0f,766.0f, black);
            }
        }



        canvas.drawRect(10.0f, 970.0f, 170.0f, 1070.0f, grey);
        canvas.drawText("Skip", 30.0f,1030.0f, black);
        canvas.drawRect(10.0f, 860.0f, 170.0f, 960.0f, grey);
        canvas.drawText("Accept", 10.0f,930.0f, black);

        grey.setTextSize(50.0f);
        canvas.drawText("Day: " + state.getDayNum(), 10.0f, 400.0f, grey);
        canvas.drawText("Player 1: " + state.getP1Score(), 10.0f, 200.0f, grey);
        canvas.drawText("Player 0: " + state.getP0Score(), 10.0f, 300.0f, grey);


        grey.setTextSize(50.0f);
        canvas.drawText("GUILLOTINE GAME ", 10.0f, 100.0f, grey);

        //Hand Arrow
        if(state.getP0Hand().size() > 7){
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 100, 100, true);
            canvas.drawBitmap(draw, 250.0f, 890.0f, null);
        }

        //p0field arrow
        if(state.getP0Field().size() > 12){
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
            canvas.drawBitmap(draw, 300.0f, 675.0f, null);
        }

        //p1field arrow
        if(state.getP1Field().size() > 12){
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
            draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
            canvas.drawBitmap(draw, 300.0f, 175.0f, null);
        }

        //Draws the P0 Hand cards on the UI
        float left = 1700;
        for(int i = 0; i < state.getP0Hand().size() && i < 7; i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 800.0f, null); //800
            left -= 220.0f;
        }

        //Draws them P0 field cards on UI
        left = 1800;
        for(int i = 0; i < state.getP0Field().size() && i < 12; i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 650.0f, null); // 650
            left -= 120;
        }

        // Draws the Noble line cards on UI
        left = 1700;
        for(int i = 0; i < state.getNobleLine().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getNobleLine().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 350.0f, null); //350
            left -= 100;
        }

        //P1 field
        left = 1800;
        for(int i = 0; i < state.getP1Field().size() && i < 12; i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP1Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 150.0f, null); //150
            left -= 120;
        }


        //Draws the Action Deck on the screen
        left = 10;
        for(int i = 0; i < state.getDeckAction().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(),R.drawable.action_card_back);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 710.0f, null);

        }

        //draws the discard deck on the screen
        for(int i = 0; i < state.getDeckDiscard().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 560.0f, null);

        }
       //draws the noble deck on the screen
        for(int i = 0; i < state.getDeckNoble().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 415.0f, null);

        }

        //Displays the information on the UI that tells the player what phase it is
        //And what action to take in each phase.
        if(state.getTurnPhase() == 0){
            canvas.drawText("Action Card Phase", 800.0f, 50.0f, grey);
            canvas.drwText("click action card", 700.0f, 90.0f, grey);
        }

        if(state.getTurnPhase() == 1){
            canvas.drawText("Take Noble Phase", 800.0f, 50.0f, grey);

            canvas.drawText("Click accept", 700.0f, 90.0f, grey);
        }
        if(state.getTurnPhase() == 2){
            canvas.drawText("Draw card Phase", 800.0f, 50.0f, grey );
            canvas.drawText("Click card from the deck", 700.0f, 90.0f, grey);
        }
        if(state.getTurnPhase() == 3){
            canvas.drawText("Select Noble in Line", 800.0f, 50.0f, grey);
            canvas.drawText("click noble card", 700.0f, 90.0f, grey);
        }

        if(state.getTurnPhase() == 4){
            canvas.drawText("Select ", 800.0f, 50.0f, grey);
            canvas.drawText("1", 50.0f, 400.0f, choice);
            canvas.drawText("2", 1050.0f, 400.0f, choice);
        }

        if(state.getTurnPhase() == 5){
            canvas.drawText("Select", 800.0f, 50.0f, grey);
            if(state.getArrival()){
                left = 50.0f;
                for(int i = 0; i < state.getDeckNoble().size() && i < 3; i++){
                    draw = BitmapFactory.decodeResource(getResources(), state.getDeckNoble().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 400, 560, true);
                    canvas.drawBitmap(draw, left, 120.0f, null);
                    left += 700.0f;
                }
            } else {
                canvas.drawText("1", 50.0f, 400.0f, choice);
                canvas.drawText("2", 720.0f, 400.0f, choice);
                canvas.drawText("3", 1375.0f, 400.0f, choice);
            }
        }

        if(state.getTurnPhase() == 6){
            canvas.drawText("Select Card to Discard in Opponent's Hand", 800.0f, 50.0f, grey);
            if(state.getPlayerTurn() == 0){
                left = 1570;
                for(int i = 0; i < state.getP1Hand().size() && i < 4; i++){
                    draw = BitmapFactory.decodeResource(getResources(), state.getP1Hand().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if(state.getP1Hand().size() > 4){
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            } else{
                left = 1570;
                for(int i = 0; i < state.getP0Hand().size() && i < 4; i++){
                    draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if(state.getP0Hand().size() > 4){
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            }

        }

        if(state.getTurnPhase() == 7){
            left = 1570;
            for(int i = 0; i < state.getDeckDiscard().size() && i < 4; i++){
                draw = BitmapFactory.decodeResource(getResources(), state.getDeckDiscard().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                canvas.drawBitmap(draw, left, 360.0f, null);
                left -= 370;
            }
            if(state.getDeckDiscard().size() > 4){
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                canvas.drawBitmap(draw, 300.0f, 530.0f, null);
            }
        }





    }
}
