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
 * @version Beta November 2020
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

        //checks if human is player 1 or 0
        //If statement for if the turn is correct
        if (state.getPlayerTurn() == 0) {

            //Defining the coordinates of the touch
            int x = (int) event.getX();
            int y = (int) event.getY();

            boolean discardCall = DiscardButton(x, y);
            boolean handArrow = handArrow(x, y);
            boolean p0Arrow = p0FieldArrow(x, y);
            boolean p1Arrow = p1FieldArrow(x, y);

            //check if hand arrow is pressed
            if (handArrow) {
                HandMoveAction action = new HandMoveAction(this);
                game.sendAction(action);
            }

            //check if p0 arrow is pressed
            if (p0Arrow) {
                P0MoveAction action = new P0MoveAction(this);
                game.sendAction(action);
            }

            //check if p1 arrow is pressed
            if (p1Arrow) {
                P1MoveAction action = new P1MoveAction(this);
                game.sendAction(action);
            }

            //see if discard button is pressed
            //Not used right now
            if (discardCall) {

            }

            //if it is the play action/skip phase
            if (state.getTurnPhase() == 0) {

                //Methods for determining which card/button is pressed
                int cardPos = handCard(x, y);
                boolean skip = skipButton(x, y);

                //If skip button is pressed
                if (skip) {
                    game.sendAction(new SkipAction(this));

                    //If an invalid place on the screen is touched
                } else if (cardPos < 0 || cardPos + 1 > state.getP0Hand().size()) {
                    return false;
                }
                PlayAction action = new PlayAction(this, cardPos);

                //Sends whichever action is the result
                game.sendAction(action);


            } else if (state.getTurnPhase() == 3) {

                int cardPos = lineCard(x, y);

                if (cardPos > -1 && !(cardPos + 1 > state.getNobleLine().size())) {
                    ChooseAction action = new ChooseAction(this, cardPos, 1);
                    game.sendAction(action);
                }

            } else if (state.getTurnPhase() == 4) {
                int cardPos = twoChoice(x, y);

                ChooseAction action = new ChooseAction(this, cardPos, 2);
                game.sendAction(action);

            } else if (state.getTurnPhase() == 5) {
                int cardPos = threeChoice(x, y);

                ChooseAction action = new ChooseAction(this, cardPos, 2);
                game.sendAction(action);

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
            } else if (state.getTurnPhase() == 2) {

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
        board.setHumanPlayerLoc(this.playerNum);
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
        }else if(x > 380 && x < 580 && y > 800 && y < 1080) {
            return 6;
        }
        return -1;
    }

    private int lineCard(int x, int y){
        if(x > 1800 && x < 1900 && y > 350 && y < 630){
            return 0;
        } else if(x > 1700 && x < 1800 && y > 350 && y < 630){
            return 1;
        } else if(x > 1600 && x < 1700 && y > 350 && y < 630){
            return 2;
        } else if(x > 1500 && x < 1600 && y > 350 && y < 630){
            return 3;
        } else if(x > 1400 && x < 1500 && y > 350 && y < 630){
            return 4;
        } else if(x > 1300 && x < 1400 && y > 350 && y < 630){
            return 5;
        } else if(x > 1200 && x < 1300 && y > 350 && y < 630){
            return 6;
        } else if(x > 1100 && x < 1200 && y > 350 && y < 630){
            return 7;
        } else if(x > 1000 && x < 1100 && y > 350 && y < 630){
            return 8;
        } else if(x > 900 && x < 1000 && y > 350 && y < 630){
            return 9;
        } else if(x > 800 && x < 900 && y > 350 && y < 630){
            return 10;
        } else if(x > 700 && x < 800 && y > 350 && y < 630){
            return 11;
        }
        return -1;
    }




    private boolean handArrow(int x, int y){
        if(x > 240 && x < 310 && y > 880 && y < 950){
            return true;
        }
        return false;
    }

    private boolean p0FieldArrow(int x, int y){
        if(x > 250 && x < 300 && y > 675 && y < 725){
            return true;
        }
        return false;
    }

    private boolean p1FieldArrow(int x, int y){
        if(x > 250 && x < 300 && y > 175 && y < 225){
            return true;
        }
        return false;
    }


    private boolean DiscardButton(int x, int y){
        if(x > 10 && x < 200 && y > 670 && y < 770){
            return true;
        }
        return false;
    }

    private boolean acceptButton(int x, int y){
        if(x > 10 && x < 170 && y > 860 && y < 960){
            return true;
        }
        return false;
    }

    private boolean skipButton(int x, int y){
        if(x > 10 && x < 170 && y > 970 && y < 1070){
            return true;
        }
        return false;
    }

    private int twoChoice(int x, int y){
        if(x > 0 && x < 1000 && y > 0 && y < 1100){
            return 1;
        }
        else if(x > 1000 && x < 2000 && y > 0 && y < 1100){
            return 2;
        }
        return -1;
    }

    private int threeChoice(int x, int y){
        if(x > 0 && x < 667 && y > 0 && y < 1100){
            return 1;
        }
        else if(x > 667 && x < 1325 && y > 0 && y < 1100){
            return 2;
        }
        else if(x > 1325 && x < 2000 && y > 0 && y < 1100){
            return 3;
        }

        return -1;
    }

    private int fourChoice(int x, int y){
        if(x > 0 && x < 500 && y > 0 && y < 1100){
            return 1;
        }
        else if(x > 500 && x < 1000 && y > 0 && y < 1100){
            return 2;
        }
        else if(x > 1000 && x < 1500 && y > 0 && y < 1100){
            return 3;
        }
        else if(x > 1500 && x < 2000 && y > 0 && y < 1100){
            return 4;
        }

        return -1;
    }

}
