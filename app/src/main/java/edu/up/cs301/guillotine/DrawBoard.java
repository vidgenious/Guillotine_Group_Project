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

    private GuillotineState state; //holds the GuillotineState object
    private Paint grey = new Paint(); //grey paint that will be used
    private Paint black = new Paint(); //black paint that will be used
    private Paint choice = new Paint(); //color for the choice pop-ups
    private int playerHuman; //holds the information of human player location
    private String humanName; //holds the string of the human name
    private String aiName; //holds the string of the AI name

    /**
     * constructor for draw board
     * @param context
     * @param attrs
     */
    public DrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        grey.setColor(Color.WHITE);
        black.setColor(Color.BLACK);
        black.setTextSize(50.0f);
        choice.setColor(Color.RED);
        choice.setTextSize(400.0f);

    }

    /**
     * this sets the private state variable to the parameter state
     * @param state Guillotine state object that will be clone by this class
     */
    public void setState(GuillotineState state) {
        this.state = state;
    }

    /**
     * this sets a private int to the parameter human in order to determine what position the human player is
     * @param human int of human player position. IE, if human is player 0 or player 1
     */
    public void setPlayerHuman(int human){this.playerHuman = human;}

    /**
     * this sets the humanName String to equal the human player name
     * @param name String of human player name
     */
    public void setHumanName(String name){this.humanName = name;}

    /**
     *  this sets the aiName string to equal the ai player name
     * @param name String of AI player name
     */
    public void setAIName(String name){this.aiName = name;}

    /**
     * this method draws the entire board by using the information from playerHuman and state
     * always draws the human player hand and field at the bottom of the board
     * @param canvas canvas that will be drawn on
     */
    @Override
    public void onDraw(Canvas canvas) {

        Bitmap draw;

        if(state == null){
            return;
        }

        //text for accept and skip buttons
        canvas.drawRect(10.0f, 970.0f, 170.0f, 1070.0f, grey);
        canvas.drawText("Skip", 30.0f,1030.0f, black);
        canvas.drawRect(10.0f, 860.0f, 170.0f, 960.0f, grey);
        canvas.drawText("Accept", 10.0f,930.0f, black);

        grey.setTextSize(50.0f);
        //text for the card decks
        if(!state.getDeckDiscard().isEmpty()) {
            canvas.drawText("Discard Deck", 130.0f, 650.0f, grey);
        }
        if(!state.getDeckNoble().isEmpty()){
            canvas.drawText("Noble Deck", 130.0f, 800.0f, grey);
        }
        if(!state.getDeckAction().isEmpty()){
            canvas.drawText("Action Deck", 130.0f, 500.0f, grey);
        }

        canvas.drawText("GUILLOTINE GAME ", 10.0f, 100.0f, grey);

        //check if human is player 0
        if(playerHuman == 0) {

            //text for the days and players names
            canvas.drawText("Day: " + state.getDayNum(), 10.0f, 400.0f, grey);
            canvas.drawText(this.aiName + ": " + state.getP1Score(), 10.0f, 200.0f, grey);
            canvas.drawText(this.humanName + ": " + state.getP0Score(), 10.0f, 300.0f, grey);

            if(state.getTurnPhase() == 0 || state.getTurnPhase() == 1 || state.getTurnPhase() == 2){
                //Maximize Hand Symbol
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
                draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                canvas.drawText("Zoom Hand", 1550.0f, 40.0f, grey);
                canvas.drawBitmap(draw, 1600.0f, 40.0f, null);
            } else if (state.getTurnPhase() == 8){
                //Minimize Hand Symbol
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.minus);
                draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                canvas.drawText("Zoom Hand", 1550.0f, 40.0f, grey);
                canvas.drawBitmap(draw, 1600.0f, 40.0f, null);
            }


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

            //Draws the P0 Hand cards on the UI
            float left = 1700;
            for (int i = 0; i < state.getP0Hand().size() && i < 7; i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
                canvas.drawBitmap(draw, left, 800.0f, null); //800
                left -= 220.0f;
            }

            //Draws them P0 field cards on UI
            left = 1800;
            for (int i = 0; i < state.getP0Field().size() && i < 12; i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 650.0f, null); // 650
                left -= 120;
            }

            // Draws the Noble line cards on UI
            left = 1700;
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
                canvas.drawBitmap(draw, left, 200.0f, null); //150
                left -= 120;
            }


            //Draws the Action Deck on the screen
            left = 10;
            if (!(state.getDeckAction().isEmpty())) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 710.0f, null);

            }

            //draws the discard deck on the screen
            if (!(state.getDeckDiscard().isEmpty())) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 560.0f, null);

            }

            //draws the noble deck on the screen
            if (!(state.getDeckNoble().isEmpty())){
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.noble_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 415.0f, null);

            }

            //Displays the information on the UI that tells the player what phase it is
            //And what action to take in each phase.
            if (state.getTurnPhase() == 0) {
                canvas.drawText("Action Card Phase", 800.0f, 50.0f, grey);
                canvas.drawText("Touch an action card", 800.0f, 115.0f, grey);
            }

            if (state.getTurnPhase() == 1) {
                canvas.drawText("Take Noble Phase", 800.0f, 50.0f, grey);

                canvas.drawText("Touch accept button", 800.0f, 115.0f, grey);
            }
            if (state.getTurnPhase() == 2) {
                canvas.drawText("Draw card Phase", 800.0f, 50.0f, grey);
                canvas.drawText("Touch accept button", 800.0f, 115.0f, grey);
            }
            if (state.getTurnPhase() == 3) {
                canvas.drawText("Select Noble in Line", 800.0f, 50.0f, grey);
                canvas.drawText("Touch noble card", 800.0f, 115.0f, grey);
            }

            if (state.getTurnPhase() == 4) {
                canvas.drawText("Select ", 800.0f, 50.0f, grey);
                canvas.drawText("1", 50.0f, 400.0f, choice);
                canvas.drawText("2", 1050.0f, 400.0f, choice);
            }

            if (state.getTurnPhase() == 5) {
                canvas.drawText("Select", 800.0f, 50.0f, grey);
                if (state.getArrival()) {
                    left = 50.0f;
                    for (int i = 0; i < state.getDeckNoble().size() && i < 3; i++) {
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

            if (state.getTurnPhase() == 6) {
                canvas.drawText("Select Card to Discard in Opponent's Hand", 800.0f, 50.0f, grey);
                if (state.getPlayerTurn() == 0) {
                    left = 1570;
                    for (int i = 0; i < state.getP1Hand().size() && i < 4; i++) {
                        draw = BitmapFactory.decodeResource(getResources(), state.getP1Hand().get(i).image);
                        draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                        canvas.drawBitmap(draw, left, 360.0f, null);
                        left -= 370;
                    }
                    if (state.getP1Hand().size() > 4) {
                        draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                        draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                        canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                    }
                } else {
                    left = 1570;
                    for (int i = 0; i < state.getP0Hand().size() && i < 4; i++) {
                        draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
                        draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                        canvas.drawBitmap(draw, left, 360.0f, null);
                        left -= 370;
                    }
                    if (state.getP0Hand().size() > 4) {
                        draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                        draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                        canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                    }
                }

            }

            if (state.getTurnPhase() == 7) {
                left = 1570;
                for (int i = 0; i < state.getDeckDiscard().size() && i < 4; i++) {
                    draw = BitmapFactory.decodeResource(getResources(), state.getDeckDiscard().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if (state.getDeckDiscard().size() > 4) {
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            }

            if (state.getTurnPhase() == 8){
                left = 1570;
                for (int i = 0; i < state.getP0Hand().size() && i < 4; i++) {
                    draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if (state.getP0Hand().size() > 4) {
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            }


        }//end check if human is player 0

        //for when human is player 1
        else{

            //text for the days and players names
            canvas.drawText("Day: " + state.getDayNum(), 10.0f, 400.0f, grey);
            canvas.drawText(this.aiName + ": " + state.getP0Score(), 10.0f, 200.0f, grey);
            canvas.drawText(this.humanName + ": " + state.getP1Score(), 10.0f, 300.0f, grey);

            if(state.getTurnPhase() == 0 || state.getTurnPhase() == 1 || state.getTurnPhase() == 2){
                //Maximize Hand Symbol
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
                draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                canvas.drawText("Zoom Hand", 1550.0f, 40.0f, grey);
                canvas.drawBitmap(draw, 1600.0f, 40.0f, null);
            } else if (state.getTurnPhase() == 8){
                //Minimize Hand Symbol
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.minus);
                draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                canvas.drawText("Zoom Hand", 1550.0f, 40.0f, grey);
                canvas.drawBitmap(draw, 1600.0f, 40.0f, null);
            }

            //Hand Arrow
            if (state.getP1Hand().size() > 7) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                draw = Bitmap.createScaledBitmap(draw, 100, 100, true);
                canvas.drawBitmap(draw, 250.0f, 890.0f, null);
            }

            //p1field arrow
            if (state.getP1Field().size() > 12) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
                canvas.drawBitmap(draw, 300.0f, 675.0f, null);
            }

            //p0field arrow
            if (state.getP0Field().size() > 12) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                draw = Bitmap.createScaledBitmap(draw, 50, 50, true);
                canvas.drawBitmap(draw, 300.0f, 175.0f, null);
            }

            //Draws the P1 Hand cards on the UI
            float left = 1700;
            for (int i = 0; i < state.getP1Hand().size() && i < 7; i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getP1Hand().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
                canvas.drawBitmap(draw, left, 800.0f, null); //800
                left -= 220.0f;
            }

            //Draws them P1 field cards on UI
            left = 1800;
            for (int i = 0; i < state.getP1Field().size() && i < 12; i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getP1Field().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 650.0f, null); // 650
                left -= 120;
            }

            // Draws the Noble line cards on UI
            left = 1700;
            for (int i = 0; i < state.getNobleLine().size(); i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getNobleLine().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 200, 280, true);
                canvas.drawBitmap(draw, left, 350.0f, null); //350
                left -= 100;
            }

            //P0 field
            left = 1800;
            for (int i = 0; i < state.getP0Field().size() && i < 12; i++) {
                draw = BitmapFactory.decodeResource(getResources(), state.getP0Field().get(i).image);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 200.0f, null); //150
                left -= 120;
            }


            //Draws the Action Deck on the screen
            left = 10;
            if (!(state.getDeckAction().isEmpty())) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 710.0f, null);

            }

            //draws the discard deck on the screen
            if (!(state.getDeckDiscard().isEmpty())) {
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.action_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 560.0f, null);

            }

            //draws the noble deck on the screen
            if (!(state.getDeckNoble().isEmpty())){
                draw = BitmapFactory.decodeResource(getResources(), R.drawable.noble_card_back);
                draw = Bitmap.createScaledBitmap(draw, 100, 140, true);
                canvas.drawBitmap(draw, left, 415.0f, null);

            }

            //Displays the information on the UI that tells the player what phase it is
            //And what action to take in each phase.
            if (state.getTurnPhase() == 0) {
                canvas.drawText("Action Card Phase", 800.0f, 50.0f, grey);
                canvas.drawText("Touch an action card", 800.0f, 115.0f, grey);
            }

            if (state.getTurnPhase() == 1) {
                canvas.drawText("Take Noble Phase", 800.0f, 50.0f, grey);

                canvas.drawText("Touch accept button", 800.0f, 115.0f, grey);
            }
            if (state.getTurnPhase() == 2) {
                canvas.drawText("Draw card Phase", 800.0f, 50.0f, grey);
                canvas.drawText("Touch accept button", 800.0f, 115.0f, grey);
            }
            if (state.getTurnPhase() == 3) {
                canvas.drawText("Select Noble in Line", 800.0f, 50.0f, grey);
                canvas.drawText("Touch noble card", 800.0f, 115.0f, grey);
            }

            if (state.getTurnPhase() == 4) {
                canvas.drawText("Select ", 800.0f, 50.0f, grey);
                canvas.drawText("1", 50.0f, 400.0f, choice);
                canvas.drawText("2", 1050.0f, 400.0f, choice);
            }

            if (state.getTurnPhase() == 5) {
                canvas.drawText("Select", 800.0f, 50.0f, grey);
                if (state.getArrival()) {
                    left = 50.0f;
                    for (int i = 0; i < state.getDeckNoble().size() && i < 3; i++) {
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

            if (state.getTurnPhase() == 6) {
                canvas.drawText("Select Card to Discard in Opponent's Hand", 800.0f, 50.0f, grey);
                if (state.getPlayerTurn() == 0) {
                    left = 1570;
                    for (int i = 0; i < state.getP0Hand().size() && i < 4; i++) {
                        draw = BitmapFactory.decodeResource(getResources(), state.getP0Hand().get(i).image);
                        draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                        canvas.drawBitmap(draw, left, 360.0f, null);
                        left -= 370;
                    }
                    if (state.getP1Hand().size() > 4) {
                        draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                        draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                        canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                    }
                } else {
                    left = 1570;
                    for (int i = 0; i < state.getP1Hand().size() && i < 4; i++) {
                        draw = BitmapFactory.decodeResource(getResources(), state.getP1Hand().get(i).image);
                        draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                        canvas.drawBitmap(draw, left, 360.0f, null);
                        left -= 370;
                    }
                    if (state.getP0Hand().size() > 4) {
                        draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                        draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                        canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                    }
                }

            }

            if (state.getTurnPhase() == 7) {
                left = 1570;
                for (int i = 0; i < state.getDeckDiscard().size() && i < 4; i++) {
                    draw = BitmapFactory.decodeResource(getResources(), state.getDeckDiscard().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if (state.getDeckDiscard().size() > 4) {
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            }

            if (state.getTurnPhase() == 8){
                left = 1570;
                for (int i = 0; i < state.getP1Hand().size() && i < 4; i++) {
                    draw = BitmapFactory.decodeResource(getResources(), state.getP1Hand().get(i).image);
                    draw = Bitmap.createScaledBitmap(draw, 350, 490, true);
                    canvas.drawBitmap(draw, left, 360.0f, null);
                    left -= 370;
                }
                if (state.getP1Hand().size() > 4) {
                    draw = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow_transparent);
                    draw = Bitmap.createScaledBitmap(draw, 150, 150, true);
                    canvas.drawBitmap(draw, 300.0f, 530.0f, null);
                }
            }
        }
    }
}
