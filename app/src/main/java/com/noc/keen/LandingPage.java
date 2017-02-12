package com.noc.keen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by JiaWern on 11/2/17.
 */

public class LandingPage extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Intent intent = getIntent();
        final Button buy_button = (Button) findViewById(R.id.buy_button);
        buy_button.setOnClickListener(this);

        final Button info_button = (Button) findViewById(R.id.info_button);
        info_button.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getTag().equals(getResources().getString(R.string.buynow_value))) {
            intent = new Intent(this, CustomTenderActivity.class);
        } else if (v.getTag().equals(getResources().getString(R.string.getinfo_value))) {
            intent = new Intent(this, CustomTenderActivity.class);
        }
        startActivity(intent);
    }
}
