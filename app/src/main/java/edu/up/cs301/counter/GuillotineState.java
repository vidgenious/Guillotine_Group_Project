package edu.up.cs301.counter;

import java.util.ArrayList;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class GuillotineState extends GameState {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 7737393762469851826L;

    //These are public for ease of use in the GameActions.java
    public int dayNum;
    public int playerTurn;
    public int p0Score;
    public int p1Score;
    public int lastFirstPlayer;
    public int currFirstPlayer;
    public int turnPhase;
    public ArrayList<Card> p1Hand;
    public ArrayList<Card> p1Field;
    public ArrayList<Card> p0Hand;
    public ArrayList<Card> p0Field;
    public ArrayList<Card> nobleLine;
    public ArrayList<Card> deckDiscard;
    public ArrayList<Card> deckAction;
    public ArrayList<Card> deckNoble;
    public String[] playerNames;
    public boolean actionCardPlayed;

    // constructor to init all variables
    public GuillotineState() {
        this.dayNum = 1;
        this.playerTurn = 0;
        this.p0Score = 0;
        this.p1Score = 0;
        this.lastFirstPlayer = 0;
        this.currFirstPlayer = 0;
        this.turnPhase = 0;
        this.playerNames = new String[2];
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

        this.playerNames = new String[2];
        for(String n : origin.playerNames){
            this.playerNames = origin.playerNames;
        }

    }

    //This initiates all of the noble cards ands places them into the deck unshuffled
    public void initNobleDeck(){
        this.deckNoble.add(new Card(true, false, 4, "Blue","Archbishop"));
        this.deckNoble.add(new Card(true, false, 3, "Blue","Bad_Nun"));
        this.deckNoble.add(new Card(true, false, 3, "Purple","Baron"));
        this.deckNoble.add(new Card(true, false, 2, "Blue","Bishop"));
        this.deckNoble.add(new Card(true, true, 2,  "Red","Capt_Guard"));
        this.deckNoble.add(new Card(true, false, 5, "Blue","Cardinal"));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Coiffeur"));
        this.deckNoble.add(new Card(true, false, 3, "Red","Colonel"));
        this.deckNoble.add(new Card(true, false, 3, "Green","Councilman"));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Count"));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Countess"));
        this.deckNoble.add(new Card(true, false, 3, "Purple","Duke"));
        this.deckNoble.add(new Card(true, true, 2,  "Purple","Fast_Noble"));
        this.deckNoble.add(new Card(true, true, 4,  "Red","General"));
        this.deckNoble.add(new Card(true, false, 4, "Green","Governer"));
        this.deckNoble.add(new Card(true, false, 2, "Blue","Heretic"));
        this.deckNoble.add(new Card(true, false, -3, "Grey","Hero_People"));
        this.deckNoble.add(new Card(true, true, -1,  "Grey","Innocent"));
        this.deckNoble.add(new Card(true, false, 5,  "Purple","King_Louis"));
        this.deckNoble.add(new Card(true, true, 2, "Purple","Lady"));
        this.deckNoble.add(new Card(true, true, 1, "Purple","Lady_Waiting"));
        this.deckNoble.add(new Card(true, false, 2, "Green","Land_Lord"));
        this.deckNoble.add(new Card(true, false, 2, "Red","Lieutenant1"));
        this.deckNoble.add(new Card(true, false, 2, "Red","Lieutenant2"));
        this.deckNoble.add(new Card(true,true, 2, "Purple","Lord"));
        this.deckNoble.add(new Card(true, false, 5, "Purple","Antoinette"));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr1"));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr2"));
        this.deckNoble.add(new Card(true, false, -1, "Grey","Martyr3"));
        this.deckNoble.add(new Card(true, true, 4, "Red","Spy"));
        this.deckNoble.add(new Card(true, false, 3, "Green","Mayor"));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard1"));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard2"));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard3"));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard4"));
        this.deckNoble.add(new Card(true, true, 0, "Red","Palace_Guard5"));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Piss_Boy"));
        this.deckNoble.add(new Card(true, false, 4, "Purple","Regent"));
        this.deckNoble.add(new Card(true, true, 1, "Green","Rival1"));
        this.deckNoble.add(new Card(true, true, 1, "Green","Rival2"));
        this.deckNoble.add(new Card(true, true, 3, "Purple","Robespierre"));
        this.deckNoble.add(new Card(true, false, 1, "Purple","Cartographer"));
        this.deckNoble.add(new Card(true, false, 1, "Green","Sheriff1"));
        this.deckNoble.add(new Card(true, false, 1, "Green","Sheriff2"));
        this.deckNoble.add(new Card(true, false, 2, "Green","Tax_Collector"));
        this.deckNoble.add(new Card(true, true, -2, "Grey","Clown"));
        this.deckNoble.add(new Card(true, true, 0, "Grey","Tragic_Figure"));
        this.deckNoble.add(new Card(true, true, 2, "Green","Judge1"));
        this.deckNoble.add(new Card(true, true, 2, "Green","Judge2"));
        this.deckNoble.add(new Card(true, false, 1, "Blue","Wealthy_Priest1"));
        this.deckNoble.add(new Card(true, false, 1, "Blue","Wealthy_Priest2"));

    }


    //This initiates all of the action cards ands places them into the deck unshuffled
    private void initActionDeck(){
        this.deckAction.add(new Card(false, true, 0, "actionCard", "After_You"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Bribed"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Callous"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Church_Support"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Civic_Pride"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Civic_Support"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Clerical_Error"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Clothing_Swap"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Confusion"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Double_Feature1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Double_Feature2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Escape"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Extra_Cart1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Extra_Cart2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fainting"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fled"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Forced_Break"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Foreign_Support"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Forward_March"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Fountain"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Friend_Queen1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Friend_Queen2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Idiot1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Idiot2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Ignoble1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Ignoble2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Indifferent"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Infighting"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Info_Exchange"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Lack_Faith"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Lack_Support"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Late_Arrival"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Let_Cake"));
        this.deckAction.add(new Card(false, true, 0, "actionCard", "Majesty"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Mass_Confusion"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Military_Might"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Military_Support"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Milling1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Milling2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Missed"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Missing_Heads"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Opinionated"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Political_Influence1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Political_Influence2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Public_Demand"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Pushed1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Pushed2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rain_Delay"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rat_Break"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Rush_Job"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Scarlet"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Stumble1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Stumble2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Long_Walk"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Better_Thing"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Tough_Crowd"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Trip1"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Trip2"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Twist_Fate"));
        this.deckAction.add(new Card(false, true, 0, "actionCard","Was_Name"));
    }
}

