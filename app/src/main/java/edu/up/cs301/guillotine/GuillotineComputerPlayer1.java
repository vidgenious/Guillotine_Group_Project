package edu.up.cs301.guillotine;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.R;

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
        if(!(info instanceof GuillotineState)){
            return;
        }


        GuillotineState gameState = (GuillotineState) info;

        if(gameState.getPlayerTurn() == 1) {
            if(gameState.getTurnPhase() == 0) {
                Random rand = new Random();
                int play = rand.nextInt(2);

                int pos = rand.nextInt(gameState.getP1Hand().size());
                if (play == 1) {


                    game.sendAction(new PlayAction(this, pos));

                } else {
                    game.sendAction(new SkipAction(this));
                }
            }else if(gameState.getTurnPhase() == 1){
                game.sendAction(new NobleAction(this));
            }
        }else{
            game.sendAction(new NullAction(this));
        }
    }
}
