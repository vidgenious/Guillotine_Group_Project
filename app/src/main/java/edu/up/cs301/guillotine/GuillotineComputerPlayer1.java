package edu.up.cs301.guillotine;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.R;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Final December 2020
 */

/**
 * This class acts as the code for the dumb AI and relies on making random choices for functionality
 */
public class GuillotineComputerPlayer1 extends GameComputerPlayer {
    // the most recent game state, as given to us by the CounterLocalGame
    /**
     * Constructor for objects of class CounterComputerPlayer1
     *
     * @param name
     * 		the player's name
     */
    public GuillotineComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);


    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof GuillotineState)) {
            return;
        }

        Random rand = new Random();

        GuillotineState gameState = (GuillotineState) info;
        //checks if player is position 1
        if(this.playerNum == 1){
        //code for if AI is player 1
        if (gameState.getPlayerTurn() == 1) {
            if (gameState.getTurnPhase() == 0) {

                //AI randomly either plays an action card or skips
                int play = rand.nextInt(2);
                int pos = rand.nextInt(gameState.getP1Hand().size());
                if (play == 1) {


                    game.sendAction(new PlayAction(this, pos));

                    gameState.setTurnPhase(1);
                }
                else {
                    game.sendAction(new SkipAction(this));
                    gameState.setTurnPhase(1);

                }
            }

            //plays a noble action
            else if (gameState.getTurnPhase() == 1) {
                game.sendAction(new NobleAction(this));
            }
            //ai draws a card
            else if (gameState.getTurnPhase() == 2) {
                game.sendAction(new DrawAction(this));
            }
            //ai picks a second card
            else if (gameState.getTurnPhase() == 3) {
                int pos = rand.nextInt(gameState.getNobleLine().size());

                ChooseAction action = new ChooseAction(this, pos, 1);
                game.sendAction(action);

            }
            else if (gameState.getTurnPhase() == 4) {
                int chos = rand.nextInt(2) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }
            else if (gameState.getTurnPhase() == 5) {
                int chos = rand.nextInt(3) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }
            else if (gameState.getTurnPhase() == 6) {
                int chos = rand.nextInt(4) + 1;

                ChooseAction action = new ChooseAction(this, chos, 1);
                game.sendAction(action);
            }
            else if (gameState.getTurnPhase() == 7) {
                int chos = rand.nextInt(gameState.getDeckDiscard().size());


                ChooseAction action = new ChooseAction(this, chos, 1);
                game.sendAction(action);
            }

        }

        else{
            game.sendAction(new NullAction(this));
        }

        }//end if player is position 1

        //else if player is position 0
        else{
            //code for if AI is player 0
            if (gameState.getPlayerTurn() == 0) {
                if (gameState.getTurnPhase() == 0) {

                    //AI randomly either plays an action card or skips
                    int play = rand.nextInt(2);
                    int pos = rand.nextInt(gameState.getP0Hand().size());
                    if (play == 1) {


                        game.sendAction(new PlayAction(this, pos));

                        gameState.setTurnPhase(1);
                    }
                    else {
                        game.sendAction(new SkipAction(this));
                        gameState.setTurnPhase(1);

                    }
                }

                //plays a noble action
                else if (gameState.getTurnPhase() == 1) {
                    game.sendAction(new NobleAction(this));
                }
                //ai draws a card
                else if (gameState.getTurnPhase() == 2) {
                    game.sendAction(new DrawAction(this));
                }
                //ai picks a second card
                else if (gameState.getTurnPhase() == 3) {
                    int pos = rand.nextInt(gameState.getNobleLine().size());

                    ChooseAction action = new ChooseAction(this, pos, 1);
                    game.sendAction(action);

                }
                else if (gameState.getTurnPhase() == 4) {
                    int chos = rand.nextInt(2) + 1;

                    ChooseAction action = new ChooseAction(this, chos, 2);
                    game.sendAction(action);

                }
                else if (gameState.getTurnPhase() == 5) {
                    int chos = rand.nextInt(3) + 1;

                    ChooseAction action = new ChooseAction(this, chos, 2);
                    game.sendAction(action);

                }
                else if (gameState.getTurnPhase() == 6) {
                    int chos = rand.nextInt(4) + 1;

                    ChooseAction action = new ChooseAction(this, chos, 1);
                    game.sendAction(action);
                }
                else if (gameState.getTurnPhase() == 7) {
                    int chos = rand.nextInt(gameState.getDeckDiscard().size());


                    ChooseAction action = new ChooseAction(this, chos, 1);
                    game.sendAction(action);
                }

            }

            else{
                game.sendAction(new NullAction(this));
            }
        }
    }
}
