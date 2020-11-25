package edu.up.cs301.guillotine;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameFramework.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 * @version Beta November 2020
 */

/**
 * This class acts as the code for the dumb AI and relies on making random choices for functionality
 */
public class GuillotineComputerPlayer2 extends GameComputerPlayer {
    private ArrayList<Card> nobleLine = new ArrayList<Card>();
    private ArrayList<Card> hand = new ArrayList<Card>();
    private boolean hasScarlet = false;
    private boolean hasTwo = false;
    private int pos = 0;

    public GuillotineComputerPlayer2(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof GuillotineState)) {
            return;
        }
        Random rand = new Random();

        GuillotineState gameState = (GuillotineState) info;
        nobleLine = gameState.getNobleLine();
        hand = gameState.getP1Hand();

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).id.equals("Scarlet")) {
                hasScarlet = true;
            }
        }


        if (gameState.getPlayerTurn() == 1) {
            if (gameState.getTurnPhase() == 0) {

                //if AI is winning, the game is almost over, and has an end the day card, it will play it
                if (gameState.getP0Score() > gameState.getP1Score() && gameState.getDayNum() == 3 && hasScarlet) {
                    for (int i = 0; i < hand.size(); i++) {
                        if (hand.get(i).id.equals("Scarlet")) {
                            game.sendAction(new PlayAction(this, i));
                        }
                    }
                    gameState.setTurnPhase(1);

                }

                //check if first card is bad, if so play a card to change the first card
                else if (!hand.isEmpty() && nobleLine.get(0).points < 3 && !nobleLine.get(0).hasEffect) {
                    //tries to find a type 2 card that will allow a specific selection
                    for (int i = 0; i < hand.size(); i++) {
                        if (hand.get(i).type == 2) {
                            pos = 0;
                            ChooseAction choose = new ChooseAction(this, 0, rand.nextInt(2)+1);
                            game.sendAction(choose);
                            hasTwo = true;
                            break;
                        }
                    }

                    //if there are no type 2 cards, the ai will just play a random type 1 card in hopes it solves the problem
                    if (!hasTwo) {
                        game.sendAction(new PlayAction(this, rand.nextInt(hand.size())));
                    }
                    gameState.setTurnPhase(1);
                    hasTwo = false;
                }

                //if next card is good, change the card so the player suffers
                else if (!hand.isEmpty() && nobleLine.get(1).points > 3 && nobleLine.get(1).hasEffect) {
                    for (int i = 0; i < hand.size(); i++) {
                        if (hand.get(i).type == 2) {
                            pos = 1;
                            ChooseAction choose = new ChooseAction(this, 1, rand.nextInt(2)+1);
                            game.sendAction(choose);
                            break;
                        }
                    }
                    gameState.setTurnPhase(1);

                }

                //if AI has card that doesnt affect the field, it plays it
                else if(!hand.isEmpty() && rand.nextInt(2) == 1){
                    for(int i = 0; i < hand.size(); i++){
                        if(!hand.get(i).affectsLine){
                            game.sendAction(new PlayAction(this, i));
                            break;
                        }
                    }
                }
                //skip action 
                else{
                    game.sendAction(new SkipAction(this));
                    gameState.setTurnPhase(1);

                }
            }

            //noble action
            else if(gameState.getTurnPhase() == 1){
                game.sendAction(new NobleAction(this));
                gameState.setTurnPhase(2);
            }

            //draw action card phase
            else if(gameState.getTurnPhase() == 2){
                game.sendAction(new DrawAction(this));
                gameState.setTurnPhase(0);
            }

            //clause for when AI needs to pick two cards
            else if(gameState.getTurnPhase() == 3) {

                ChooseAction action = new ChooseAction(this, pos, rand.nextInt(2)+1);
                game.sendAction(action);
            }

            //choose claus
            else if(gameState.getTurnPhase() == 4){
                int chos = rand.nextInt(2) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }

            //choose claus
            else if (gameState.getTurnPhase() == 5){
                int chos = rand.nextInt(3) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }
            else {
                game.sendAction(new NullAction(this));
            }
        }
    }
}
