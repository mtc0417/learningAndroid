package com.dhoolak.learning;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerView extends View{
    public enum PlayerType {
        PLAYER_TYPE_ME, PLAYER_TYPE_MY_FRIEND, PLAYER_TYPE_OPPONENT
    }
    public ArrayList<CardView> getCardList() {
        return mCardList;
    }

    public void setCardList(ArrayList<CardView> cardList) {
        this.mCardList = cardList;
    }

    protected ArrayList<CardView> mCardList;
    protected PlayerType mPlayerType;
    public PlayerView(Context c, PlayerType pt)
    {
        super(c);
        mCardList = new ArrayList<CardView>();
        mPlayerType = pt;
    }
    public void addCard(Card c)
    {
        CardView.CardDisplayState displayState = CardView.CardDisplayState.CLOSE;
        CardView.CardOrientation cardOrientation = CardView.CardOrientation.HORIZONTAL;
        switch (mPlayerType)
        {
            case PLAYER_TYPE_OPPONENT:
                displayState = CardView.CardDisplayState.CLOSE;
                cardOrientation = CardView.CardOrientation.HORIZONTAL;
                break;
            case PLAYER_TYPE_MY_FRIEND:
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
        mCardList.add(card);
    }
}
