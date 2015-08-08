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
    private ViewGroup mControlBar;
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
        mInstance = this;
        initView(context);
    }
    public MainLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInstance = this;
        initView(context);
    }
    protected void initView(Context context){
        mPlayerMe = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_ME);
        mPlayerMyPartner = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_MY_PARTNER);
        mPlayerOpponentLeft = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_OPPONENT_LEFT);
        mPlayerOpponentRight = new PlayerViewH(context, PlayerView.PlayerType.PLAYER_TYPE_OPPONENT_RIGHT);
        mControlBar = new ControlBarView(context);
        addView(mPlayerMe);
        addView(mPlayerMyPartner);
        addView(mPlayerOpponentLeft);
        addView(mPlayerOpponentRight);
        addView(mControlBar);

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
    public void changeColor()
    {
        mPlayerMe.setBackgroundColor(0xFF000000);
        mPlayerMyPartner.setBackgroundColor(0xFF000000);
        mPlayerOpponentRight.setBackgroundColor(0xFF0000FF);
        mPlayerOpponentLeft.setBackgroundColor(0xFF0000FF);
    }
    public void removeAllCards()
    {
        mPlayerOpponentLeft.removeAllCards();
        mPlayerMyPartner.removeAllCards();
        mPlayerMyPartner.removeAllCards();
        mPlayerMe.removeAllCards();
    }
    public void refresh()
    {
        mPlayerMe.invalidate();
        mPlayerMyPartner.invalidate();
        mPlayerOpponentLeft.invalidate();
        mPlayerOpponentRight.invalidate();
        this.invalidate();
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

        int w = r-l;
        int h = b - t;
        mControlBar.layout(l, t, l+w, t+h/10);
        h = 9*b/10;
        t = b/10;

        mPlayerMyPartner.layout(l, t, l+w, t+h/4);
        t += h/4;
        mPlayerOpponentLeft.layout(l, t, l+w/3, t+h/2);
        mPlayerOpponentRight.layout(l+2*w/3, t, r, t+h/2);
        t += h/2;
        mPlayerMe.layout(l, t, l+w, t+h/4);
 // remaining height
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
    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        int desiredWidth = screenWidth;
        int desiredHeight = screenHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
        mControlBar.measure(width, height/10);
        height = height*9/10;
        mPlayerMe.measure(width, height/4);
        mPlayerMyPartner.measure(width, height/4);
        mPlayerOpponentLeft.measure(width/3, height/2);
        mPlayerOpponentRight.measure(width/3, height/4);
        System.out.println("MainLayout:onMeasure: width:" + width + ", height:" + height);
    }
}
