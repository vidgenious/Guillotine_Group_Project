package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Alpha November 2020
 */

/**
 * This class acts as the Local game and checks for things such as if the game is over
 */
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

        if(action instanceof PlayAction) {
            if(gameState.getPlayerTurn() == 0){
                gameState.getP0Hand().remove(((PlayAction) action).getPos());
                gameState.setPlayerTurn(1);
            }else{
                gameState.getP1Hand().remove(((PlayAction) action).getPos());
                gameState.setPlayerTurn(0);
            }
            return true;
        }
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
