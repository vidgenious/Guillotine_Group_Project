package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class GuillotineLocalGame extends LocalGame {

    private GuillotineState gameState;

    @Override
    protected boolean canMove(int playerIdx) {
        return gameState.getPlayerTurn() == playerIdx;
    }

    public GuillotineLocalGame() {
        this.gameState = new GuillotineState();
    }

    @Override
    protected boolean makeMove(GameAction action){
        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new GuillotineState(gameState));
    }

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        String winner;

        if(gameState.getDayNum() == 4){
            if(gameState.getP0Score() > gameState.getP1Score()){
                winner = this.playerNames[0];
            }else{
                winner = this.playerNames[1];
            }
            return winner + " is the winner!";
        }else{
            return null;
        }
    }
}
