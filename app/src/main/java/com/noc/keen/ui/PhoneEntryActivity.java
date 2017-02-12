package com.noc.keen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.widget.EditText;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class PhoneEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_phone_entry);

        //EditText text = (EditText) findViewById(R.id.editTextId);
        //PhoneNumberUtils.formatNumber(text.getText().toString());
        //text.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
}
