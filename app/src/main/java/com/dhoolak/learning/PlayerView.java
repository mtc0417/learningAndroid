package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerView extends ViewGroup {

    public enum PlayerType {
        PLAYER_TYPE_ME, PLAYER_TYPE_OPPONENT_LEFT, PLAYER_TYPE_MY_PARTNER, PLAYER_TYPE_OPPONENT_RIGHT
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

    public String getPlayerName() {
        return mPlayerName;
    }

    public void setPlayerName(String playerName) {
        this.mPlayerName = playerName;
    }

    private String mPlayerName;
    public PlayerView(Context c) {
        super(c);
        mCardList = new ArrayList<CardView>();
        initView(c);
    }

    public PlayerView(Context c, PlayerType playerType) {
        super(c);
        mCardList = new ArrayList<CardView>();
        mPlayerType = playerType;
        mPlayerName = playerType.toString();
        initView(c);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int playerType = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/com.dhoolak.learning", "playerType", 3);
        String type = attrs.getAttributeName(0);
        mPlayerType = PlayerType.values()[playerType];
        System.out.println("mPlayerType:" + mPlayerType + ", indexString(0):" + type);
        mCardList = new ArrayList<CardView>();
    }

    private void setGlobalPlayerView() {
        switch (mPlayerType) {
            case PLAYER_TYPE_ME:
                Game.getInstance().setPlayerMe(this);
                break;
            case PLAYER_TYPE_OPPONENT_LEFT:
                Game.getInstance().setPlayerOpponentLeft(this);
                break;
            case PLAYER_TYPE_MY_PARTNER:
                Game.getInstance().setPlayerMyPartner(this);
                break;
            case PLAYER_TYPE_OPPONENT_RIGHT:
                Game.getInstance().setPlayerOpponentRight(this);
                break;
        }
    }

    public PlayerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCardList = new ArrayList<CardView>();
        int playerType = attrs.getAttributeIntValue(context.getPackageName(), "playerType", 0);
        String type = attrs.getAttributeName(5);
        initView(context);
    }

    protected void initView(Context context) {
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("onLayout:changed:" + changed + ", left:" + left + ", top:" + top + ", right:" + right + ", bottom:" + bottom);
        //redraw(left, top, right, bottom);
        //redraw(left, top, right, bottom);
        redraw(0, 0, right - left, bottom - top);
        if (mPlayerType.equals(PlayerType.PLAYER_TYPE_ME) || mPlayerType.equals(PlayerType.PLAYER_TYPE_MY_PARTNER)) {
            //redrawHorizontal(0, 0, right - left, bottom - top);
        } else {
            //redrawVertical(0, 0, right - left, bottom - top);
        }
    }

    public void addCard(Card[] cards) {
        System.out.printf("\nAdding " + cards.length + " cards to " + getPlayerName());
        for (Card card : cards) {
            addCard(card);
            System.out.printf(" " + card.toString());
        }
    }
    public void addCard(Card c) {
        CardView.CardDisplayState displayState = CardView.CardDisplayState.CLOSE;
        CardView.CardOrientation cardOrientation = CardView.CardOrientation.HORIZONTAL;
        switch (mPlayerType) {
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
        Collections.sort(mCardList, Collections.reverseOrder());
        addView(card, new LayoutParams(card.getOriginalWidth(), card.getOriginalHeight()));
    }
    public void removeAllCards()
    {
        mCardList.clear();
        removeAllViews();
    }

    public CardView removeCard(Card card) {
        // find cardView
        CardView view = null;
        for (CardView v : mCardList) {
            if (v.equals(card)) {
                view = v;
                break;
            }
        }
        if (view == null) {
            return view;
        }
        mCardList.remove(view);
        removeView(view);
        return view;
    }
    protected void redraw(int left, int top, int right, int bottom)
    {}
    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    public void addButton()
    {
        Button btn1 = new Button(this.getContext());
        btn1.setText(this.getPlayerName());
        this.addView(btn1);
        btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

    }
}
