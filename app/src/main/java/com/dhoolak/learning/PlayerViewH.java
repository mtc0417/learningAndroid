package com.dhoolak.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by prakasht on 7/24/2015.
 */
public class PlayerViewH extends PlayerView {
    public PlayerViewH(ViewGroup viewGroup)
    {
        super(viewGroup);
    }
    public PlayerViewH(ViewGroup c, PlayerType pt)
    {
        super(c, pt);
    }
}