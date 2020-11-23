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
 * @version Alpha November 2020
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

        if(state == null){
            return;
        }
       //canvas.setBackgroundColor()
        canvas.drawRect(10.0f, 970.0f, 170.0f, 1070.0f, grey);
        canvas.drawText("Skip", 30.0f,1030.0f, black);
        canvas.drawRect(10.0f, 860.0f, 170.0f, 960.0f, grey);
        canvas.drawText("Accept", 10.0f,930.0f, black);

        grey.setTextSize(50.0f);
        canvas.drawText("Player 1: " + state.getP1Score(), 10.0f, 400.0f, grey);
        canvas.drawText("Player 0: " + state.getP0Score(), 10.0f, 500.0f, grey);

        grey.setTextSize(50.0f);
        canvas.drawText("GUILLOTINE GAME ", 10.0f, 100.0f, grey);


        //P0 Hand 200,  280
        float left = 1700;
        Bitmap draw;
        for(int i = 0; i < state.getP0Hand().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 300, 420, true);
            canvas.drawBitmap(draw, left, 900.0f, null); //800
            left -= 220;
        }

        //P0 field 100 140
        left = 1800;
        for(int i = 0; i < state.getP0Field().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 150, 210, true);
            canvas.drawBitmap(draw, left, 675.0f, null); // 650
            left -= 120;
        }

        //Noble line 200 280
        left = 1700;
        for(int i = 0; i < state.getNobleLine().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getNobleLine().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 300, 420, true);
            canvas.drawBitmap(draw, left, 250.0f, null); //350
            left -= 100;
        }

        //P1 field 100   140
        left = 1800;
        for(int i = 0; i < state.getP1Field().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP1Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 150, 210, true);
            canvas.drawBitmap(draw, left, 35.0f, null); //150
            left -= 120;
        }

        if(state.getTurnPhase() == 4){
            canvas.drawText("1", 50.0f, 400.0f, choice);
            canvas.drawText("2", 1050.0f, 400.0f, choice);
        }

        if(state.getTurnPhase() == 5){
            canvas.drawText("1", 50.0f, 400.0f, choice);
            canvas.drawText("2", 720.0f, 400.0f, choice);
            canvas.drawText("3", 1375.0f, 400.0f, choice);
        }

    }
}
