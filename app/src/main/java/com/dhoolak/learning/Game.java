package com.dhoolak.learning;

/**
 * Created by prakasht on 7/31/2015.
 */
public class Game {
    private static Game mInstance = new Game();

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

    private Card.CardSuit mTrump;
    public static Game getInstance()
    {
        return mInstance;
    }

}
