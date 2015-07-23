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
    private CardSuit mCardSuit;
    private CardNumber mCardNumber;
    private CardDisplayState mCardDisplayState;
    private CardOrientation mCardOrientation;

    public Card(Context context){
        super(context);
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

        switch
        return name;
    }
    public Bitmap getFrontBitmap() {
        Drawable d  = getAndroidDrawable("b1fh");
        Bitmap bMap = getBitmap(d);
        return bMap;
    }

    static public Drawable getAndroidDrawable(String pDrawableName){
        int resourceId= Resources.getSystem().getIdentifier(pDrawableName, "drawable", "android");
        if(resourceId==0){
            return null;
        } else {
            Resources.Theme theme = null;
            return Resources.getSystem().getDrawable(resourceId);
            //return Resources.getSystem().getDrawable(resourceId, theme);
        }
    }
    static public Bitmap getBitmap(Drawable d) {
        Bitmap image = ((BitmapDrawable) d).getBitmap();
        return image;
    }
    static public Bitmap getHorizontalBackBitmap() {
        Drawable d  = getAndroidDrawable("b1fh");
        Bitmap bMap = getBitmap(d);
        return bMap;
    }
    static public Bitmap getVerticalBackBitmap() {
        Drawable d  = getAndroidDrawable("b1vh");
        Bitmap bMap = getBitmap(d);
        return bMap;
    }
    private class CardToImageIdMap {
        public CardType cardType;
        public CardNumber cardNumber;
        public BitmapFactory.Options imageId;
    }

}
