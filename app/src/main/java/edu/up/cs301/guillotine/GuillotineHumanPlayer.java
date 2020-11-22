package edu.up.cs301.guillotine;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.R;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Alpha November 2020
 */

/**
 * This class is the human player class that is responsible for the actions that a human can take
 */

public class GuillotineHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {
    private GuillotineState state;

    private GameMainActivity myActivity;

    private DrawBoard board;

    /**
     * constructor
     *
     * @param name
     * 		the player's name
     */
    public GuillotineHumanPlayer(String name) {super(name);}

    /**
     * returns the GUI's top view
     *
     * @return
     * 		the GUI's top view
     */
    public View getTopView() { return myActivity.findViewById(R.id.top_gui_layout);}

    /**
     * callback method when the screen it touched. We're
     * looking for a screen touch (which we'll detect on
     * the "up" movement" onto a tic-tac-tie square
     *
     * @param event
     * 		the motion event that was detected
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) return true;

        //If statement for if the turn is correct
        if(state.getPlayerTurn() == 0) {

            //Defining the coordinates of the touch
            int x = (int) event.getX();
            int y = (int) event.getY();

            //if it is the play action/skip phase
            if (state.getTurnPhase() == 0) {

                //Methods for determining which card/button is pressed
                int cardPos = handCard(x, y);
                boolean skip = skipButton(x, y);

                //If skip button is pressed
                if(skip){
                    game.sendAction(new SkipAction(this));

                 //If an invalid place on the screen is touched
                }else if (cardPos < 0 || cardPos + 1 > state.getP0Hand().size()) {
                    return false;
                }
                PlayAction action = new PlayAction(this, cardPos);

                //Sends whichever action is the result
                game.sendAction(action);



            } else if(state.getTurnPhase() == 3){
                

             //If it is the get noble phase
            } else if (state.getTurnPhase() == 1) {

                //Method for determining if the accept button was pressed
                boolean select = acceptButton(x, y);

                //If accept button pressed
                if (select) {
                    NobleAction action = new NobleAction(this);
                    game.sendAction(action);

                    //moving to draw card phase
                    state.setTurnPhase(2);
                }

             //If it is the draw card phase
            }else if (state.getTurnPhase() == 2){

                //Method for if accept button pressed
                boolean select = acceptButton(x, y);

                //if accept is pressed
                if (select) {
                    DrawAction action = new DrawAction(this);
                    game.sendAction(action);
                    state.setTurnPhase(0);
                }
            }

            //Whatever action is taken, the board invalidates.
            board.invalidate();
        }

        return true;
    }

    /**
     * Callback method, called when player gets a message
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof GuillotineState)) {
            flash(0xff129834, 5);
            return;
        }

        this.state = (GuillotineState)info;
        board.setState(state);
        board.invalidate();
    }

    /**
     * sets the current player as the activity's GUI
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;

        activity.setContentView(R.layout.guillotine_layout);

        board = (DrawBoard) myActivity.findViewById(R.id.guillotine_board);

        board.setOnTouchListener(this);
        board.setState(state);

    }

    private int handCard(int x, int y){
        if(x > 1700 && x < 1900 && y > 800 && y < 1080){
            return 0;
        }else if(x > 1480 && x < 1680 && y > 800 && y < 1080){
            return 1;
        }else if(x > 1260 && x < 1460 && y > 800 && y < 1080){
            return 2;
        }else if(x > 1040 && x < 1240 && y > 800 && y < 1080){
            return 3;
        }else if(x > 820 && x < 1020 && y > 800 && y < 1080){
            return 4;
        }else if(x > 600 && x < 800 && y > 800 && y < 1080){
            return 5;
        }else if(x > 380 && x < 580 && y > 800 && y < 1080){
            return 6;
        }else if(x > 160 && x < 360 && y > 800 && y < 1080) {
            return 7;
        }
        return -1;
    }

    private boolean acceptButton(int x, int y){
        if(x > 10 && x < 160 && y > 860 && y < 960){
            return true;
        }
        return false;
    }

    private boolean skipButton(int x, int y){
        if(x > 10 && x < 160 && y > 970 && y < 1070){
            return true;
        }
        return false;
    }
}
