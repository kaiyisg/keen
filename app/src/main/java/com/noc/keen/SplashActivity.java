package com.noc.keen;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_LENGTH = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                SplashActivity.this.finish();
            }
        }, SPLASH_LENGTH);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("splash", false);
    }
}
