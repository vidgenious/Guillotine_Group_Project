package edu.up.cs301.guillotine;

import android.graphics.BitmapFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;
import edu.up.cs301.game.R;

public class GuillotineState extends GameState {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 7737393762469851826L;

    //These are public for ease of use in the GameActions.java
    private int dayNum;
    private int playerTurn;
    private int p0Score;
    private int p1Score;
    private int lastFirstPlayer;
    private int currFirstPlayer;
    private int turnPhase;
    private ArrayList<Card> p1Hand;
    private ArrayList<Card> p1Field;
    private ArrayList<Card> p0Hand;
    private ArrayList<Card> p0Field;
    private ArrayList<Card> nobleLine;
    private ArrayList<Card> deckDiscard;
    private ArrayList<Card> deckAction;
    private ArrayList<Card> deckNoble;
    //private String[] playerNames;
    private boolean actionCardPlayed;

    // constructor to init all variables
    public GuillotineState() {
        this.dayNum = 1;
        this.playerTurn = 0;
        this.p0Score = 0;
        this.p1Score = 0;
        this.lastFirstPlayer = 0;
        this.currFirstPlayer = 0;
        this.turnPhase = 0;
        //this.playerNames = new String[2];
        this.p1Hand = new ArrayList<Card>();
        this.p0Hand = new ArrayList<Card>();
        this.p1Field = new ArrayList<Card>();
        this.p0Field = new ArrayList<Card>();
        this.nobleLine = new ArrayList<Card>();
        this.deckDiscard = new ArrayList<Card>();
        this.deckAction = new ArrayList<Card>();
        initActionDeck();

        this.deckNoble = new ArrayList<Card>();
        initNobleDeck();

        this.actionCardPlayed = false;



    }

    //Deep copy constructor
    public GuillotineState(GuillotineState origin) {
        this.dayNum = origin.dayNum;
        this.playerTurn = origin.playerTurn;
        this.p0Score = origin.p0Score;
        this.p1Score = origin.p1Score;
        this.lastFirstPlayer = origin.lastFirstPlayer;
        this.currFirstPlayer = origin.currFirstPlayer;
        this.turnPhase = origin.turnPhase;

        this.p1Hand = new ArrayList<Card>();
        for(Card c : origin.p1Hand){
            this.p1Hand.add(c);
        }


        this.p0Hand = new ArrayList<Card>();
        for(Card c : origin.p0Hand){
            this.p0Hand.add(c);
        }


        this.p1Field = new ArrayList<Card>();
        for(Card c : origin.p1Field){
            this.p1Field.add(c);
        }


        this.p0Field = new ArrayList<>();
        for(Card c : origin.p0Field){
            this.p0Field.add(c);
        }

        this.nobleLine = new ArrayList<>();
        for(Card c : origin.nobleLine){
            this.nobleLine.add(c);
        }


        this.deckDiscard = new ArrayList<>();
        for(Card c : origin.deckDiscard){
            this.deckDiscard.add(c);
        }


        this.deckAction = new ArrayList<>();
        for(Card c : origin.deckAction){
            this.deckAction.add(c);
        }


        this.deckNoble = new ArrayList<>();
        for(Card c : origin.deckNoble){
            this.deckNoble.add(c);
        }

        /*this.playerNames = new String[2];
        for(String n : origin.playerNames){
            this.playerNames = origin.playerNames;
        }*/

    }

    //This initiates all of the noble cards ands places them into the deck unshuffled
    public void initNobleDeck(){
        this.deckNoble.add(new Card(true, false, 4, "Blue","Archbishop", R.drawable.archbishop));
        this.deckNoble.add(new Card(true, false, 3, "Blue","Bad_Nun", R.drawable.bad_nun));
        this.deckNoble.add(new Card(true, false, 3, "Purple","Baron", R.drawable.baron));
        this.deckNoble.add(new Card(true, false, 2, "Blue","Bishop", R.drawable.bishop));
        this.deckNoble.add(new Card(true, true, 2,  "Red","Capt_Guard", R.drawable.captain_of_the_guard));
        this.deckNoble.add(new Card(true, false, 5, "Blue","Cardinal", R.drawable.cardinal));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Coiffeur", R.drawable.coiffeur));
        this.deckNoble.add(new Card(true, false, 3, "Red","Colonel", R.drawable.colonel));
        this.deckNoble.add(new Card(true, false, 3, "Green","Councilman", R.drawable.councilman));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Count", R.drawable.count));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Countess", R.drawable.countess));
        this.deckNoble.add(new Card(true, false, 3, "Purple","Duke", R.drawable.duke));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Fast_Noble", R.drawable.fast_noble));
        this.deckNoble.add(new Card(true, true, 4,  "Red","General", R.drawable.general));
        this.deckNoble.add(new Card(true, false, 4, "Green","Governer", R.drawable.governer));
        this.deckNoble.add(new Card(true, false, 2, "Blue","Heretic", R.drawable.heretic));
        this.deckNoble.add(new Card(true, false, -3, "Grey","Hero_People", R.drawable.hero_of_the_people));
        this.deckNoble.add(new Card(true, true, -1,  "Grey","Innocent", R.drawable.innocent_vicitm));
        this.deckNoble.add(new Card(true, false, 5,  "Purple","King_Louis", R.drawable.king_louis));
        this.deckNoble.add(new Card(true, true, 2, "Purple","Lady", R.drawable.lady));
        this.deckNoble.add(new Card(true, true, 1, "Purple","Lady_Waiting", R.drawable.lady_in_waiting));
        this.deckNoble.add(new Card(true, false, 2, "Green","Land_Lord", R.drawable.land_lord));
        this.deckNoble.add(new Card(true, false, 2, "Red","Lieutenant1", R.drawable.lieutenant));
        this.deckNoble.add(new Card(true, false, 2, "Red","Lieutenant2", R.drawable.lieutenant));
        this.deckNoble.add(new Card(true,true, 2, "Purple","Lord", R.drawable.lord));
        this.deckNoble.add(new Card(true, false, 5, "Purple","Antoinette", R.drawable.marie_antoinette));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr1", R.drawable.martyr));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr2", R.drawable.martyr));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr3", R.drawable. martyr));
        this.deckNoble.add(new Card(true, true, 4, "Red","Spy", R.drawable.master_spy));
        this.deckNoble.add(new Card(true, false, 3, "Green","Mayor", R.drawable.mayor));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard1", R.drawable.palace_guard));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard2", R.drawable.palace_guard));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard3", R.drawable.palace_guard));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard4", R.drawable.palace_guard));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard5", R.drawable.palace_guard));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Piss_Boy", R.drawable.piss_boy));
        this.deckNoble.add(new Card(true, false, 4, "Purple","Regent", R.drawable.regent));
        this.deckNoble.add(new Card(true, true, 1, "Green","Rival1", R.drawable.rival_executioner));
        this.deckNoble.add(new Card(true, true, 1, "Green","Rival2", R.drawable.rival_executioner));
        this.deckNoble.add(new Card(true, true, 3, "Purple","Robespierre", R.drawable.robespierre));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Cartographer", R.drawable.royal_cartographer));
        this.deckNoble.add(new Card(true, false, 1, "Green","Sheriff1", R.drawable.sheriff));
        this.deckNoble.add(new Card(true, false, 1, "Green","Sheriff2", R.drawable.sheriff));
        this.deckNoble.add(new Card(true, false, 2, "Green","Tax_Collector", R.drawable.tax_collector));
        this.deckNoble.add(new Card(true, true, -2, "Grey","Clown", R.drawable.the_clown));
        this.deckNoble.add(new Card(true, true, 0, "Grey","Tragic_Figure", R.drawable.tragic_figure));
        this.deckNoble.add(new Card(true, true, 2, "Green","Judge1", R.drawable.unpopular_judge));
        this.deckNoble.add(new Card(true, true, 2, "Green","Judge2", R.drawable.unpopular_judge));
        this.deckNoble.add(new Card(true, false, 1, "Blue","Wealthy_Priest1", R.drawable.wealthy_priest));
        this.deckNoble.add(new Card(true, false, 1, "Blue","Wealthy_Priest2", R.drawable.wealthy_priest));

    }

    //This initiates all of the action cards ands places them into the deck unshuffled
    private void initActionDeck(){
        this.deckAction.add(new Card(false, true, 0, "actionCard", "After_You", R.drawable.after_you));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Bribed", R.drawable.bribed_guards));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Callous", R.drawable.callous_guards));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Church_Support", R.drawable.church_support));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Civic_Pride", R.drawable.civic_pride));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Civic_Support", R.drawable.civic_support));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Clerical_Error", R.drawable.clerical_error));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Clothing_Swap", R.drawable.clothing_swap));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Confusion", R.drawable.confusion_in_line));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Double_Feature1", R.drawable.double_feature));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Double_Feature2", R.drawable.double_feature));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Escape", R.drawable.escape));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Extra_Cart1", R.drawable.extra_cart));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Extra_Cart2", R.drawable.extra_cart));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fainting", R.drawable.fainting_spell));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fled", R.drawable.fled_to_england));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Forced_Break", R.drawable.forced_break));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Foreign_Support", R.drawable.foreign_support));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Forward_March", R.drawable.forward_march));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fountain", R.drawable.fountain_of_blood));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Friend_Queen1", R.drawable.friend_of_the_queen));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Friend_Queen2", R.drawable.friend_of_the_queen));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Idiot1", R.drawable.idiot));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Idiot2", R.drawable.idiot));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Ignoble1", R.drawable.ignoble_noble));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Ignoble2", R.drawable.ignoble_noble));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Indifferent", R.drawable.indifferent_public));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Infighting", R.drawable.infighting));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Info_Exchange", R.drawable.information_exchange));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Lack_Faith", R.drawable.lack_of_faith));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Lack_Support", R.drawable.lack_of_support));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Late_Arrival", R.drawable.late_arrival));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Let_Cake", R.drawable.let_them_eat_cake));
        this.deckAction.add(new Card(false, true, 0, "actionCard", "Majesty", R.drawable.majesty));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Mass_Confusion", R.drawable.mass_confusion));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Military_Might", R.drawable.military_might));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Military_Support", R.drawable.military_support));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Milling1", R.drawable.milling_in_line));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Milling2", R.drawable.milling_in_line));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Missed", R.drawable.missed));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Missing_Heads", R.drawable.missing_heads));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Opinionated", R.drawable.opinionated_guards));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Political_Influence1", R.drawable.political_influence));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Political_Influence2", R.drawable.political_influence));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Public_Demand", R.drawable.public_demand));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Pushed1", R.drawable.pushed));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Pushed2", R.drawable.pushed));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rain_Delay", R.drawable.rain_delay));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rat_Break", R.drawable.rat_break));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rush_Job", R.drawable.rush_job));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Scarlet", R.drawable.scarlet_pimpernel));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Stumble1", R.drawable.stumble));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Stumble2", R.drawable.stumble));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Long_Walk", R.drawable.the_long_walk));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Better_Thing", R.drawable.tis_far_better));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Tough_Crowd", R.drawable.tough_crowd));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Trip1", R.drawable.trip));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Trip2", R.drawable.trip));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Twist_Fate", R.drawable.twist_of_fate));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Was_Name", R.drawable.was_that_my_name));
    }

    public int getDayNum(){return this.dayNum;}
    public int getPlayerTurn(){return this.playerTurn;}
    public int getP0Score(){return this.p0Score;}
    public int getP1Score(){return this.p1Score;}
    public int getLastFirstPlayer(){return this.lastFirstPlayer;}
    public int getCurrFirstPlayer(){return this.currFirstPlayer;}
    public int getTurnPhase(){return this.turnPhase;}
    public ArrayList<Card> getP1Hand(){return this.p1Hand;}
    public ArrayList<Card> getP1Field(){return this.p1Field;}
    public ArrayList<Card> getP0Hand(){return this.p0Hand;}
    public ArrayList<Card> getP0Field(){return this.p0Field;}
    public ArrayList<Card> getNobleLine(){return this.nobleLine;}
    public ArrayList<Card> getDeckDiscard(){return this.deckDiscard;}
    public ArrayList<Card> getDeckAction(){return this.deckAction;}
    public ArrayList<Card> getDeckNoble(){return this.deckNoble;}

    //Setters for variables that may need to be set
    public void setDayNum(int dayNum){this.dayNum = dayNum;}
    public void setPlayerTurn(int playerTurn){this.playerTurn = playerTurn;}
    public void setP0Score(int p0Score){this.p0Score = p0Score;}
    public void setP1Score(int p1Score){this.p1Score = p1Score;}
    public void setLastFirstPlayer(int lastFirstPlayer){this.lastFirstPlayer = lastFirstPlayer;}
    public void setCurrFirstPlayer(int currFirstPlayer){this.currFirstPlayer = currFirstPlayer;}
    public void setTurnPhase(int turnPhase){this.turnPhase = turnPhase;}

    //Variables that are not used outside of a single call
    private boolean gameStart = false;
    private boolean shuffle0 = false;
    private boolean shuffle1 = false;
    private boolean FS0 = false;
    private boolean FS1 = false;
    private boolean noAction = false;
    private boolean scarletInPlay = false;
    private Card temp;
    private ArrayList<Card> tempList;

    //point vars
    private boolean p0Count = false;
    private boolean p1Count = false;
    private boolean p0Countess = false;
    private boolean p1Countess = false;
    private int p0PalaceGuard = 0;
    private int p1PalaceGuard = 0;


    /*
    Methods that have one phase
     */
    public boolean startGame(){
        this.gameStart = true;
        shuffleDecks();
        dealStartingGameCards();
        this.turnPhase = 0;
        this.gameStart = false;
        return true;
        //setPlayerTurn();
    }

    //gets the noble from noble line and gives it to person
    public boolean getNoble(ArrayList field) {
        if (this.turnPhase == 1 || this.actionCardPlayed) {


            if(shuffle0){
                if(this.playerTurn == 0){
                    Collections.shuffle(this.nobleLine);
                    shuffle0 = false;
                }

            }
            else if (shuffle1){
                if(this.playerTurn == 1){
                    Collections.shuffle(this.nobleLine);
                    shuffle1 = false;
                }
            }

            if (this.nobleLine.get(0).getId().equals("Clown")) {
                if (this.playerTurn == 0) {
                    placeClown(this.p0Field, this.nobleLine.get(0));
                    this.nobleLine.remove(0);
                }
                else {
                    placeClown(this.p1Field, this.nobleLine.get(0));
                    this.nobleLine.remove(0);
                }
            }

            else {
                if(this.FS0 && this.playerTurn == 0){
                    field.add(this.nobleLine.get(0));
                    this.nobleLine.remove(0);
                    if(this.p0Field.get(0).cardColor.equals("Purple")){
                        dealActionCard(field);
                    }
                }
                else if(this.FS1 && this.playerTurn == 1){
                    field.add(this.nobleLine.get(0));
                    this.nobleLine.remove(0);
                    if(this.p1Field.get(0).cardColor.equals("Purple")){
                        dealActionCard(field);
                    }

                }
                else {
                    field.add(this.nobleLine.get(0));
                    this.nobleLine.remove(0);
                }
            }

            if (this.nobleLine.isEmpty()) {
                endDay();
            }
            else {
                if (this.actionCardPlayed) {
                    return true;
                } else {
                    this.turnPhase++;
                    return true;
                }
            }
        }

        return false;
    }

    //checks if decks have already been shuffled, then shuffles if false
    public boolean shuffleDecks(){
        //code to shuffle all decks
        Collections.shuffle(this.deckAction);
        Collections.shuffle(this.deckNoble);
        return true;
    }

    //deals all cards needed to be dealt at start of game
    public boolean dealStartingGameCards(){
        if(this.dayNum == 1 && this.turnPhase == 0) {
            for (int i = 0; i < 5; i++) {
                dealActionCard(this.p0Hand);
                dealActionCard(this.p1Hand);
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
        if(scarletInPlay && !this.actionCardPlayed){
            hand.add(this.deckAction.get(0));
            while(!this.nobleLine.isEmpty()){
                this.deckDiscard.add(this.nobleLine.get(0));
            }
        }
        if(this.gameStart || this.turnPhase == 2 || this.actionCardPlayed) {
            hand.add(this.deckAction.get(0));
            this.deckAction.remove(0);
            if(!this.actionCardPlayed && !this.actionCardPlayed){
                this.turnPhase = 0;
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

    public boolean dealNoble(){

        this.nobleLine.add(this.deckNoble.get(0));
        this.deckNoble.remove(0);

        return true;
    }

    //lets user skip action card play
    public boolean skipAction(){
        if(this.turnPhase == 0){
            this.turnPhase++;

            return true;

        }

        return false;
    }

    //lets user play actioncard
    public boolean playAction(ArrayList hand, int loc){
        if(!this.noAction) {
            if (!this.nobleLine.get(0).getId().equals("Judge1") || !this.nobleLine.get(0).getId().equals("Judge2")) {
                if (this.turnPhase == 0) {
                    this.temp = (Card) hand.get(loc);
                    acknowledgeCardAbility(this.temp);
                    this.turnPhase++;
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

    //ends player turn
    public boolean endTurn(){
        if(this.playerTurn == 0){
            this.playerTurn++;
            this.turnPhase = 0;
            return true;
        }
        else{
            this.playerTurn--;
            this.turnPhase = 0;
            return true;
        }
    }

    //ends day when no nobles exist
    public boolean endDay(){
        if(this.dayNum == 3){
            //this is commented out so that nothing extra is printed for tests
            //quitGame();
            return true;
        }
        else{
            this.dayNum++;
            dealStartingGameCards();
            return true;
        }
        //end the day

    }

    public boolean reverseLineOrder(){
        if(!this.nobleLine.isEmpty()){
            //for command that goes through the list/array and swaps the first and last posoitions, stops when one counter reaches halfway
            Collections.reverse(this.nobleLine);
            return true;
        }
        return false;
    }

    public boolean discardRemainingNobles(){
        if(!this.nobleLine.isEmpty()){
            //go through nobleline list and discard each one
            while(!this.nobleLine.isEmpty()){
                this.deckDiscard.add(this.nobleLine.get(0));
                this.nobleLine.remove(0);
            }
            endDay();
            return true;
        }
        return false;
    }

    public boolean placeClown(ArrayList reciever, Card card){

        reciever.add(card);
        return true;

    }

    public boolean tradeHands(ArrayList p1, ArrayList p2){
        if(this.turnPhase == 0) {
            tempList = p1;
            p1 = p2;
            p2 = tempList;
            tempList = null;
            return true;
        }
        return false;
    }

    public boolean rearrangeFirstFour(){
        if(this.nobleLine.size() > 5){
            //again idk what system we are using so finding the first 5 cards will be different for lists or arrays
            for(int i = 0; i < 4; i++) {
                tempList.add(this.nobleLine.get(0));
                this.nobleLine.remove(0);
            }
            Collections.shuffle(tempList);
            for(int k = 0; k < 4; k++){
                this.nobleLine.add(tempList.get(0));
                tempList.remove(0);
            }
            return true;
        }
        return false;
    }

    /*
    Multi-phase methods
     */
    //removes the desired card from enemy player and puts it in the player who called this method
    public boolean takeNoble(ArrayList taker, ArrayList victim, Card desiredcard){
        if(victim.contains(desiredcard)){
            victim.remove(desiredcard);
            taker.add(desiredcard);
            return true;
        }
        return false;
    }

    public boolean takeDiscardCard(ArrayList user){
        if(!this.deckDiscard.isEmpty()){
            //enlarge one card, let user switch inebtween which one is enlarged and they can click a button to get that card
            //noidea
            return true;
        }
        return false;
    }

    /*
    Don't know right now, Maybe useless
     */
    public boolean discardActionCard(ArrayList hand, int loc){
        if(hand.size() > loc){
            //discard  actioncard from player
            this.deckDiscard.add((Card) hand.get(loc));
            hand.remove(loc);
            return true;
        }
        return false;
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
     */
    public boolean calculatePoints(ArrayList<Card> field, int user){
        boolean end = false;
        if(user == 0){
            for(int i = 0; i < field.size(); i++){

                if(field.get(i).isNoble) {
                    this.p0Score += field.get(i).points;
                }

            }

        }
        else{
            for(int i = 0; i < field.size(); i++){

                if(field.get(i).isNoble) {
                    this.p1Score += field.get(i).points;
                }

            }
        }

        return true;
    }

    public boolean rearrangeFives(){
        if(this.nobleLine.size() > 5){
            //again idk what system we are using so finding the first 5 cards will be different for lists or arrays
            for(int i = 0; i < 5; i++) {
                tempList.add(this.nobleLine.get(0));
                this.nobleLine.remove(0);
            }
            Collections.shuffle(tempList);
            for(int k = 0; k < 5; k++){
                this.nobleLine.add(tempList.get(0));
                tempList.remove(0);
            }
            return true;
        }
        return false;
    }
    
    public boolean chooseCard(ArrayList Phand, ArrayList enemyhand, Card card){
     if(enemyhand.contains(card)){
     //lets user see enemy hand, chosen card then gets added to user's hand and removed from enemy hand
     enemyhand.remove(card);
     Phand.add(card);
     return true;
     }
     return false;
     }

    public boolean topThreeCards(ArrayList user){
        //puts 3 noble cards in user field in pos 0, 1, 2
        //user then selects the one they want and the others are put in back in deck
        if(!this.deckNoble.isEmpty()){
            user.add(0, this.deckNoble.get(0));
            user.add(1, this.deckNoble.get(1));
            user.add(2, this.deckNoble.get(2));
            chooseCard(user);
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
            this.deckDiscard.add(this.deckNoble.get(0));
            this.deckNoble.remove(0);
        }
        else if( userloc == 1){
            user.remove(2);
            user.remove(0);
            this.deckDiscard.add(this.deckNoble.get(1));
            this.deckNoble.remove(1);
        }
        else{
            user.remove(1);
            user.remove(2);
            this.deckDiscard.add(this.deckNoble.get(2));
            this.deckNoble.remove(2);
        }
    }

    public boolean discardNoble(ArrayList hand, Card card){
        if(hand.contains(card) ){
            hand.remove(card);
            return true;
        }
        return false;
    }

    public boolean placeNoble( ArrayList playerField){
        //check if card is first noble, then check if player exists
        //add card to player's hand
        playerField.add(this.nobleLine.get(0));
        this.nobleLine.remove(0);
        //remove card from noble line
        return true;

    }

    public boolean moveNoble(int nobleCardLocation, int newLocation){
        if(this.nobleLine.size() > nobleCardLocation && this.nobleLine.size() > newLocation ) {
            this.deckDiscard.add(0,this.nobleLine.get(nobleCardLocation));
            this.nobleLine.remove(nobleCardLocation);
            this.nobleLine.add(newLocation, this.deckDiscard.get(0));
            this.deckDiscard.remove(0);
            //code to remove card from array of cards, then make everycard's location inbewteen nobleCardLocation and newLocation have their
            //location +1, then put the card in newLocation
            return true;
        }
        else{
            return false;
        }
    }

//method to call on card ability
    //checks if the card is noble and gets the id to play ability
    //if not noble it gets id of action and plays ability


    //Variables for executing the cards properly
    private int phase1 = -1;
    private int phase2 = -1;
    private int phase3 = -1;
    private int choice1 = -1;
    private int choice2 = -1;


    /**
     * this can be more effective, but i did the best I could in the time I had
     * There are not that many checks for certain cards to see if they are valid
     * Some methods have errors since they require the index of the card that the user chose in onCLick
     * I believe I have a comment for each card to give a short description
     * If you have any question on the specifics of the code I will try my best to get back to you
     * I believe the majority of it isnt too complex to understand
     * https://guillotine-card-translations.github.io/cards/
     * ^^^ Use this to help you with looking at the cards
     */
    public boolean acknowledgeCardAbility(Card card){
        if(card.isNoble){
            switch(card.getId()){

                //add noble from deck to end of card line after collecting this noble
                case "Capt_Guard":
                    this.actionCardPlayed = true;
                    dealNoble();
                    this.actionCardPlayed = false;
                    break;

                case "Count":
                    //needs to go in special calculate points
                    if(this.playerTurn == 0){
                        p0Count = true;
                    }
                    else{
                        p1Count = true;
                    }
                    break;

                case "Countess":
                    //need to go in calculate points statement
                    if(this.playerTurn == 0){
                        p0Countess = true;
                    }
                    else{
                        p1Countess = true;
                    }
                    break;

                case "Fast_Noble":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        getNoble(this.p0Field);
                    }
                    else{
                        getNoble(this.p1Field);
                    }
                    this.actionCardPlayed = false;

                    break;

                case "General":
                    this.actionCardPlayed = true;

                    dealNoble();

                    this.actionCardPlayed = false;

                    break;

                //user selects a card in their action card and the click
                //gets the location and then it uses the location to discard the card
                case "Innocent":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        discardActionCard(this.p0Field, loc);
                    }
                    else{
                        discardActionCard(this.p1Field, loc);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Lady":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        dealActionCard(this.p0Field);
                    }
                    else{
                        dealActionCard(this.p1Field);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Lady_Waiting":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        dealActionCard(this.p0Field);
                    }
                    else{
                        dealActionCard(this.p1Field);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Lord":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        dealActionCard(this.p0Field);
                    }
                    else{
                        dealActionCard(this.p1Field);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Spy":
                    this.actionCardPlayed = true;
                    //need to add method and checks
                    this.actionCardPlayed = false;
                    break;

                case "Palace_Guard1":
                    //ability is in calculate point method
                    if(this.playerTurn == 0){
                        p0PalaceGuard++;
                    }
                    else{
                        p1PalaceGuard++;
                    }
                    break;

                case "Palace_Guard2":
                    //ability is in calculate point method
                    if(this.playerTurn == 0){
                        p0PalaceGuard++;
                    }
                    else{
                        p1PalaceGuard++;
                    }
                    break;

                case "Palace_Guard3":
                    //ability is in calculate point method
                    if(this.playerTurn == 0){
                        p0PalaceGuard++;
                    }
                    else{
                        p1PalaceGuard++;
                    }
                    break;

                case "Palace_Guard4":
                    //ability is in calculate point method
                    if(this.playerTurn == 0){
                        p0PalaceGuard++;
                    }
                    else{
                        p1PalaceGuard++;
                    }
                    break;

                case "Palace_Guard5":
                    //ability is in calculate point method
                    if(this.playerTurn == 0){
                        p0PalaceGuard++;
                    }
                    else{
                        p1PalaceGuard++;
                    }
                    break;

                case "Rival1":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        this.p0Field.add(this.deckNoble.get(0));
                        this.deckNoble.remove(0);
                    }
                    else{
                        this.p1Field.add(this.deckNoble.get(0));
                        this.deckNoble.remove(0);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Rival2":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        this.p0Field.add(this.deckNoble.get(0));
                        this.deckNoble.remove(0);
                    }
                    else{
                        this.p1Field.add(this.deckNoble.get(0));
                        this.deckNoble.remove(0);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Robespierre":
                    this.actionCardPlayed = true;
                    discardRemainingNobles();
                    this.actionCardPlayed = false;
                    break;

                case "Clown":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        placeClown(this.p1Field, this.p0Field.get(this.p0Field.size()-1));
                        this.p0Field.remove(this.p0Field.size()-1);
                    }
                    else{
                        placeClown(this.p0Field, this.p1Field.get(this.p1Field.size()-1));
                        this.p1Field.remove(this.p1Field.size()-1);
                    }
                    this.actionCardPlayed = false;
                    break;

                case "Tragic_Figure":
                    //action is in calculate points method
                    break;


            }
        }
        else{
            switch(card.getId()){

                // put noble at front of line into other players field
                case "After_You":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }

                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        getNoble(this.p1Field);
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("After_You")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        getNoble(this.p0Field);
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("After_You")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble at front of line to end of line
                case "Bribed":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    moveNoble(0, this.nobleLine.size()-1);
                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Bribed")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Bribed")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                case "Callous":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Callous")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Callous")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //card goes to field and is used for the pointer counter
                case "Church_Support":
                    this.actionCardPlayed = true;

                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Church_Support")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Church_Support")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }

                    this.actionCardPlayed = false;
                    break;

                //moves one green card to a different spot in the line
                //gets the locations from the onclick, so i cant implement this
                case "Civic_Pride":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(this.nobleLine.get(loc1).cardColor.equals("Green")) {
                        moveNoble(loc1, loc2);
                    }


                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Civic_Pride")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Civic_Pride")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //increases point values in field. Will be calculated in a points method
                case "Civic_Support":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Civic_Support")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Civic_Support")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //lets players swap nobles (cant swap same noble)
                //need locations that both players choose using onclick
                case "Clerical_Error":
                    this.actionCardPlayed = true;
                    //will write method later


                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Clerical_Error")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Clerical_Error")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //discard any noble in nobleline and replace it with card in Ndeck
                case "Clothing_Swap":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    this.deckDiscard.add(this.nobleLine.get(touchloc));
                    this.nobleLine.remove(touchLoc);
                    this.nobleLine.add(touchloc, this.deckNoble.get(0));
                    this.deckNoble.remove(0);
                    this.actionCardPlayed = false;


                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Clothing_Swap")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Clothing_Swap")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    break;

                //shuffles noble line right before other player draws a noble
                case "Confusion":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(this.playerTurn == 0){

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Confusion")){
                                this.shuffle1 = true;
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Confusion")){
                                this.shuffle0 = true;
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //lets user collect additional noble from noble line
                case "Double_Feature1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        getNoble(this.p0Hand);
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Double_Feature1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        getNoble(this.p1Hand);
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Double_Feature1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //lets user collect additional noble from noble line
                case "Double_Feature2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        getNoble(this.p0Hand);
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Double_Feature2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        getNoble(this.p1Hand);
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Double_Feature2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;
                //discard two nobleline cards and then shuffle the nobleline deck
                case "Escape":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0) {
                        int rand1 = (int) (Math.random() * this.nobleLine.size());
                        this.deckDiscard.add(this.nobleLine.get(rand1));
                        this.nobleLine.remove(rand1);

                        int rand2 = (int) (Math.random() * this.nobleLine.size());
                        this.deckDiscard.add(this.nobleLine.get(rand2));
                        this.nobleLine.remove(rand2);
                        Collections.shuffle(this.nobleLine);

                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Escape")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Escape")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //add 3 nobles from noble deck to noble line
                case "Extra_Cart1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    for(int i = 0; i < 3; i++){
                        dealNoble();
                    }


                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Extra_Cart1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Extra_Cart1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //add 3 nobles from noble deck to noble line
                case "Extra_Cart2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    for(int i = 0; i < 3; i++){
                        dealNoble();
                    }/


                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Extra_Cart2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Extra_Cart2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble up to 3 card positions back
                //locs from onclick
                case "Fainting":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(newPos - origPos > 3){
                        this.nobleLine.add(newPost, this.nobleLine.get(origPost));
                        this.nobleLine.remove(origPost);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Fainting")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Fainting")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //discard any noble in line
                //needs onclick to select the noble
                case "Fled":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    this.nobleLine.remove(cardPos);

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Fled")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Fled")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //randomly removes action card from other player
                case "Forced_Break":
                    this.actionCardPlayed = true;

                    int rand = 0;

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Forced_Break")){
                                if(!this.p1Hand.isEmpty()) {
                                    rand = (int) (Math.random() * this.p1Hand.size());
                                    this.deckDiscard.add(this.p1Hand.get(rand));
                                    this.p1Hand.remove(rand);
                                }
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Forced_Break")){
                                if(!this.p0Hand.isEmpty()) {
                                    rand = (int) (Math.random() * this.p0Hand.size());
                                    this.deckDiscard.add(this.p0Hand.get(rand));
                                    this.p0Hand.remove(rand);
                                }
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //put card in field and draw an actioncard when you get purple noble
                case "Foreign_Support":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Foreign_Support")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                                FS0 = true;
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Foreign_Support")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                                FS0 = true;
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //grabs the first palace guard in noble line and puts it at position 0
                case "Forward_March":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;


                    //going through line to find the first palace guard. Putting in discard
                    //so then I could get the card when readding it.
                    for(int k = 0; k < this.nobleLine.size(); k++){
                        if(this.nobleLine.get(k).getId().contains("Palace_Guard")){
                            this.deckDiscard.add(0,this.nobleLine.get(k));
                            this.nobleLine.remove(k);
                            this.nobleLine.add(0, this.deckDiscard.get(0));
                            this.deckDiscard.remove(0);
                            k = this.nobleLine.size();
                        }
                    }


                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Forward_March")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Forward_March")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //card is in field and worth 2 points
                //actiond one in points method
                case "Fountain":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Fountain")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Fountain")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble backwards up to 2 spaces
                case "Friend_Queen1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(newLoc - oldLoc < 3){
                        moveNoble(oldLoc, newLoc);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Friend_Queen1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Friend_Queen1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card 2 spaces back
                case "Friend_Queen2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(newLoc - oldLoc < 3){
                        moveNoble(oldLoc, newLoc);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Friend_Queen2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Friend_Queen2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble up to two spaces
                case "Idiot1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(oldLoc - newLoc < 3 && oldLoc - newLoc >=0){
                        moveNoble(oldLoc, newLoc);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Idiot1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Idiot1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card up to 2 spaces
                case "Idiot2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(oldLoc - newLoc < 3 && oldLoc - newLoc >=0){
                        moveNoble(oldLoc, newLoc);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Idiot2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Idiot2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble exactly four spaces
                //need onclick location of card they choose
                case "Ignoble1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(oldLoc - 4 >= 0){
                        moveNoble(oldLoc, oldLoc-4);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Ignoble1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Ignoble1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble exactly four spaces
                //need onclick location of card they choose
                case "Ignoble2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(oldLoc - 4 >= 0){
                        moveNoble(oldLoc, oldLoc-4);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Ignoble2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Ignoble2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //makes all gray cards in user field worth 1 point
                //implemented more in point method
                case "Indifferent":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Indifferent")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Indifferent")){
                                this.p0Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //other player discards two action cards
                //uses onclick for user choice
                case "Infighting":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Infighting")){
                                for(int k = 0; k < 2; k ++) {
                                    if(this.p1Hand.size() != 0) {
                                        discardActionCard(this.p1Hand, userLoc);
                                    }
                                }
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Infighting")){
                                for(int k = 0; k < 2; k ++) {
                                    if(this.p0Hand.size() != 0) {
                                        discardActionCard(this.p0Hand, userLoc);
                                    }
                                }
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //trade user hands
                case "Info_Exchange":
                    this.actionCardPlayed = true;
                    tradeHands(this.p0Hand, this.p1Hand);

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Info_Exchange")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Info_Exchange")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move nearest blue noble to front of line
                case "Lack_Faith":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    for(int k = 0; k < this.nobleLine.size(); k++){
                        if(this.nobleLine.get(k).cardColor.equals("Blue")){
                            moveNoble(k, 0);
                        }
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Lack_Faith")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Lack_Faith")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //look at enemy player hand and discard one card
                //cannot do as of right now
                case "Lack_Support":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Lack_Support")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Lack_Support")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;


                //look at top three cards of noble deck and add one to nobleLine
                case "Late_Arrival":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Late_Arrival")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                                topThreeCards(this.p0Field);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Late_Arrival")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                                topThreeCards(this.p1Field);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //if marie is in line, move her to front
                case "Let_Cake":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    for(int k = 0; k < this.nobleLine.size(); k++){
                        if(this.nobleLine.get(k).id.equals("Antoinette")){
                            moveNoble(k, 0);
                        }
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Let_Cake")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Let_Cake")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move purple noble up to 2 spaces ahead
                //needs onclick
                case "Majesty":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(this.nobleLine.get(userLoc).cardColor.equals("Purple")){
                        if(userLoc - newLoc < 3){
                            moveNoble(userLoc, newLoc);
                        }
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Majesty")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Majesty")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //put all noble cards in noble line back in noble deck, shuffle noble deck
                //and redeal the same amount of noble cards that used to be in line
                case "Mass_Confusion":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    int var = this.nobleLine.size();
                    for(int k = 0; k < this.nobleLine.size(); k++){
                        this.deckNoble.add(this.nobleLine.get(k));
                        this.nobleLine.remove(k);
                    }
                    shuffleDecks();
                    for(int l = 0; l < var; l++){
                        dealNoble();
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Mass_Confusion")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Mass_Confusion")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move red card up to two spaces forward
                case "Military_Might":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(this.nobleLine.get(userLoc).cardColor.equals("Red")){
                        if(userLoc - newLoc < 3){
                            moveNoble(userLoc, newLoc);
                        }
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Military_Might")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Military_Might")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //if this card is in your field, then you get +1 points for all red cards you have
                //is implemented in point method
                case "Military_Support":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Military_Support")){
                                this.p0Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Military_Support")){
                                this.p1Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //randomly shuffle first 5 nobles in line
                case "Milling1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    rearrangeFives();

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Milling1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Milling1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //randomly shuffle first five nobles in line
                case "Milling2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    rearrangeFives();

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Milling2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Milling2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //other player places their last collected noble back in noble line
                case "Missed":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    boolean notFound = true;
                    int length;
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Missed")){
                                length = this.p1Field.size()-1;
                                while(notFound){
                                    if(this.p1Field.get(length).isNoble){
                                        this.nobleLine.add(this.p1Field.get(length));
                                        this.p1Field.remove(length);
                                        notFound = false;
                                    }
                                    length--;
                                }

                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Missed")){
                                length = this.p0Field.size()-1
                                while(notFound){
                                    if(this.p0Field.get(length).isNoble){
                                        this.nobleLine.add(this.p0Field.get(length));
                                        this.p0Field.remove(length);
                                        notFound = false;
                                    }
                                    length--;
                                }
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //removes random noble from enemy player noble field
                case "Missing_Heads":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Missing_Heads")){
                                this.p1Field.remove((int)(Math.random()*this.p1Field.size()));
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Missing_Heads")){
                                this.p0Field.remove((int)(Math.random()*this.p0Field.size()));
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //user rearranges the first four nobles however they want
                case "Opinionated":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(this.nobleLine.size() > 3) {
                        moveNoble(0, newLoc);
                        moveNoble(1, newLoc);
                        moveNoble(2, newLoc);
                        moveNoble(3, newLoc);
                    }
                    else{
                        for(int k = 0; k < this.nobleLine.size(); k++){
                            moveNoble(k, newLoc);
                        }
                    }


                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Opinionated")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Opinionated")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //get 3 action cards and skip collect noble phase
                case "Political_Influence1":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Political_Influence1")){
                                dealActionCard(this.p0Hand);
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Political_Influence1")){
                                dealActionCard(this.p1Hand);
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.turnPhase++;
                    this.actionCardPlayed = false;
                    break;

                //get 3 action cards and skip noble draw phase
                case "Political_Influence2":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Political_Influence2")){
                                dealActionCard(this.p0Hand);
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Political_Influence2")){
                                dealActionCard(this.p1Hand);
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.turnPhase++;
                    this.actionCardPlayed = false;
                    break;

                //move anynoble in line to front
                //requires on click
                case "Public_Demand":
                    this.actionCardPlayed = true;
                    moveNoble(userLoc, 0);

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Public_Demand")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Public_Demand")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card exactly 2 spaces in line
                //need on click listener
                case "Pushed1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(userLoc - 2 >=0){
                        moveNoble(userLoc, userLoc-2);
                    }
                    else{
                        moveNoble(userLoc, 0);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Pushed1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Pushed1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card exactly 2 spaces in line
                //need on click listener
                case "Pushed2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(userLoc - 2 >=0){
                        moveNoble(userLoc, userLoc-2);
                    }
                    else{
                        moveNoble(userLoc, 0);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Pushed2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Pushed2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //put players action cards back in action deck, shuffle action deck and give each player 5 cards
                case "Rain_Delay":
                    this.actionCardPlayed = true;

                    while(!this.p0Hand.isEmpty()){
                        this.deckAction.add(this.p0Hand.get(0));
                        this.p0Hand.remove(0);
                    }
                    while(!this.p1Hand.isEmpty()){
                        this.deckAction.add(this.p1Hand.get(0));
                        this.p1Hand.remove(0);
                    }
                    shuffleDecks();
                    for(int k = 0; k < 5; k++){
                        dealActionCard(this.p0Hand);
                        dealActionCard(this.p1Hand);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Rain_Delay")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Rain_Delay")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //put action card in discard pile into your hand
                //need onclick
                case "Rat_Break":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Rat_Break")){
                                if(!this.deckDiscard.get(userLoc).isNoble) {
                                    this.p0Hand.add(this.deckDiscard.get(userLoc));
                                    this.deckDiscard.remove(userLoc);
                                }
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Rat_Break")){
                                if(!this.deckDiscard.get(userLoc).isNoble) {
                                    this.p1Hand.add(this.deckDiscard.get(userLoc));
                                    this.deckDiscard.remove(userLoc);
                                }
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //other player cant play action on their next turn
                case "Rush_Job":
                    this.actionCardPlayed = true;
                    noAction = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Rush_Job")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Rush_Job")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //day ends after player finishes turn, discards all noble line
                case "Scarlet":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    scarletInPlay = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Scarlet")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Scarlet")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move one noble card one spot forward
                case "Stumble1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(userLoc != 0){
                        moveNoble(userLoc, userLoc -1);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Stumble1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Stumble1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move one noble card one spot forward
                case "Stumble2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(userLoc != 0){
                        moveNoble(userLoc, userLoc -1);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Stumble2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Stumble2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //reverse nobleline order
                case "Long_Walk":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    reverseLineOrder();
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Long_Walk")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Long_Walk")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble forward exactly 3 places
                case "Better_Thing":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    if(userLoc - 3 >=0){
                        moveNoble(userLoc, userLoc-3);
                    }
                    else{
                        moveNoble(userLoc, 0);
                    }
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Better_Thing")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Better_Thing")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;


                //add card to other player field, it is worth -2
                //will be done in point method
                case "Tough_Crowd":

                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Tough_Crowd")){
                                this.p1Field.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Tough_Crowd")){
                                this.p0Field.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card back one spot let user play another action
                //i have no idea how to let the user play another action
                case "Trip1":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    this.turnPhase --;

                    if(userLoc != this.nobleLine.size()-1){
                        moveNoble(userLoc, userLoc +1);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Trip1")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Trip1")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move noble card back one spot let user play another action
                //i have no idea how to let the user play another action
                case "Trip2":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;
                    this.turnPhase --;

                    if(userLoc != this.nobleLine.size()-1){
                        moveNoble(userLoc, userLoc +1);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Trip2")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Trip2")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //put any action card in player field into discard
                //need onclick user loc
                case "Twist_Fate":
                    this.actionCardPlayed = true;
                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Twist_Fate")){
                                if(!this.p1Field.get(userLoc).isNoble){
                                    this.deckDiscard.add(this.p1Field.get(userLoc));
                                    this.p1Field.remove(userLoc);
                                }
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Twist_Fate")){
                                if(!this.p0Field.get(userLoc).isNoble){
                                    this.deckDiscard.add(this.p0Field.get(userLoc));
                                    this.p0Field.remove(userLoc);
                                }
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

                //move any noble up to 3 spaces forward
                //need onclick user loc and the new location
                case "Was_Name":
                    if(this.p1Field.contains(card.id.equals("Callous")) || this.p0Field.contains(card.id.equals("Callous")) ){
                        break;
                    }
                    this.actionCardPlayed = true;

                    if(userLoc - newLoc >=0){
                        moveNoble(userLoc, newLoc);
                    }
                    else{
                        moveNoble(userLoc, 0);
                    }

                    if(this.playerTurn == 0){
                        for(int i = 0; i < this.p0Hand.size(); i++){
                            if(this.p0Hand.get(i).getId().equals("Was_Name")){
                                this.deckDiscard.add(this.p0Hand.get(i));
                                this.p0Hand.remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0; i < this.p1Hand.size(); i++){
                            if(this.p1Hand.get(i).getId().equals("Was_Name")){
                                this.deckDiscard.add(this.p1Hand.get(i));
                                this.p1Hand.remove(i);
                            }
                        }

                    }
                    this.actionCardPlayed = false;
                    break;

            }
        }
        return false;
    }

