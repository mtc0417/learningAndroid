package com.dhoolak.learning;

/**
 * Created by prakasht on 7/31/2015.
 */
public class Game {
    private static Game mInstance = new Game();

    private Game()
    {
        mTrump = Card.CardSuit.UNKNOWN;
    }
    public Card.CardSuit getTrump() {
        return mTrump;
    }
    public boolean isTrumpSet()
    {
        return mTrump != Card.CardSuit.UNKNOWN;
    }
    public void setTrump(Card.CardSuit mTrump) {
        this.mTrump = mTrump;
    }

    private Card.CardSuit mTrump;
    public static Game getInstance()
    {
        return mInstance;
    }

}
