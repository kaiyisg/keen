package com.noc.keen.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andexert.library.RippleView;
import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class ItemDetailActivity extends AppCompatActivity {

    private RippleView mBuynowButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_item_detail);
        mContext = this;

        mBuynowButton = (RippleView) findViewById(R.id.buy_now_button);

        mBuynowButton.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(mContext, CustomTenderActivity.class);
                startActivity(intent);
            }
        });

    }
}
