package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.R;

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
    protected boolean makeMove(GameAction action) {


        if (action instanceof PlayAction) {
            if (gameState.getPlayerTurn() == 0) {
                int cardPlayed = -1;
                for (int i = 0; i < gameState.getP0Hand().size(); i++) {
                    if (i == ((PlayAction) action).getPos()) {
                        cardPlayed = i;
                    }
                }
                gameState.playAction(gameState.getP0Hand(), cardPlayed);
            } else {
                int cardPlayed = -1;
                for (int i = 0; i < gameState.getP1Hand().size(); i++) {
                    if (i == ((PlayAction) action).getPos()) {
                        cardPlayed = i;
                    }
                }
                gameState.playAction(gameState.getP1Hand(), cardPlayed);
            }
            return true;
        } else if (action instanceof SkipAction) {
            gameState.setTurnPhase(1);
            return true;
        } else if (action instanceof NobleAction) {
            if (gameState.getPlayerTurn() == 0) {
                gameState.getNoble(gameState.getP0Field());
            } else if (gameState.getPlayerTurn() == 1) {
                gameState.getNoble(gameState.getP1Field());
            }
            if(gameState.getNobleLine().size() < 1){
                for(int i = 0; i < 12; i++) {
                    gameState.dealNoble();
                }
                gameState.setDayNum(gameState.getDayNum()+1);
                gameState.setTurnPhase(0);
            }
            return true;
        } else if (action instanceof DrawAction) {
            if (gameState.getPlayerTurn() == 0) {
                gameState.dealActionCard(gameState.getP0Hand());
                gameState.setPlayerTurn(1);
            } else if (gameState.getPlayerTurn() == 1) {
                gameState.dealActionCard(gameState.getP1Hand());
                gameState.setPlayerTurn(0);
            }
            return true;
        } else if(action instanceof  NullAction){
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
