package com.noc.keen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

/**
 * Created by JiaWern on 11/2/17.
 */

public class LandingPageActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.landing_page);

        mContext = this;

        final Button buy_button = (Button) findViewById(R.id.buy_button);
        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomTenderActivity.class);
                startActivity(intent);
            }
        });

        final Button info_button = (Button) findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
