package edu.up.cs301.guillotine;

import android.graphics.Point;
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

        int x = (int) event.getX();
        int y = (int) event.getY();

        PlayAction action = new PlayAction(this, handCard(x, y));
        game.sendAction(action);
        board.invalidate();

        return true;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof GuillotineState)) {
            flash(0xff129834, 5);
            return;
        }

        this.state = (GuillotineState)info;
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
        if(x > 1700 && x < 1800 && y > 800 && y < 1080){
            return 0;
        }
        return -1;
    }
}
