package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerViewH extends PlayerView {
    public PlayerViewH(Context c, PlayerType playerType)
    {
        super(c, playerType);
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
