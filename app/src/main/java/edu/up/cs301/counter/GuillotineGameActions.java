package edu.up.cs301.counter;

import java.util.ArrayList;
import java.util.Collections;

import edu.up.cs301.game.GameFramework.GamePlayer;
import edu.up.cs301.game.GameFramework.actionMessage.GameAction;

/**
 * This class includes basic actions of Guillotine game.
 * We will add more actions of the game. This is for the Alpha Release.
**/
public class GuillotineGameActions extends GameAction {

    private ArrayList<Card> tempList;
    private boolean decksShuffled = false;
    Card temp;
    GuillotineState gameState;
    private boolean gameStart = false;
    private boolean shuffle0 = false;
    private boolean shuffle1 = false;
    private boolean FS0 = false;
    private boolean FS1 = false;
    private boolean noAction = false;
    private boolean scarletInPlay = false;

    //point vars
    private boolean p0Count = false;
    private boolean p1Count = false;
    private boolean p0Countess = false;
    private boolean p1Countess = false;
    private int p0PalaceGuard = 0;
    private int p1PalaceGuard = 0;


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public GuillotineGameActions(GamePlayer player) {
        super(player);
    }

    //checks if you can start the game and starts it
    public boolean startGame(){
        this.gameStart = true;
        shuffleDecks();
        dealStartingGameCards();
        gameState.turnPhase = 0;
        this.gameStart = false;
        return true;
        //  setPlayerTurn();


    }

    //lets you quit the game
    public boolean quitGame(){
        if(gameState.dayNum == 3 && gameState.nobleLine.size() == 0){
            if(gameState.p0Score > gameState.p1Score){
                System.out.println("Player "+ gameState.playerNames[0]+ " wins with "+ gameState.p0Score + " points!");

            }
            else if(gameState.p0Score < gameState.p1Score){
                System.out.println("Player "+ gameState.playerNames[1]+ " wins with "+ gameState.p1Score + " points!");
            }
            else{
                System.out.println("All players tied with " +gameState.p1Score + " points!");
            }
            return true;
        }
        return false;
    }


    //checks if decks have already been shuffled, then shuffles if false
    public boolean shuffleDecks(){
        if(decksShuffled){
            return false;
        }
        else{
            //code to shuffle all decks
            Collections.shuffle(gameState.deckAction);
            Collections.shuffle(gameState.deckNoble);
            decksShuffled = true;
            return true;
        }
    }

    //deals all cards needed to be dealt at start of game
    public boolean dealStartingGameCards(){
        if(gameState.dayNum == 1 && gameState.turnPhase == 0) {
            for (int i = 0; i < 5; i++) {
                dealActionCard(gameState.p0Hand);
                dealActionCard(gameState.p1Hand);
            }
            for (int k = 0; k < 12; k++) {
                dealNoble();
            }

            return true;
        }
        else{
            for (int k = 0; k < 12; k++) {
                dealNoble();
                return true;
            }
        }
        return false;
    }


    //deals action cards at end of turn
    public boolean dealActionCard(ArrayList hand){
        if(scarletInPlay && !gameState.actionCardPlayed){
            hand.add(gameState.deckAction.get(0));
            while(!gameState.nobleLine.isEmpty()){
                gameState.deckDiscard.add(gameState.nobleLine.get(0));
            }
        }
        if(this.gameStart || gameState.turnPhase == 2 || gameState.actionCardPlayed) {
            hand.add(gameState.deckAction.get(0));
            gameState.deckAction.remove(0);
            if(!gameState.actionCardPlayed && !gameState.actionCardPlayed){
                gameState.turnPhase = 0;
                return true;
            }
            else{
                return true;
            }
        }
        return false;
        //basically how ever the action cards are stored, the player will have their action
        //cards gain the first action card of the actioncard deck.
    }

    //lets user play actioncard
    public boolean playAction(ArrayList hand, int loc){
        if(!this.noAction) {
            if (!gameState.nobleLine.get(0).getId().equals("Judge1") || !gameState.nobleLine.get(0).getId().equals("Judge2")) {
                if (gameState.turnPhase == 0) {
                    this.temp = (Card) hand.get(loc);
                    //acknowledgeCardAbility(this.temp, card0, card1);
                    gameState.turnPhase++;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else{
            this.noAction = false;
            skipAction();
            return false;
        }
    }



    //lets user skip action card play
    public boolean skipAction(){
        if(gameState.turnPhase == 0){
            gameState.turnPhase++;

            return true;

        }

        return false;
    }

    //gets the card that the user clicks and keeps it.
    //idk how to implement this with onclick
    public boolean chooseCard(ArrayList user){
        if(userloc == 0){
            user.remove(2);
            user.remove(1);
            gameState.deckDiscard.add(gameState.deckNoble.get(0));
            gameState.deckNoble.remove(0);
        }
        else if( userloc == 1){
            user.remove(2);
            user.remove(0);
            gameState.deckDiscard.add(gameState.deckNoble.get(1));
            gameState.deckNoble.remove(1);
        }
        else{
            user.remove(1);
            user.remove(2);
            gameState.deckDiscard.add(gameState.deckNoble.get(2));
            gameState.deckNoble.remove(2);
        }
    }

    /**
     * Noble Card Actions
     */
    public boolean dealNoble(){

        gameState.nobleLine.add(gameState.deckNoble.get(0));
        gameState.deckNoble.remove(0);

        return true;
    }

    /**
     *For this method, you must add all the noble points together. It takes in the user's field and
     * that turn number of the user
     * I would suggest adding all the noble cards that are not dependant on other cards first
     * WARNING: action cards are in this field if the action card manipulates the points, so check if the card is noble first
     * Then, I would add any noble card point that has an action of it's own.
     * I have some instance vars that will tell you important information (such as how many palace guards a user has)
     * Use those vars (you will most likely have to make your own for I think I am missing some) to find the new point values
     * I would also add a local counter for each card color. (Red, Blue, Green, Purple, and Grey)
     * Then check each card in field and count how many cards of certain colors exist
     * Use this information to find the point value of all cards that are dependent of number of certain colors
     *
     *
     *
     */
    public boolean calculatePoints(ArrayList<Card> field, int user){
        boolean end = false;
        if(user == 0){
            for(int i = 0; i < field.size(); i++){

                if(field.get(i).isNoble) {
                    gameState.p0Score += field.get(i).points;
                }
                else{
                    gameState.p1Score += field.get(i).points;
                }
            }

        }
        return true;
    }




}
