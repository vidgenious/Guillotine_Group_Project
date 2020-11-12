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

public class DrawBoard extends FlashSurfaceView {

    private GuillotineState state;

    public DrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void setState(GuillotineState state) {
        this.state = state;
    }

    @Override
    public void onDraw(Canvas canvas) {
        this.state = new GuillotineState();
        state.startGame();

        float left = 1700;
        Bitmap draw;
        for(int i = 0; i < state.getP0Hand().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 800.0f, null);
            left -= 220;
        }

        left = 1800;
        for(int i = 0; i < state.getP0Field().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
            canvas.drawBitmap(draw, left, 650.0f, null);
            left -= 120;
        }

        left = 1500;
        for(int i = 0; i < state.getNobleLine().size(); i++){
            draw = BitmapFactory.decodeResource(getResources(), state.getNobleLine().get(i).image);
            draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
            canvas.drawBitmap(draw, left, 350.0f, null);
            left -= 100;
        }



    }

    private void drawHand(Canvas canvas) {
    }
}
