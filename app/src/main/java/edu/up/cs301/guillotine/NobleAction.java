package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.Game;
import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class NobleAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public NobleAction(GamePlayer player) {
        super(player);
    }
}
