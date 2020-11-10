package edu.up.cs301.counter;

import android.view.MotionEvent;
import android.view.View;

import edu.up.cs301.game.GameFramework.GameHumanPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.R;

public class GuillotineHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {
    private GuillotineState state;

    private GameMainActivity myActivity;

    public GuillotineHumanPlayer(String name) {super(name);}

    public View getTopView() { return myActivity.findViewById(R.id.top_gui_layout);}

    protected void updateDisplay() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof GuillotineState)) return;

        this.state = (GuillotineState)info;
        updateDisplay();
    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
