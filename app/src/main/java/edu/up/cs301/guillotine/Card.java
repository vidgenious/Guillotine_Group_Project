package edu.up.cs301.guillotine;

import android.graphics.Bitmap;

/**
 * @author William Cloutier
 * @author Moses Karemera
 * @author Maxwell McAtee
 */

/*
 * This class is very bare bones and not in its final state. It only has the information
 * necessary for part d
 */
public class Card {
    //Instance variables for a card object
    public boolean isNoble;
    public boolean hasEffect;
    public int points;
    public String cardColor;
    public String id;
    public int image;

    //Constructor for Card object
    public Card(boolean isNoble, boolean hasEffect, int points, String cardColor,  String id, int image){
        this.isNoble = isNoble;
        this.hasEffect = hasEffect;
        this.points = points;
        this.cardColor = cardColor;
        this.id = id;
        this.image = image;
    }

    public String getId(){ return this.id;}
}
