package com.dhoolak.learning;

import java.util.ArrayList;

/**
 * Created by prakasht on 8/12/2015.
 */
public class Hand {
    public enum State {RUNNING, CLOSED};
    private ArrayList<Card> mCards;
    private PlayerView mPlayerWon;
    private PlayerView mFirstPlayer;
    private State mState;

    public Hand()
    {
        mCards = new ArrayList<Card>();
        mState = State.RUNNING;
    }

    public void addCard(Card card) {
        if (mCards.size() > 3) {
            return; // no-op
        }
        mCards.add(card);
        if (mCards.size() == 4) {
            mState = State.CLOSED;
        }
        setWinner();
    }
    public void setWinner()
    {
        // find the player who wins this hand
        Card maxCard = mCards.get(0);
        PlayerView winner = mFirstPlayer;
        for(int i = 1; i < mCards.size(); i++)
        {
            Card currentCard = mCards.get(i);
            PlayerView currentPlayer = Game.getInstance().getNextPlayer(winner);
            if(currentCard.compareTo(maxCard) > 0)
            {
                maxCard = currentCard;
                winner = currentPlayer;
            }
        }
        mPlayerWon = winner;
    }
    public int size()
    {
        return mCards.size();
    }
    public Card getFirstCard()
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
