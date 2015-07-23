package com.dhoolak.learning;

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

        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottom);
        ImageView image = new ImageView(this);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.b2fv);
        image.setImageBitmap(bMap);
        bottomLayout.addView(image);
        //l_layout.setOrientation(RelativeLayout.VERTICAL);

        LinearLayout leftLayout = (LinearLayout) findViewById(R.id.left);
        ImageView image2 = new ImageView(this);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.b2fh);
        image2.setImageBitmap(bMap);
        leftLayout.addView(image2);
        //leftLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView image3 = new ImageView(this);
        //bMap = BitmapFactory.decodeResource(getResources(), R.drawable.b2fh);
        image3.setImageBitmap(bMap);
        leftLayout.addView(image3);

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
