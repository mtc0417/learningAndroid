package com.dhoolak.learning;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by prakasht on 8/21/2015.
 */
public class PlayerViewMe extends PlayerViewH {
    public PlayerViewMe(Context c, PlayerType playerType)
    {
        super(c, playerType);
    }
    public CardView addCard(Card card) {
        CardView cardView = super.addCard(card);
        //cardView.onTouchEvent()
        return cardView;
    }

    protected void redraw(int left, int top, int right, int bottom)
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
        int cardHeight = mCardList.get(0).getOriginalHeight();
        int layoutWidth = right-left;//getWidth();
        int layoutHeight = bottom-top;//getHeight();

        cardOverlappingOffset = 3*cardWidth/4;
        if((cardWidth + cardOverlappingOffset + cardOverlappingOffset * (mCardList.size()-1)) > layoutWidth)
        {
            cardOverlappingOffset = (layoutWidth - cardWidth) / (mCardList.size());
        }
        int leftOffset = (layoutWidth - (cardWidth + cardOverlappingOffset * (mCardList.size()-1)))/2;
        int topOffset = (layoutHeight - cardHeight)/2;
        //System.out.println("children:" + mCardList.size() + ", display(" + screenWidth + "x" + screenHeight + "), card(" + cardWidth + "x" + cardHeight + "), layout(" + layoutWidth + "x" + layoutHeight + "), leftOffset:" + leftOffset);
        for(int i = 0; i < mCardList.size(); i++)
        {
            CardView card = mCardList.get(i);
            //card.setZ(i);
            card.bringToFront();
            card.layout(left + leftOffset + cardOverlappingOffset * i, top + topOffset, left + leftOffset + cardOverlappingOffset * i + cardWidth, top + topOffset + cardHeight);
        }
    }

}
