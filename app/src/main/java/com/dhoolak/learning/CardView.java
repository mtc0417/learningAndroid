package com.dhoolak.learning;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * Created by prakasht on 7/22/2015.
 */
public class CardView extends ImageView  implements Comparable<CardView>{

    public int getOriginalWidth() {
        return mWidth;
    }

    public int getOriginalHeight() {
        return mHeight;
    }

    private int mWidth;
    private int mHeight;
    public enum CardDisplayState {
        OPEN, CLOSE
    }
    public enum CardOrientation {
        VERTICAL, HORIZONTAL
    }

    public boolean isTrump()
    {
        return mCard.isTrump();
    }
    public int compareTo(CardView card)
    {
        return this.mCard.compareTo(card.mCard);
    }
    public boolean equals(CardView card)
    {
        return this.mCard.equals(card.mCard);
    }
    public boolean equals(Card card)
    {
        return this.mCard.equals(card);
    }
    public CardDisplayState getCardDisplayState() {
        return mCardDisplayState;
    }

    public CardOrientation getCardOrientation() {
        return mCardOrientation;
    }

    public void setCardDisplayState(CardDisplayState cardDisplayState) {
        this.mCardDisplayState = cardDisplayState;
    }

    public void setCardOrientation(CardOrientation cardOrientation) {
        this.mCardOrientation = cardOrientation;
    }

    private Card mCard;
    private CardDisplayState mCardDisplayState;
    private CardOrientation mCardOrientation;

    public CardView(Context context){
        super(context);
    }

    public CardView(Context context, Card card){
        super(context);
        mCard = card;
    }
    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void loadImage() // call externally after setting orientation and display state
    {
        Bitmap bm;
        if(mCardDisplayState == CardDisplayState.OPEN)
        {
            bm = getFrontBitmap();
        }
        else
        {
            if(mCardOrientation == CardOrientation.HORIZONTAL) {
                bm = getHorizontalBackBitmap();
            }
            else
            {
                bm = getVerticalBackBitmap();
            }
        }
        this.setImageBitmap(bm);
    }
    public Bitmap getFrontBitmap() {
        return getBitmap(mCard.getDrawableName());
    }

    public Drawable getAndroidDrawable(String pDrawableName){
        int resourceId = this.getContext().getResources().getIdentifier(pDrawableName, "drawable", BuildConfig.APPLICATION_ID);
        String name = this.getContext().getResources().getResourceName(resourceId);
        if(resourceId==0){
            return null;
        } else {
            Resources.Theme theme = null;
            return this.getContext().getResources().getDrawable(resourceId);
            //return Resources.getSystem().getDrawable(resourceId, theme);
        }
    }
    public Bitmap getBitmap(Drawable d) {
        Bitmap image = ((BitmapDrawable) d).getBitmap();
        mWidth = image.getWidth();
        mHeight = image.getHeight();
        //System.out.println("Image Height:" + image.getHeight() + " width:" + image.getWidth());
        return image;
    }
    public Bitmap getHorizontalBackBitmap() {
        return getBitmap("b1fh");
    }
    public Bitmap getVerticalBackBitmap() {
        return getBitmap("b1fv");
    }
    public Bitmap getBitmap(String drawableName)
    {
        Drawable d  = getAndroidDrawable(drawableName);
        Bitmap bMap = getBitmap(d);
        return bMap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getOriginalWidth();
        int height = getOriginalHeight();
        setMeasuredDimension(width, height);
        System.out.println("CardView:onMeasure: width:" + width + ", height:" + height);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //this.setImageAlpha(225);

                AlphaAnimation alpha = new AlphaAnimation(1.0F, 0.5F); // change values as you want
                alpha.setDuration(1); // Make animation instant
                alpha.setFillAfter(true); // Tell it to persist after the animation ends
                // And then on your imageview
                this.startAnimation(alpha);

                break;
            default:
                //this.setImageAlpha(255);
                alpha = new AlphaAnimation(0.5F, 1.0F); // change values as you want
                alpha.setDuration(1); // Make animation instant
                alpha.setFillAfter(true); // Tell it to persist after the animation ends
                // And then on your imageview
                this.startAnimation(alpha);

                break;
        }
        return true;
    }
}
