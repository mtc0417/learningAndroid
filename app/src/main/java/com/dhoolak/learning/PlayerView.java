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
public class PlayerView extends ViewGroup{

    public enum PlayerType {
        PLAYER_TYPE_ME , PLAYER_TYPE_OPPONENT_LEFT, PLAYER_TYPE_MY_PARTNER, PLAYER_TYPE_OPPONENT_RIGHT
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
    //protected ViewGroup mLayout;
    public PlayerView(Context c)
    {
        super(c);
        mCardList = new ArrayList<CardView>();
    }
    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int playerType = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/com.dhoolak.learning","playerType", 3);
        String type = attrs.getAttributeName(0);
        /*
        for(int i = 0; i < attrs.getAttributeCount(); i++)
        {
            System.out.println("Listing attributes i:" + i + ", name :" + attrs.getAttributeName(i));
            if(attrs.getAttributeName(i).equals("playerType"))
            {
                playerType = attrs.getAttributeIntValue(i, 3);
                break;
            }
        }
        */
        mPlayerType = PlayerType.values()[playerType];
        System.out.println("mPlayerType:" + mPlayerType + ", indexString(0):" + type);
        mCardList = new ArrayList<CardView>();
        initView(context);
        setGlobalPlayerView();
    }
    private void setGlobalPlayerView()
    {
        switch(mPlayerType)
        {
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
        System.out.println("def mPlayerType:" + playerType + ", inDexString(5):" + type);

        initView(context);
    }
    protected void initView(Context context){
        //PlayerView bottomLayout = this;//(PlayerView)findViewById(R.id.mainPlayerView);
        //bottomLayout.addCard(new Card(Card.CardSuit.HUKUM, Card.CardNumber.N5));
        //bottomLayout.addCard(new Card(Card.CardSuit.CHIDI, Card.CardNumber.N3));
        //bottomLayout.addCard(new Card(Card.CardSuit.EENT, Card.CardNumber.N10));
        //bottomLayout.addCard(new Card(Card.CardSuit.PAAN, Card.CardNumber.N1));
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("onLayout:changed:" + changed + ", left:" + left + ", top:" + top + ", right:" + right + ", bottom:" + bottom);
        //redraw(left, top, right, bottom);
        if(mPlayerType.equals(PlayerType.PLAYER_TYPE_ME) || mPlayerType.equals(PlayerType.PLAYER_TYPE_MY_PARTNER)) {
            redrawHorizontal(0, 0, right - left, bottom - top);
        }
        else
        {
            redrawVertical(0, 0, right-left, bottom-top);
        }
        for(int i = 0 ; i < getChildCount() ; i++){
            //getChildAt(i).layout(left, top, right, bottom);
        }
    }
    public void addCard(Card[] cards)
    {
        for(Card card : cards)
        {
            addCard(card);
        }
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
        Collections.sort(mCardList, Collections.reverseOrder());
        addView(card);
        //redraw();
        invalidate();
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
        removeView(view);
        //redraw();
        invalidate();
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
        int layoutWidth = getWidth();
        int layoutHeight = getHeight();
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
        invalidate();
    }

    protected void redrawHorizontal(int left, int top, int right, int bottom)
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
        int layoutWidth = right-left;//getWidth();
        int layoutHeight = bottom-top;//getHeight();
        int leftOffset = (layoutWidth - (cardWidth + cardOverlappingOffset * (mCardList.size()-1)))/2;
        int topOffset = (layoutHeight - cardHeight)/2;
        System.out.println("children:" + mCardList.size() + ", display(" + screenWidth + "x" + screenHeight + "), card(" + cardWidth + "x" + cardHeight + "), layout(" + layoutWidth + "x" + layoutHeight + "), leftOffset:" + leftOffset);
        for(int i = 0; i < mCardList.size(); i++)
        {
            CardView card = mCardList.get(i);
            card.setZ(i);
            //card.setX(left + leftOffset + cardOverlappingOffset * i);
            //card.setY(top + topOffset);
            card.layout(left + leftOffset + cardOverlappingOffset * i, top + topOffset, left + leftOffset + cardOverlappingOffset * i + cardWidth, top + topOffset + cardHeight);
            //card.invalidate();
        }
        //invalidate();
    }

    protected void redrawVertical(int left, int top, int right, int bottom)
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
        int layoutWidth = right-left;//getWidth();
        int layoutHeight = bottom-top;//getHeight();
        int topOffset = (layoutHeight - (cardHeight + cardOverlappingOffset * (mCardList.size()-1)))/2;
        int leftOffset = (layoutWidth - cardWidth)/2;
        System.out.println("children:" + mCardList.size() + ", display(" + screenWidth + "x" + screenHeight + "), card(" + cardWidth + "x" + cardHeight + "), layout(" + layoutWidth + "x" + layoutHeight + "), leftOffset:" + leftOffset);
        for(int i = 0; i < mCardList.size(); i++)
        {
            CardView card = mCardList.get(i);
            card.setZ(i);
            //card.setX(left + leftOffset + cardOverlappingOffset * i);
            //card.setY(top + topOffset);
            card.layout(left + leftOffset, top + topOffset + cardOverlappingOffset * i, left + leftOffset + cardWidth, top + topOffset + cardOverlappingOffset * i + cardHeight);
            //card.invalidate();
        }
        //invalidate();
    }
}
