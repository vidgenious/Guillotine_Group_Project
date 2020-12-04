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
    //private ArrayList<Card> nobleLine = new ArrayList<Card>();
    //private ArrayList<Card> hand = new ArrayList<Card>();
    private boolean hasScarlet = false;
    private int pos = 0;


    public GuillotineComputerPlayer2(String name) {

        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(!(info instanceof GuillotineState)){
            return;
        }

        Random rand = new Random();
        GuillotineState gameState = (GuillotineState) info;

        for(int k = 0; k < gameState.getP1Hand().size(); k++){
            if(gameState.getP1Hand().get(k).getId().equals("Scarlet")){
                this.hasScarlet = true;
            }
        }

        //code for if AI is player 1
        if(gameState.getPlayerTurn() == 1) {
            if(gameState.getTurnPhase() == 0) {


                int actionPos = 0;
                int play = rand.nextInt(2);
                int pos = rand.nextInt(gameState.getP1Hand().size());
                //checks if it has the scarlet card and plays it if it has more points or if day is 3
                if(hasScarlet && (gameState.getP1Score()-5 > gameState.getP0Score() || gameState.getP1Score() > gameState.getP0Score() && gameState.getDayNum() == 3)){
                    for(int k = 0; k < gameState.getP1Hand().size(); k++){
                        if(gameState.getP1Hand().get(k).getId().equals("Scarlet")){
                            actionPos = k;
                        }
                    }
                    this.pos = 0;
                    game.sendAction(new PlayAction(this, actionPos));
                    gameState.setTurnPhase(1);
                }

                //checks if first card in noble line is bad, if so, change it if possible
                else if ((!gameState.getNobleLine().isEmpty()) && gameState.getNobleLine().get(0).points < 3 && !gameState.getNobleLine().get(0).hasEffect) {
                    for(int i = 0; i < gameState.getP1Hand().size(); i++){
                        if(gameState.getP1Hand().get(i).affectsLine){
                            actionPos = i;

                        }
                    }
                    this.pos = 0;
                    game.sendAction(new PlayAction(this, actionPos));
                    gameState.setTurnPhase(1);
                }


                //checks if next card in line is good, if so, change it if possible so next player cant get a good card
                else if (gameState.getNobleLine().size() > 0 && (gameState.getNobleLine().get(1).points > 3 || gameState.getNobleLine().get(1).hasEffect)){
                    for(int i = 0; i < gameState.getP1Hand().size(); i++){
                        if(gameState.getP1Hand().get(i).affectsLine){
                            actionPos = i;

                        }
                    }
                    this.pos = 1;
                    game.sendAction(new PlayAction(this, actionPos));
                    gameState.setTurnPhase(1);
                }


                //AI randomly either plays an action card or skips
               else if (play == 1 && gameState.getNobleLine().get(0).points < 3 && !gameState.getNobleLine().get(0).hasEffect) {


                    game.sendAction(new PlayAction(this, pos));

                    gameState.setTurnPhase(1);
                }
                else {
                    game.sendAction(new SkipAction(this));
                    gameState.setTurnPhase(1);

                }
            }

            //plays a noble action
            else if(gameState.getTurnPhase() == 1){
                game.sendAction(new NobleAction(this));
            }
            //ai draws a card
            else if(gameState.getTurnPhase() == 2){
                game.sendAction(new DrawAction(this));
            }

            //ai picks a second card
            else if(gameState.getTurnPhase() == 3) {

                ChooseAction action = new ChooseAction(this, this.pos, 1);
                game.sendAction(action);

            }

            //picking a distance
            else if(gameState.getTurnPhase() == 4){
                int chos = rand.nextInt(2) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }
            else if (gameState.getTurnPhase() == 5){
                int chos = rand.nextInt(3) + 1;

                ChooseAction action = new ChooseAction(this, chos, 2);
                game.sendAction(action);

            }

        }


        else{
            game.sendAction(new NullAction(this));
        }
    }
}

