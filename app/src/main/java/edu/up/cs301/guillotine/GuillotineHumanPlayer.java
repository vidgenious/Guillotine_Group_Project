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

public class GuillotineHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {
    private GuillotineState state;

    private GameMainActivity myActivity;

    private DrawBoard board;

    public GuillotineHumanPlayer(String name) {super(name);}

    public View getTopView() { return myActivity.findViewById(R.id.top_gui_layout);}

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) return true;

        if(state.getPlayerTurn() == 0) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (state.getTurnPhase() == 0) {
                int cardPos = handCard(x, y);
                boolean skip = skipButton(x, y);

                if(skip){
                    game.sendAction(new SkipAction(this));
                }else if (cardPos < 0 || cardPos + 1 > state.getP0Hand().size()) {
                    return false;
                }
                PlayAction action = new PlayAction(this, cardPos);
                game.sendAction(action);
                state.setTurnPhase(1);
            } else if (state.getTurnPhase() == 1) {
                boolean select = acceptButton(x, y);
                if (select) {
                    NobleAction action = new NobleAction(this);
                    game.sendAction(action);
                    state.setTurnPhase(2);
                }
            }else if (state.getTurnPhase() == 2){
                boolean select = acceptButton(x, y);
                if (select) {
                    DrawAction action = new DrawAction(this);
                    game.sendAction(action);
                    state.setTurnPhase(0);
                }
            }
            board.invalidate();
        }

        return true;
    }

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
