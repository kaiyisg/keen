package com.noc.keen.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.noc.keen.R;
import com.noc.keen.helper.AccountManager;
import com.noc.keen.helper.Utils;

public class AdvertismentActivity extends AppCompatActivity {

    RelativeLayout mBackground;
    Context mContext;
    TextView mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_advertisment);
        mContext = this;

        mBackground = (RelativeLayout) findViewById(R.id.activity_advertisment);
        mToken = (TextView) findViewById(R.id.tokenText);

        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        /*String token = AccountManager.getToken();
        if (!TextUtils.isEmpty(token)) {
            mToken.setText(token);
        }*/
    }
}
