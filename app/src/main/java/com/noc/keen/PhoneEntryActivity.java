package com.noc.keen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.widget.EditText;

public class PhoneEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_entry);

        EditText text = (EditText) findViewById(R.id.editTextId);
        //PhoneNumberUtils.formatNumber(text.getText().toString());
        text.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
}
