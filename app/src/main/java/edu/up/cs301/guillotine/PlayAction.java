package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Beta November 2020
 */

/**
 * This class is the constructor for play action
 */
public class PlayAction extends GameAction {
    private int pos;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayAction(GamePlayer player, int pos) {
        super(player);
        this.pos = pos;
    }

    public int getPos(){return pos;}
}
