package edu.up.cs301.counter;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class GuillotineLocalGame extends LocalGame {

    private GuillotineState gameState;

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    public GuillotineLocalGame() {

    }

    @Override
    protected boolean makeMove(GameAction action){
        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new GuillotineState(gameState));
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }
}
