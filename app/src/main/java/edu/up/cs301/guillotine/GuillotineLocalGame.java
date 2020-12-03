package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.R;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Beta November 2020
 */

/**
 * This class acts as the Local game and checks for things such as if the game is over
 */
public class GuillotineLocalGame extends LocalGame {

    Card temp;

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

        //If the action is a play card action
        if (action instanceof PlayAction) {

            //If the turn is 0
            if (gameState.getPlayerTurn() == 0) {
                int cardPlayed = -1;

                //For loop to find the card selected in the hand
                for (int i = 0; i < gameState.getP0Hand().size(); i++) {
                    if (i == ((PlayAction) action).getPos()) {
                        cardPlayed = i;
                        this.temp = gameState.getP0Hand().get(i);
                    }
                }

                //Calls the play action method in the state
                gameState.playAction(gameState.getP0Hand(), cardPlayed);

             //If the turn is 1
            }
            else {
                int cardPlayed = -1;

                //For loop to find the card selected in the hand
                for (int i = 0; i < gameState.getP1Hand().size(); i++) {
                    if (i == ((PlayAction) action).getPos()) {
                        cardPlayed = i;
                        this.temp = gameState.getP1Hand().get(i);
                    }
                }
                //Calls the play action method in the state
                gameState.playAction(gameState.getP1Hand(), cardPlayed);
            }

            //Calculates the points of both players after the phase has ended
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;

         //If the action is a skip play action
        }
        else if(action instanceof ChooseAction) {
            if(((ChooseAction) action).getChoice() == 1) {
                gameState.setChoice1(((ChooseAction) action).getPos());
            }else if(((ChooseAction) action).getChoice() == 2){
                gameState.setChoice2(((ChooseAction) action).getPos());
            }
            gameState.acknowledgeCardAbility(temp);
            return true;

        }
        else if (action instanceof SkipAction) {
            gameState.skipAction();

            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;

         //If the action is a collect noble action
        }
        else if (action instanceof NobleAction) {



            //if the noble line is empty, the day is over
            if(gameState.getNobleLine().size() == 0){
                for(int i = 0; i < 12; i++) {
                    gameState.dealNoble();
                }

                gameState.setTurnPhase(0);
                gameState.setDayNum(gameState.getDayNum()+1);
                gameState.setTurnPhase(0);
            }
                //Depending on which player it is, that player gets the noble card
                if (gameState.getPlayerTurn() == 0) {
                    gameState.getNoble(gameState.getP0Field());
                } else if (gameState.getPlayerTurn() == 1) {
                    gameState.getNoble(gameState.getP1Field());
                }
            //if the noble line is empty, the day is over
            if(gameState.getNobleLine().size() == 0){
                for(int i = 0; i < 12; i++) {
                    gameState.dealNoble();
                }

                gameState.setTurnPhase(0);
                gameState.setDayNum(gameState.getDayNum()+1);
                gameState.setTurnPhase(0);
            }


            //Calculating points
            gameState.calculatePoints(gameState.getP0Field(), 0);
            gameState.calculatePoints(gameState.getP1Field(), 1);
            return true;

         //If the action is a draw card action
        }
        else if (action instanceof DrawAction) {

            //Depending on player, that player get a new action card
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


        }else if(action instanceof HandMoveAction) {
            gameState.moveThroughHand();

        }else if(action instanceof P0MoveAction) {
            gameState.moveThroughP0Field();

        } else if(action instanceof P1MoveAction) {
            gameState.moveThroughP1Field();

        } else if(action instanceof LackMoveAction) {
            gameState.moveThroughLack();

        } else if(action instanceof  RatMoveAction) {
            gameState.moveThroughRat();
            //If it a null action, due to a turn not being complete
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
            return winner + " is the winner! ";
        }else{
            return null;
        }
    }
}
