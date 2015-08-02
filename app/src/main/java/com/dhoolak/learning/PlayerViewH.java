package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerViewH extends PlayerView {
    public PlayerViewH(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public PlayerViewH(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    protected void initView(Context context){
        View.inflate(context, R.id.mainPlayerView, this);  //correct way to inflate..
    }
}
