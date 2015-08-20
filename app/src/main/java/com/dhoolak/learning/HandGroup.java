package com.dhoolak.learning;

import java.util.ArrayList;

/**
 * Created by prakasht on 8/18/2015.
 * Hand group is set of hands(running or won)
 */
public class HandGroup {
    public enum State {RUNNING, CLOSED};

    private PlayerView mWinner; // winner of this hand group
    private ArrayList<Hand> mHands;
    private Hand mRunningHand;
    private HandGroup.State mState;

    public HandGroup(PlayerView firstPlayer)
    {
        mHands = new ArrayList<Hand>();
        mRunningHand = new Hand(firstPlayer);
        mState = State.RUNNING;
    }
    public void add(Hand hand)
    {
        if(mState.equals(State.CLOSED))
        {
            System.out.println("Error: HandGroup: Adding hand to closed handgroup. Aborting...");
            return;
        }
    }
    public int size()
    {
        return mHands.size();
    }
    public int getDahlaCount()
    {
        int count = 0;
        for(int i = 0; i < mHands.size(); i++)
        {
            count += mHands.get(i).getDahlaCount();
        }
        return count;
    }
    public PlayerView getWinner() {
        return mWinner;
    }
    public ArrayList<Hand> getHands() {
        return mHands;
    }
    public State getState() {
        return mState;
    }
}
