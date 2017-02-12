package com.noc.keen.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class EndingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_ending);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(EndingActivity.this, SplashActivity.class);
                EndingActivity.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                EndingActivity.this.finish();
            }
        }, 2500);
    }



}
