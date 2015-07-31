package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerView{

    public enum PlayerType {
        PLAYER_TYPE_ME , PLAYER_TYPE_MY_PARTNER, PLAYER_TYPE_OPPONENT_LEFT, PLAYER_TYPE_OPPONENT_RIGHT
    }
    public ArrayList<CardView> getCardList() {
        return mCardList;
    }

    public void setCardList(ArrayList<CardView> cardList) {
        this.mCardList = cardList;
    }

    protected ArrayList<CardView> mCardList;

    public PlayerType getPlayerType() {
        return mPlayerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.mPlayerType = playerType;
    }

    protected PlayerType mPlayerType;
    protected ViewGroup mLayout;
    public PlayerView(ViewGroup layout)
    {
        mLayout = layout;
        mCardList = new ArrayList<CardView>();
    }
    public PlayerView(ViewGroup layout, PlayerType pt)
    {
        this(layout);
        mPlayerType = pt;
    }
    public void addCard(Card c)
    {
        CardView.CardDisplayState displayState = CardView.CardDisplayState.CLOSE;
        CardView.CardOrientation cardOrientation = CardView.CardOrientation.HORIZONTAL;
        switch (mPlayerType)
        {
            case PLAYER_TYPE_OPPONENT_LEFT:
            case PLAYER_TYPE_OPPONENT_RIGHT:
                displayState = CardView.CardDisplayState.CLOSE;
                cardOrientation = CardView.CardOrientation.HORIZONTAL;
                break;
            case PLAYER_TYPE_MY_PARTNER:
                displayState = CardView.CardDisplayState.CLOSE;
                cardOrientation = CardView.CardOrientation.VERTICAL;
                break;
            case PLAYER_TYPE_ME:
                displayState = CardView.CardDisplayState.OPEN;
                cardOrientation = CardView.CardOrientation.VERTICAL;
                break;
        }
        CardView card = new CardView(this.getContext(), c);
        card.setCardDisplayState(displayState);
        card.setCardOrientation(cardOrientation);
        card.loadImage();
        mCardList.add(card);
        Collections.sort(mCardList);
        mLayout.addView(card);
    }
    protected Context getContext()
    {
        return mLayout.getContext();
    }
    public void addView(View view)
    {
        mLayout.addView(view);
    }
}
