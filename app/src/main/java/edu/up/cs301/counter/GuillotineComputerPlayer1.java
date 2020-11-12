package edu.up.cs301.counter;

import android.util.Log;

import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.R;

public class GuillotineComputerPlayer1 extends GameComputerPlayer {
    // the most recent game state, as given to us by the CounterLocalGame
    private GuillotineState currentGameState = null;
    private GuillotineGameActions gameActions = null;
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
            //stupid AI does not need any information
            //this is blank

    }

    /**
     * callback method: the timer ticked
     * check whos turn it is
     * 50/50 chance to play an action or to skip
     * if it plays an action it randomly selects a card
     * does not work if the card requires more locations (IE pick another card in deck)
     *
     */
    protected void determineAction() {
        if(currentGameState.playerTurn == 1){
        int locp1 = (int)(Math.random()*currentGameState.p1Hand.size());

        if(Math.random() < .5) {
            game.sendAction(gameActions.playAction(currentGameState.p1Hand, locp1);
        }

        }

        else{
            game.sendAction(new SkipAction);
        }
    }
}
