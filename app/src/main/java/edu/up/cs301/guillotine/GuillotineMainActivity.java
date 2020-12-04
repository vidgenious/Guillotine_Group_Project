package edu.up.cs301.guillotine;

import java.util.ArrayList;

import edu.up.cs301.game.GameFramework.GameMainActivity;
import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.LocalGame;
import edu.up.cs301.game.GameFramework.gameConfiguration.GameConfig;
import edu.up.cs301.game.GameFramework.gameConfiguration.GamePlayerType;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Beta November 2020
 *
 * Meeting 12/2/2020 with Professor Nuxoll
 *      Granted 3-star status to guillotine game
 *      Allowed to forgo implementation for 6 cards
 *          Cards chosen:
 *              Callous Guards
 *              Clerical Error
 *              Opinionated Guards
 *              Twist of Fate
 *              Infighting
 *
 *
 * Beta:
 * For the Beta we are fully functional except for some action cards that haven't been implemented yet.
 * The other thing that we have not completed for the rubric is making the players interchangeable.
 * Currently the only functional way to play is to have human player as the first player and the
 * computer player as the second player.
 *
 * For the cards, the problem ended up being that we did not have a clear plan from the beginning.
 * We had not made plans about how each card would function for the GUI and for onTouch events.
 * This could have been avoided by making a solid plan from the beginning.
 *
 * For the interchangeable users, the problem is that the system was not built from the ground up
 * with that functionality in mind. If we had planned from the beginning to make that work properly
 * we would not be in the state we are now, where we have to retroactively fix the program so that
 * it has that functionality.
 *
 * Overall better planning would have the game further on in production than it is right now.
 *
 * Full Release:
 *
 */
/**
 * Dumb AI: Everything it does is random
 * Smart AI:
 * 1) Checks if it has a game ending card and checks to see if the conditions are right before using that card
 * Those conditions are based off of both players score and what day it is.
 * 2) Checks to see if the first card is a bad card (determined off of points and if the noble card has an action)
 * if it is bad, the AI will try to play an action card to either swap that noble with another card
 * 3) Checks to see if the second card in noble line is a good card. If it is a good card
 * the AI will play an action card to specifically swap that card with another card. If the AI does not have a specific
 * action card
 * if the AI does not have specific cards, it will play a random action card to change the line
 * everything else it does it random
 *
 * If you try to move a card an amount of spaces that it cannot, the card is discarded and nothing happens.
 * That is not a bug.
 *
 *
 */


/**
 * Main activity class that creates the default configurations of our game (the settings basically)
 */
public class GuillotineMainActivity extends GameMainActivity {
    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2234;

    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     * - one kind of computer player and one kind of human player available
     *
     * @return
     * 		the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // a human player player type (player type 0)
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new GuillotineHumanPlayer(name);
            }});

        // a computer player type (player type 1)
        playerTypes.add(new GamePlayerType("Dumb AI Computer") {
            public GamePlayer createPlayer(String name) {
                return new GuillotineComputerPlayer1(name);
            }});

        // a computer player type (player type 2)
        playerTypes.add(new GamePlayerType("Smart AI Computer") {
            public GamePlayer createPlayer(String name) {
                return new GuillotineComputerPlayer2(name);
            }});

        // Create a game configuration class for Counter:
        // - player types as given above
        // - from 1 to 2 players
        // - name of game is "Counter Game"
        // - port number as defined above
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Guillotine",
                PORT_NUMBER);

        // Add the default players to the configuration
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Dumb Computer", 1); // player 2: a computer player


        // Set the default remote-player setup:
        // - player name: "Remote Player"
        // - IP code: (empty string)
        // - default player type: human player
        defaultConfig.setRemoteData("Remote Player", "", 0);

        // return the configuration
        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game, a counter game
     */
    @Override
    public LocalGame createLocalGame() {
        return new GuillotineLocalGame();
    }
}
