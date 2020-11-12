package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class PlayAction extends GameAction {
    private int pos;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayAction(GamePlayer player, int pos) {
        super(player);
    }

    public int getPos(){return pos;}
}
