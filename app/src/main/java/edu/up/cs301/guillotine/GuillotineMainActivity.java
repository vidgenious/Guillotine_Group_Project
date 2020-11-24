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
