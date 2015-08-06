package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by prakasht on 8/6/2015.
 */
public class MainLayout extends ViewGroup {
    private static MainLayout mInstance;
    private PlayerView mPlayerMe;
    private PlayerView mPlayerMyPartner;
    private PlayerView mPlayerOpponentLeft;
    private PlayerView mPlayerOpponentRight;
    private LinearLayout mControlVG;


    public static MainLayout getInstance()
    {
        return mInstance;
    }
    public MainLayout(Context c)
    {
        super(c);
        mInstance = this;
    }
    public MainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        mInstance = this;
    }
    public MainLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
        mInstance = this;
    }
    protected void initView(Context context){
        mPlayerMe = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_ME);
        mPlayerMyPartner = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_MY_PARTNER);
        mPlayerOpponentLeft = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_OPPONENT_LEFT);
        mPlayerOpponentRight = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_OPPONENT_RIGHT);
        addView(mPlayerMe);
        addView(mPlayerMyPartner);
        addView(mPlayerOpponentLeft);
        addView(mPlayerOpponentRight);

        mPlayerMe.setBackgroundColor(0xFF00FF00);
        mPlayerMyPartner.setBackgroundColor(0xFF00FF00);
        mPlayerOpponentRight.setBackgroundColor(0xFF0000FF);
        mPlayerOpponentLeft.setBackgroundColor(0xFF0000FF);

        Game game = Game.getInstance();
        game.setPlayerMe(mPlayerMe);
        game.setPlayerMyPartner(mPlayerMyPartner);
        game.setPlayerOpponentLeft(mPlayerOpponentLeft);
        game.setPlayerOpponentRight(mPlayerOpponentRight);
        //game.onAllPlayersLoaded();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("MainLayout:onLayout:changed:" + changed + ", left:" + l + ", top:" + t + ", right:" + r + ", bottom:" + b);
        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        mPlayerMyPartner.layout(l, t, r, b/4);
        mPlayerOpponentLeft.layout(l, b/4, l+r/3, 3*b/4);
        mPlayerOpponentRight.layout(2*r/3, b/4, r, 3*b/4);
        mPlayerMe.layout(l, 3*b/4, l+r, b);

    }
    public PlayerView getPlayerMe() {
        return mPlayerMe;
    }

    public void setPlayerMe(PlayerView mPlayerMe) {
        this.mPlayerMe = mPlayerMe;
    }

    public PlayerView getPlayerMyPartner() {
        return mPlayerMyPartner;
    }

    public PlayerView getPlayerOpponentLeft() {
        return mPlayerOpponentLeft;
    }

    public PlayerView getPlayerOpponentRight() {
        return mPlayerOpponentRight;
    }

}
