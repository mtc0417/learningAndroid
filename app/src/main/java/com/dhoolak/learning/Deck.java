package com.dhoolak.learning;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by prakasht on 8/3/2015.
 */
public class Deck {
    private Card[] mCards;
    private static Deck mInstance = null;
    private int mCurrentIndex = 0;
    private static Object mutex= new Object();
    public static Deck getInstance()
    {
        if(mInstance != null)
        {
            return mInstance;
        }
        synchronized (mutex)
        {
            if(mInstance == null) {
                mInstance = new Deck();
                mInstance.initCards();
            }
        }
        return mInstance;
    }
    private void initCards()
    {
        ArrayList<Card> cardList = new ArrayList<Card>();
        for(Card.CardSuit suit : Card.CardSuit.values())
        {
            if(suit.equals(Card.CardSuit.UNKNOWN))
            {
                continue;
            }
            for(Card.CardNumber number : Card.CardNumber.values())
            {
                cardList.add(new Card(suit, number));
            }
        }
        mCards = cardList.toArray(new Card[0]);
    }
    public void shuffle()
    {
        Random random = new Random();
        int currentMax = mCards.length;
        for(int i  = 0; i < mCards.length; i++)
        {
            int rand = random.nextInt(currentMax);
            Card temp = mCards[rand];
            mCards[rand] = mCards[currentMax-1];
            mCards[currentMax-1] = temp;
            currentMax--;
        }
        mCurrentIndex = 0;
        //System.out.println("Random numbers generated:");
        for(int i  = 0; i < mCards.length; i++)
        {
            System.out.printf(mCards[i] + ", ");
        }
    }
    public Card[] getCards(int cardCount)
    {
        if(mCurrentIndex + cardCount > mCards.length)
        {
            cardCount = mCards.length - mCurrentIndex;
        }
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i = mCurrentIndex; i < mCurrentIndex + cardCount; i++)
        {
            cards.add(mCards[i]);
        }
        mCurrentIndex += cardCount;
        return cards.toArray(new Card[0]);
    }
}
