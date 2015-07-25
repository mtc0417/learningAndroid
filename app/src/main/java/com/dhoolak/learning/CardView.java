package com.dhoolak.learning;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by prakasht on 7/22/2015.
 */
public class CardView extends ImageView {

    public enum CardDisplayState {
        OPEN, CLOSE
    }
    public enum CardOrientation {
        VERTICAL, HORIZONTAL
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
        System.out.println("Image Height:" + image.getHeight() + " width:" + image.getWidth());
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
}
