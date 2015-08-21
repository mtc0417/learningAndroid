package com.dhoolak.learning;

import android.content.Context;

/**
 * Created by prakasht on 8/21/2015.
 */
public class PlayerViewMe extends PlayerViewH {
    public PlayerViewMe(Context c, PlayerType playerType)
    {
        super(c, playerType);
    }
    public CardView addCard(Card card) {
        CardView cardView = super.addCard(card);
        //cardView.onTouchEvent()
        return cardView;
    }
}
