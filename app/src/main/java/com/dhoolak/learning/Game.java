package com.dhoolak.learning;

/**
 * Created by prakasht on 7/31/2015.
 */
public class Game {
    private static Game mInstance = new Game();
    private static Object mutex= new Object();
    public static Game getInstance()
    {
        if(mInstance != null)
        {
            return mInstance;
        }
        synchronized (mutex)
        {
            if(mInstance == null)
                mInstance = new Game();
        }
        return mInstance;
    }
    public PlayerView getPlayerMe() {
        return mPlayerMe;
    }

    public void setPlayerMe(PlayerView mPlayerMe) {
        this.mPlayerMe = mPlayerMe;
    }

    public PlayerView getPlayerMyPartner() {
        return mPlayerMyPartner;
    }

    public void setPlayerMyPartner(PlayerView mPlayerMyPartner) {
        this.mPlayerMyPartner = mPlayerMyPartner;
    }

    public PlayerView getPlayerOpponentLeft() {
        return mPlayerOpponentLeft;
    }

    public void setPlayerOpponentLeft(PlayerView mPlayerOpponentLeft) {
        this.mPlayerOpponentLeft = mPlayerOpponentLeft;
    }

    public PlayerView getPlayerOpponentRight() {
        return mPlayerOpponentRight;
    }

    public void setPlayerOpponentRight(PlayerView mPlayerOpponentRight) {
        this.mPlayerOpponentRight = mPlayerOpponentRight;
    }

    private PlayerView mPlayerMe;
    private PlayerView mPlayerMyPartner;
    private PlayerView mPlayerOpponentLeft;
    private PlayerView mPlayerOpponentRight;
    private Game()
    {
        mTrump = Card.CardSuit.UNKNOWN;
    }
    public Card.CardSuit getTrump() {
        return mTrump;
    }
    public PlayerView[] getPlayers()
    {
        PlayerView[] playerViews = {mPlayerMe, mPlayerOpponentLeft, mPlayerMyPartner, mPlayerOpponentRight};
        return playerViews;
    }
    public boolean isTrumpSet()
    {
        return mTrump != Card.CardSuit.UNKNOWN;
    }
    public void setTrump(Card.CardSuit mTrump) {
        this.mTrump = mTrump;
    }
    private void checkIfAllPlayersLoaded()
    {
        if(mPlayerOpponentRight != null && mPlayerMyPartner != null && mPlayerOpponentLeft != null && mPlayerMe != null)
        {
            //onAllPlayersLoaded();
        }
    }
    public void onAllPlayersLoaded()
    {
        Deck deck = Deck.getInstance();
        deck.shuffle();
        MainLayout.getInstance().removeAllCards();
        mPlayerMe.addCard(deck.getCards(5));
        mPlayerOpponentLeft.addCard(deck.getCards(5));
        mPlayerMyPartner.addCard(deck.getCards(5));
        mPlayerOpponentRight.addCard(deck.getCards(5));
        for(int i = 0; i < 2; i++)
        {
            mPlayerMe.addCard(deck.getCards(4));
            mPlayerOpponentLeft.addCard(deck.getCards(4));
            mPlayerMyPartner.addCard(deck.getCards(4));
            mPlayerOpponentRight.addCard(deck.getCards(4));
        }
        //MainLayout.getInstance().invalidate();
        System.out.println("Cards distributed");
    }
    public void onStart()
    {
        onAllPlayersLoaded();
    }
    private Card.CardSuit mTrump;
}
