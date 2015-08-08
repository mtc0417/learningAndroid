package com.dhoolak.learning;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by prakasht on 8/7/2015.
 */
public class ControlBarView extends ViewGroup {
    private Button mStartButton;
    public ControlBarView(Context c)
    {
        super(c);
        initView(c);
    }
    public ControlBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    protected void initView(Context context){
        Button btn1 = new Button(context);
        btn1.setText("Start");
        this.addView(btn1);
        btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mStartButton = btn1;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Game.getInstance().onStart();
                        //MainLayout.getInstance().refresh();
                        System.out.println("Starting Game");
                    }
                });

                //PlayerView bottomLayout = this;//(PlayerView)findViewById(R.id.mainPlayerView);
                //bottomLayout.addCard(new Card(Card.CardSuit.HUKUM, Card.CardNumber.N5));
                //bottomLayout.addCard(new Card(Card.CardSuit.CHIDI, Card.CardNumber.N3));
                //bottomLayout.addCard(new Card(Card.CardSuit.EENT, Card.CardNumber.N10));
                //bottomLayout.addCard(new Card(Card.CardSuit.PAAN, Card.CardNumber.N1));

                // put code on click operation
                //alert();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int w = mStartButton.getWidth();
        int h = mStartButton.getHeight();
        System.out.println("Button:w:"+mStartButton.getWidth() + ", mw:" + mStartButton.getMeasuredWidth());
        if(w == 0)
        {
            w = 250; h = 150;
        }
        mStartButton.layout(r / 2 - w / 2, b / 2 - h / 2, r / 2 + w / 2, b / 2 + h / 2);
    }
    private void alert()
    {
        new AlertDialog.Builder(getContext())
                .setTitle("Hello")
                .setMessage("How r u?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
