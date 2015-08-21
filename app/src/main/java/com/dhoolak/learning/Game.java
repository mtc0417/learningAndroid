package com.dhoolak.learning;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by prakasht on 7/31/2015.
 */
public class Game {
    private static Game mInstance = new Game();
    private static Object mutex= new Object();
    private PlayerView mPlayerMe;
    private PlayerView mPlayerMyPartner;
    private PlayerView mPlayerOpponentLeft;
    private PlayerView mPlayerOpponentRight;
    private PlayingArea mPlayingArea;
    private Card.CardSuit mTrump;

    private ArrayList<HandGroup> mHandGroups;
    private HandGroup mHandGroup;
    private Hand mChaal; // currentChaal
    private PlayerView mFirstPlayer;
    private PlayerView[] mPlayerOrder;

    private Game()
    {
        mTrump = Card.CardSuit.UNKNOWN;
        //init();
    }

    public void init()
    {
        // start with random player
        Random random = new Random();
        int rand = random.nextInt(4);
        PlayerView[] players = Game.getInstance().getPlayers();
        PlayerView player = players[rand];
        mChaal = new Hand(player);
        mHandGroup = new HandGroup(player);
        mPlayerOrder = new PlayerView[players.length];
    }
    protected void startNewChaal()
    {
        PlayerView newFirst = mChaal.getWinner();
        mHandGroup.add(mChaal);
        mChaal = new Hand(newFirst);
        setFirstPlayer(newFirst);
    }
    public void addCard(PlayerView player, CardView card)
    {
        mChaal.add(card.getCard());
    }
    protected void addCard(Card card)
    {
        if(mChaal.getState().equals(Hand.State.CLOSED))
        {
            System.out.println("Error: PlayingArea: Adding hand to closed handgroup. Aborting...");
            return;
        }
        mChaal.addCard(card);

        if(mChaal.getState().equals(Hand.State.CLOSED))
        {
            startNewChaal();
        }
    }
    private void setFirstPlayer(PlayerView firstPlayer)
    {
        PlayerView[] players = Game.getInstance().getPlayers();
        mFirstPlayer = firstPlayer;
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
    public void onStart()
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

        mPlayingArea.addCard(mPlayerMyPartner, new CardView(mPlayingArea.getContext(), new Card(Card.CardSuit.PAAN, Card.CardNumber.N6)));
        mPlayingArea.addCard(mPlayerOpponentRight, new CardView(mPlayingArea.getContext(), new Card(Card.CardSuit.EENT, Card.CardNumber.N7)));
        mPlayingArea.addCard(mPlayerMe, new CardView(mPlayingArea.getContext(), new Card(Card.CardSuit.HUKUM, Card.CardNumber.N5)));
        mPlayingArea.addCard(mPlayerOpponentLeft, new CardView(mPlayingArea.getContext(), new Card(Card.CardSuit.CHIDI, Card.CardNumber.N9)));
        System.out.println("Cards distributed");
    }
    public void onAllPlayersLoaded()
    {
        init();
    }
    public PlayerView getNextPlayer(PlayerView currentPlayer)
    {
        PlayerView[] players = getPlayers();
        int count = players.length;
        for(int i = 0; i < players.length; i++)
        {
            if(currentPlayer.equals(players[i]))
            {
                return players[(i+1)%count];
            }
        }
        return null;
    }
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
    public PlayerView getPlayerMe() {return mPlayerMe;}
    public void setPlayerMe(PlayerView mPlayerMe) {
        this.mPlayerMe = mPlayerMe;
    }
    public PlayerView getPlayerMyPartner() {
        return mPlayerMyPartner;
    }
    public void setPlayerMyPartner(PlayerView mPlayerMyPartner) {this.mPlayerMyPartner = mPlayerMyPartner;}
    public PlayerView getPlayerOpponentLeft() {return mPlayerOpponentLeft;}
    public void setPlayerOpponentLeft(PlayerView mPlayerOpponentLeft) {this.mPlayerOpponentLeft = mPlayerOpponentLeft;}
    public PlayerView getPlayerOpponentRight() {return mPlayerOpponentRight;}
    public void setPlayerOpponentRight(PlayerView mPlayerOpponentRight) {this.mPlayerOpponentRight = mPlayerOpponentRight;}
    public void setPlayingArea(PlayingArea mPlayingArea) {this.mPlayingArea = mPlayingArea; }
}
