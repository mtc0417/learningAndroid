package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by prakasht on 8/12/2015.
 */
public class PlayingArea extends ViewGroup {

    private Map<PlayerView.PlayerType, CardView> mPlayerCardMap;

    public PlayingArea(Context c) {
        super(c);
        init();
    }

    private void init()
    {
        mPlayerCardMap = new TreeMap<PlayerView.PlayerType, CardView>();
    }
    public void addCard(PlayerView player, CardView card)
    {
        mPlayerCardMap.put(player.getPlayerType(), card);
        card.setStates(CardView.DisplayState.OPEN, CardView.Orientation.VERTICAL);
        addView(card);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("PlayingArea:onLayout:changed:" + changed + ", left:" + l + ", top:" + t + ", right:" + r + ", bottom:" + b);
        for(Map.Entry<PlayerView.PlayerType, CardView> entry : mPlayerCardMap.entrySet())
        {
            drawCard(entry.getKey(), entry.getValue(), 0, 0, r-l, b-t);
        }
    }

    private void drawCard(PlayerView.PlayerType playerType, CardView card, int l, int t, int r, int b)
    {
        int width = r-l;
        int height = b-t;
        int cardWidth = card.getOriginalWidth();
        int cardHeight = card.getOriginalHeight();
        int leftMargin = width/2 - 5*cardWidth/4;
        if(leftMargin < 0)
        {
            leftMargin = width/2 - cardWidth;
        }
        if(leftMargin < 0)
        {
            leftMargin = 0;
        }
        int topMargin = height/2 - 5*cardHeight/4;
        if(topMargin < 0)
        {
            topMargin = height/2 - cardHeight;
        }
        if(topMargin < 0)
        {
            topMargin = 0;
        }
        int left = 0, right = 0, top = 0, bottom = 0;
        switch (playerType)
        {
            case PLAYER_TYPE_ME:
                left = l + width/2 - cardWidth/2;
                top = t + height - topMargin - cardHeight;
                break;
            case PLAYER_TYPE_MY_PARTNER:
                left = l + width/2 - cardWidth/2;
                top = t + topMargin;
                break;
            case PLAYER_TYPE_OPPONENT_LEFT:
                left = l + leftMargin;
                top = t + height/2 - cardHeight/2;
                break;
            case PLAYER_TYPE_OPPONENT_RIGHT:
                left = l + width - leftMargin - cardWidth;
                top = t + height/2 - cardHeight/2;
                break;
        }
        right = left + cardWidth;
        bottom = top + cardHeight;
        card.layout(left, top, right, bottom);
        System.out.println("PlayingArea:draw: card(" + cardWidth + "x" + cardHeight + "), leftOffset:" + leftMargin + ", topMargin:" + topMargin + "(" + left + ", " + top + ", " + right + ", " + bottom + ")");
    }
    public void removeAllCards()
    {
        for(Map.Entry<PlayerView.PlayerType, CardView> entry : mPlayerCardMap.entrySet())
        {
            removeCard(entry.getValue());
        }
        mPlayerCardMap.clear();
    }
    public void removeCard(CardView card)
    {
        removeView(card);
    }
    public void reset()
    {
        removeAllCards();
    }
}
