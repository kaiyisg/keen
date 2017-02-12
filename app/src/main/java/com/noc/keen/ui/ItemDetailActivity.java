package com.noc.keen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_item_detail);
    }
}
