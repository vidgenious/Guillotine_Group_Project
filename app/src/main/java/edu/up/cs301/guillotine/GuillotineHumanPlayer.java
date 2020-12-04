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
 * @version Final December 2020
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

        //if human is position 0
        if(this.playerNum == 0){
        //If statement for if the turn is correct
        if (state.getPlayerTurn() == 0) {

            //Defining the coordinates of the touch
            int x = (int) event.getX();
            int y = (int) event.getY();

            boolean handArrow = handArrow(x, y);

            //check if hand arrow is pressed
            if (handArrow) {
                HandMoveAction action = new HandMoveAction(this);
                game.sendAction(action);
            }

            if(state.getTurnPhase() == 0 || state.getTurnPhase() == 1 || state.getTurnPhase() == 2){
                if(zoom(x, y) == 0){
                    ZoomAction action = new ZoomAction(this, 0);
                    game.sendAction(action);
                    return true;
                } else if(zoom(x, y) == 1){
                    ZoomAction action = new ZoomAction(this, 1);
                    game.sendAction(action);
                    return true;
                }
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
                }
                if (state.getPlayerTurn() == 0)
                    if (cardPos < 0 || cardPos + 1 > state.getP0Hand().size()) {
                        return false;
                    }
                PlayAction action = new PlayAction(this, cardPos);

                //Sends whichever action is the result
                game.sendAction(action);


            } else if (state.getTurnPhase() == 3) {

                int cardPos = lineCard(x, y);

                if (cardPos > -1 && !(cardPos >= state.getNobleLine().size())) {
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

            } else if (state.getTurnPhase() == 6) {
                int cardPos = fourChoice(x, y);

                if (choiceArrow(x, y)) {
                    LackMoveAction action = new LackMoveAction(this);
                    game.sendAction(action);
                }

                if (cardPos != -1 && cardPos < state.getP1Hand().size()) {
                    ChooseAction action = new ChooseAction(this, cardPos, 1);
                    game.sendAction(action);
                }

            } else if (state.getTurnPhase() == 7) {
                int cardPos = fourChoice(x, y);

                if (choiceArrow(x, y)) {
                    RatMoveAction action = new RatMoveAction(this);
                    game.sendAction(action);
                }

                if (cardPos != -1 && cardPos < state.getDeckDiscard().size()) {
                    ChooseAction action = new ChooseAction(this, cardPos, 1);
                    game.sendAction(action);
                }

            } else if (state.getTurnPhase() == 8){
                if(zoom(x, y) == 0){
                    ZoomAction action = new ZoomAction(this, 0);
                    game.sendAction(action);
                } else if(zoom(x, y) == 1){
                    ZoomAction action = new ZoomAction(this, 1);
                    game.sendAction(action);
                }
                if(choiceArrow(x, y) && state.getZoom() == 0){
                    HandMoveAction action = new HandMoveAction(this);
                    game.sendAction(action);
                } else if(choiceArrow(x, y) && state.getZoom() == 1){
                    LineMoveAction action = new LineMoveAction(this);
                    game.sendAction(action);
                }


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
    }//end if player is position 0

        //if human is position 1
        else{
            //If statement for if the turn is correct
            if (state.getPlayerTurn() == 1) {

                //Defining the coordinates of the touch
                int x = (int) event.getX();
                int y = (int) event.getY();

                boolean handArrow = handArrow(x, y);

                //check if hand arrow is pressed
                if (handArrow) {
                    HandMoveAction action = new HandMoveAction(this);
                    game.sendAction(action);
                }

                if(state.getTurnPhase() == 0 || state.getTurnPhase() == 1 || state.getTurnPhase() == 2){
                    if(zoom(x, y) == 0){
                        ZoomAction action = new ZoomAction(this, 0);
                        game.sendAction(action);
                        return true;
                    } else if(zoom(x, y) == 1){
                        ZoomAction action = new ZoomAction(this, 1);
                        game.sendAction(action);
                        return true;
                    }
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
                    }
                    if (state.getPlayerTurn() == 1)
                        if (cardPos < 0 || cardPos + 1 > state.getP1Hand().size()) {
                            return false;
                        }
                    PlayAction action = new PlayAction(this, cardPos);

                    //Sends whichever action is the result
                    game.sendAction(action);


                } else if (state.getTurnPhase() == 3) {

                    int cardPos = lineCard(x, y);

                    if (cardPos > -1 && !(cardPos >= state.getNobleLine().size())) {
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

                } else if (state.getTurnPhase() == 6) {
                    int cardPos = fourChoice(x, y);

                    if (choiceArrow(x, y)) {
                        LackMoveAction action = new LackMoveAction(this);
                        game.sendAction(action);
                    }

                    if (cardPos != -1 && cardPos < state.getP0Hand().size()) {
                        ChooseAction action = new ChooseAction(this, cardPos, 1);
                        game.sendAction(action);
                    }

                } else if (state.getTurnPhase() == 7) {
                    int cardPos = fourChoice(x, y);

                    if (choiceArrow(x, y)) {
                        RatMoveAction action = new RatMoveAction(this);
                        game.sendAction(action);
                    }

                    if (cardPos != -1 && cardPos < state.getDeckDiscard().size()) {
                        ChooseAction action = new ChooseAction(this, cardPos, 1);
                        game.sendAction(action);
                    }

                } else if (state.getTurnPhase() == 8){
                    if(zoom(x, y) == 0){
                        ZoomAction action = new ZoomAction(this, 0);
                        game.sendAction(action);
                    } else if(zoom(x, y) == 1){
                        ZoomAction action = new ZoomAction(this, 1);
                        game.sendAction(action);
                    }
                    if(choiceArrow(x, y) && state.getZoom() == 0){
                        HandMoveAction action = new HandMoveAction(this);
                        game.sendAction(action);
                    } else if(choiceArrow(x, y) && state.getZoom() == 1){
                        LineMoveAction action = new LineMoveAction(this);
                        game.sendAction(action);
                    }


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
        }//end else


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

        //sets the state var to the guillotine state object
        //calls on the board with current guillotine state and gives board player names and nums
        this.state = (GuillotineState)info;
        board.setPlayerHuman(this.playerNum);
        board.setHumanName(this.allPlayerNames[this.playerNum]);
        if(this.playerNum == 0){
            board.setAIName(this.allPlayerNames[1]);
        }
        else{
            board.setAIName(this.allPlayerNames[0]);
        }
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
    /**
     * dimensions of the hand of cards on the GUI
     *
     * @param int x: position of the card
     *            int y: position of the card
     */
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

    /**
     * dimentions of the line card on the screen
     *
     * @param int x: position of the card
     *            @param int y: position of the card
     */
    private int lineCard(int x, int y){
        if(x > 1700 && x < 1900 && y > 350 && y < 630){
            return 0;
        } else if(x > 1600 && x < 1700 && y > 350 && y < 630){
            return 1;
        } else if(x > 1500 && x < 1600 && y > 350 && y < 630){
            return 2;
        } else if(x > 1400 && x < 1500 && y > 350 && y < 630){
            return 3;
        } else if(x > 1300 && x < 1400 && y > 350 && y < 630){
            return 4;
        } else if(x > 1200 && x < 1300 && y > 350 && y < 630){
            return 5;
        } else if(x > 1100 && x < 1200 && y > 350 && y < 630){
            return 6;
        } else if(x > 1000 && x < 1100 && y > 350 && y < 630){
            return 7;
        } else if(x > 900 && x < 1000 && y > 350 && y < 630){
            return 8;
        } else if(x > 800 && x < 900 && y > 350 && y < 630){
            return 9;
        } else if(x > 700 && x < 800 && y > 350 && y < 630){
            return 10;
        } else if(x > 600 && x < 700 && y > 350 && y < 630){
            return 11;
        } else if(x > 500 && x < 600 && y > 350 && y < 630){
            return 12;
        } else if(x > 400 && x < 500 && y > 350 && y < 630){
            return 13;
        } else if(x > 300 && x < 400 && y > 350 && y < 630){
            return 14;
        }
        return -1;
    }

    /**
     * dimensions of the hand arrow on the UI
     *
     * @param int x: position of the card
     *            int y: position of the card
     */


    private boolean handArrow(int x, int y){
        if(x > 240 && x < 310 && y > 880 && y < 950){
            return true;
        }
        return false;
    }
    /**
     * dimensions of the choice arrow on the GUI
     *
     * @param int x: x-position of the arrow
     *            int y: y-position of the arrow
     */

    private boolean choiceArrow(int x, int y){
        if(x > 300 && x < 450 && y > 530 && y < 680){
            return true;
        }
        return false;
    }

    /**
     * dimensions of the zoom feature on the GUI
     *
     * @param int x: x-position of the arrow
     *            int y: y-position of the arrow
     */

    private int zoom(int x, int y){
        if(x > 1550.0f && x < 1800.0f && y > 40 && y < 190){
            return 0;
        } else if(x > 1300 && x < 1550 && y > 40 && y < 190){
            return 1;
        }

        return -1;
    }

    /**
     * dimensions of accept button on the GUI
     *
     * @param int x: x-position of button
     *            int y: y-position of the button
     */
    private boolean acceptButton(int x, int y){
        if(x > 10 && x < 170 && y > 860 && y < 960){
            return true;
        }
        return false;
    }

    /**
     * dimensions of skip button on the GUI
     *
     * @param int x: x-position of button
     *            int y: y-position of the button
     */
    private boolean skipButton(int x, int y){
        if(x > 10 && x < 170 && y > 970 && y < 1070){
            return true;
        }
        return false;
    }

    /**
     * dimensions of Choice 2 on the GUI
     *
     * @param int x: x-position of 2 choice
     *            int y: y-position 2 choice
     */
    private int twoChoice(int x, int y){
        if(x > 0 && x < 1000 && y > 0 && y < 1100){
            return 1;
        }
        else if(x > 1000 && x < 2000 && y > 0 && y < 1100){
            return 2;
        }
        return -1;
    }

    /**
     * dimensions of Choice 3 on the GUI
     *
     * @param int x: x-position of 3 choice
     *            int y: y-position 3 choice
     */
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

    /**
     * dimensions of Choice 4 on the GUI
     *
     * @param int x: x-position of 4 choice
     *            int y: y-position 4 choice
     */

    private int fourChoice(int x, int y){
        if(x > 1570 && x < 1920 && y > 360 && y < 850){
            return 0;
        }
        else if(x > 1200 && x < 1550 && y > 360 && y < 850){
            return 1;
        }
        else if(x > 830 && x < 1180 && y > 360 && y < 850){
            return 2;
        }
        else if(x > 460 && x < 810 && y > 360 && y < 850){
            return 3;
        }

        return -1;
    }

}
