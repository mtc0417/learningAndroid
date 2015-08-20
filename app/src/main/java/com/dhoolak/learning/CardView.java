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

    public enum DisplayState {
        OPEN, CLOSE
    }
    public enum Orientation {
        VERTICAL, HORIZONTAL
    }
    private int mWidth;
    private int mHeight;
    private boolean mSelected;
    private Card mCard;
    private DisplayState mCardDisplayState;
    private Orientation mCardOrientation;


    public Card getCard() {return mCard;}
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
    public DisplayState getCardDisplayState() {
        return mCardDisplayState;
    }
    public Orientation getCardOrientation() {
        return mCardOrientation;
    }
    public void setCardDisplayState(DisplayState cardDisplayState) {
        this.mCardDisplayState = cardDisplayState;
    }
    public void setCardOrientation(Orientation cardOrientation) {
        this.mCardOrientation = cardOrientation;
    }
    public void setStates(DisplayState cardDisplayState, Orientation cardOrientation)
    {
        mCardDisplayState = cardDisplayState;
        mCardOrientation = cardOrientation;
        loadImage();
    }

    public CardView(Context context){
        super(context);
        mSelected = false;
    }

    public CardView(Context context, Card card){
        this(context);
        mCard = card;
        this.setBackgroundColor(0xFF000000); // make background black so that making cards semitransparent does not show other cards.
    }
    public void loadImage() // call externally after setting orientation and display state
    {
        Bitmap bm;
        if(mCardDisplayState == DisplayState.OPEN)
        {
            bm = getFrontBitmap();
        }
        else
        {
            if(mCardOrientation == Orientation.HORIZONTAL) {
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
    @Override
    public String toString()
    {
        return mCard.toString();
    }
    public void setSelected(boolean selected)
    {
        if(selected) {
            this.setImageAlpha(200);
        }
        else
        {
            this.setImageAlpha(255);
        }
        mSelected = selected;
    }
    public boolean getSelected()
    {
        return mSelected;
    }
    public boolean onTouchEvent(MotionEvent ev) {
        //System.out.println(this.toString() + " " + ev);

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                this.setImageAlpha(220);
                /*
                AlphaAnimation alpha = new AlphaAnimation(1.0F, 0.5F); // change values as you want
                alpha.setDuration(1); // Make animation instant
                alpha.setFillAfter(true); // Tell it to persist after the animation ends
                // And then on your imageview
                this.startAnimation(alpha); */

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_HOVER_EXIT:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_UP:
                this.setImageAlpha(255);
                /*
                alpha = new AlphaAnimation(0.5F, 1.0F); // change values as you want
                alpha.setDuration(1); // Make animation instant
                alpha.setFillAfter(true); // Tell it to persist after the animation ends
                // And then on your imageview
                this.startAnimation(alpha); */

                break;
        }
        return true;
    }
    public int getOriginalWidth() {
        return mWidth;
    }
    public int getOriginalHeight() {
        return mHeight;
    }

}
