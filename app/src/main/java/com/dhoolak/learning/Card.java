package com.dhoolak.learning;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by prakasht on 7/22/2015.
 */
public class Card extends ImageView {

    public enum CardSuit { // Clubs, Spades, Hearts, Diamonds
        CHIDI, HUKUM, PAAN, EENT
    }
    public enum CardNumber {
        N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, J, Q, K
    }
    public enum CardNumberTest {
        N1 {
            public String toString() {
                return "this is one";
            }
        }
    }
    public enum CardDisplayState {
        OPEN, CLOSE
    }
    public enum CardOrientation {
        VERTICAL, HORIZONTAL
    }

    public CardSuit getCardSuit() {
        return mCardSuit;
    }

    public CardNumber getCardNumber() {
        return mCardNumber;
    }

    public CardDisplayState getCardDisplayState() {
        return mCardDisplayState;
    }

    public CardOrientation getCardOrientation() {
        return mCardOrientation;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.mCardSuit = cardSuit;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.mCardNumber = cardNumber;
    }

    public void setCardDisplayState(CardDisplayState cardDisplayState) {
        this.mCardDisplayState = cardDisplayState;
    }

    public void setCardOrientation(CardOrientation cardOrientation) {
        this.mCardOrientation = cardOrientation;
    }

    private CardSuit mCardSuit;
    private CardNumber mCardNumber;
    private CardDisplayState mCardDisplayState;
    private CardOrientation mCardOrientation;

    public Card(Context context){
        super(context);
    }

    public Card(Context context, CardSuit suit, CardNumber number){
        super(context);
        mCardSuit = suit;
        mCardNumber = number;
        loadImage();
    }

    public void loadImage()
    {
        this.setImageBitmap(getFrontBitmap());
    }
    public String getDrawableName()
    {
        String name = "";
        switch(mCardSuit)
        {
            case CHIDI: // Clubs
                name += 'c';
                break;
            case HUKUM: // Spades
                name += 's';
                break;
            case EENT: // Diamonds
                name += 'd';
                break;
            case PAAN: // Hearts
                name += 'h';
                break;
        }

        switch(mCardNumber)
        {
            case N1:
                name += '1';
                break;
            case N2:
                name += '2';
                break;
            case N3:
                name += '3';
                break;
            case N4:
                name += '4';
                break;
            case N5:
                name += '5';
                break;
            case N6:
                name += '6';
                break;
            case N7:
                name += '7';
                break;
            case N8:
                name += '8';
                break;
            case N9:
                name += '9';
                break;
            case N10:
                name += "10";
                break;
            case J:
                name += 'j';
                break;
            case Q:
                name += 'q';
                break;
            case K:
                name += 'k';
                break;
        }
        //return name + ".png";
        return name;
    }
    public Bitmap getFrontBitmap() {
        return getBitmap(getDrawableName());
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
    private class CardToImageIdMap {
        public CardType cardType;
        public CardNumber cardNumber;
        public BitmapFactory.Options imageId;
    }

}
