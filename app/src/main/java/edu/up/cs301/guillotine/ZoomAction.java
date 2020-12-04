package edu.up.cs301.guillotine;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

public class ZoomAction extends GameAction {
    private int subject;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ZoomAction(GamePlayer player, int subject) {
        super(player);
        this.subject = subject;
    }

    public int getSubject() {
        return subject;
    }
}
