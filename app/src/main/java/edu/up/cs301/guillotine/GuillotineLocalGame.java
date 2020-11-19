package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.R;

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

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return gameState.getPlayerTurn() == playerIdx;
    }

    /**
     * Constructor for the TTTLocalGame.
     */
    public GuillotineLocalGame() {
        this.gameState = new GuillotineState();
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     * 			Tells whether the move was a legal one.
     */
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
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;
        } else if (action instanceof SkipAction) {
            gameState.setTurnPhase(1);
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
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
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;
        } else if (action instanceof DrawAction) {
            if (gameState.getPlayerTurn() == 0) {
                gameState.dealActionCard(gameState.getP0Hand());
                gameState.setPlayerTurn(1);
            } else if (gameState.getPlayerTurn() == 1) {
                gameState.dealActionCard(gameState.getP1Hand());
                gameState.setPlayerTurn(0);
            }
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;
        } else if(action instanceof  NullAction){
            return true;
        }
        return false;
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p
     * 			the player to notify
     */
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
