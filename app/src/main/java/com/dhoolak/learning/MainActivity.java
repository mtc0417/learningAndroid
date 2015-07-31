package com.dhoolak.learning;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //RelativeLayout bottomLayout = (RelativeLayout) findViewById(R.id.bottom);
        PlayerViewH bottomLayout = new PlayerViewH((RelativeLayout)findViewById(R.id.bottom), PlayerView.PlayerType.PLAYER_TYPE_ME);
        LinearLayout leftLayout = (LinearLayout) findViewById(R.id.left);
        ImageView image2 = new ImageView(this);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.b2fh);
        image2.setImageBitmap(bMap);
        leftLayout.addView(image2);
        //leftLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView image3 = new ImageView(this);
        //bMap = BitmapFactory.decodeResource(getResources(), R.drawable.b2fh);
        image3.setImageBitmap(bMap);
        leftLayout.addView(image3);

        CardView card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N1));
        bottomLayout.addView(card);
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N2));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50);

        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N3));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 2);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N4));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 3);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N5));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 4);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N6));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 5);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N7));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 6);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N8));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 7);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N9));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 8);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.N10));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 9);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.J));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 10);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.Q));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 11);
        card = new CardView(this, new Card(Card.CardSuit.CHIDI, Card.CardNumber.K));
        card.setCardDisplayState(CardView.CardDisplayState.OPEN);
        card.loadImage();
        bottomLayout.addView(card);
        card.setX(50 * 12);

        bottomLayout.addCard(new Card(Card.CardSuit.HUKUM, Card.CardNumber.N1));

        Button btn1 = new Button(this);
        btn1.setText("Kanha Natwarlal Tripathi");

        //l_layout.addView(btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put code on click operation
                //alert();
            }
        });
    }
    private void alert()
    {
        new AlertDialog.Builder(this)
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
