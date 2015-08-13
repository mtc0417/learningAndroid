package com.dhoolak.learning;

import java.util.ArrayList;

/**
 * Created by prakasht on 8/12/2015.
 */
public class Chaal {
    private ArrayList<Card> mCards;
    private PlayerView mPlayerWon;
    private PlayerView mFirstPlayer;

    public Chaal()
    {
        mCards = new ArrayList<Card>();
    }

    public int size()
    {
        return mCards.size();
    }
    public Card getInitialChaal()
    {
        if(mCards.size() == 0)
        {
            return null;
        }
        return mCards.get(0);
    }
    public boolean hasDahla()
    {
        for(int i = 0; i < mCards.size(); i++)
        {
            if(mCards.get(i).isDahla())
                return true;
        }
        return false;
    }
    public boolean isTrumpChaal()
    {
        if(mCards.size() == 0)
            return false;
        return mCards.get(0).isTrump();
    }
    public boolean hasTrump()
    {
        for(int i = 0; i < mCards.size(); i++)
        {
            if(mCards.get(i).isTrump())
                return true;
        }
        return false;
    }
}
