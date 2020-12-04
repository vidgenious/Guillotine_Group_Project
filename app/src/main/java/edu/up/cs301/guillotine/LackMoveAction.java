package edu.up.cs301.guillotine;

import java.util.ArrayList;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Final December 2020
 */

/**
 * Moves through opponents hand
 */
public class LackMoveAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public LackMoveAction(GamePlayer player) {
        super(player);
    }

}
