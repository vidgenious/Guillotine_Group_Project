package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Alpha November 2020
 */

/**
 * This class is the constructor for play action
 */
public class ChooseAction extends GameAction {
    private int pos;
    private int choice;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ChooseAction(GamePlayer player, int pos, int choice) {
        super(player);
        this.pos = pos;
        this.choice = choice;
    }

    public int getPos(){return pos;}
    public int getChoice(){return choice;}
}
