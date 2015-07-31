package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
        redraw();
    }
    public CardView removeCard(Card card)
    {
        // find cardView
        CardView view = null;
        for(CardView v : mCardList)
        {
            if(v.equals(card))
            {
                view = v;
                break;
            }
        }
        if(view == null)
        {
            return view;
        }
        mCardList.remove(view);
        mLayout.removeView(view);
        redraw();
        return view;
    }
    protected void redraw()
    {
        if(mCardList.size() == 0)
        {
            return;
        }
        int cardOverlappingOffset = 45;

        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        int cardWidth = mCardList.get(0).getOriginalWidth();
        int cardHeight = mCardList.get(0).getoriginalHeight();
        int layoutWidth = mLayout.getWidth();
        int layoutHeight = mLayout.getHeight();
        int leftOffset = (layoutWidth - (cardWidth + cardOverlappingOffset * (mCardList.size()-1)))/2;
        int topOffset = (layoutHeight - cardHeight)/2;
        System.out.println("display(" + screenWidth + "x" + screenHeight + "), card(" + cardWidth + "x" + cardHeight + "), layout(" + layoutWidth + "x" + layoutHeight + "), leftOffset:" + leftOffset);
        for(int i = 0; i < mCardList.size(); i++)
        {
            CardView card = mCardList.get(i);
            card.setZ(i);
            card.setX(leftOffset + cardOverlappingOffset * i);
            card.setY(topOffset);
            //card.invalidate();
        }
        mLayout.invalidate();
    }
    protected Context getContext()
    {
        return mLayout.getContext();
    }
}
