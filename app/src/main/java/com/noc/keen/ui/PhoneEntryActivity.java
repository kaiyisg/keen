package com.noc.keen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class PhoneEntryActivity extends AppCompatActivity {

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;
    ImageButton button0;
    ImageButton buttonstar;
    ImageButton buttonhex;
    ImageButton buttonclear;
    ImageButton buttonok;
    ImageButton buttoncancel;
    String mNumber = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_phone_entry);

        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);
        button3 = (ImageButton) findViewById(R.id.button3);
        button4 = (ImageButton) findViewById(R.id.button4);
        button5 = (ImageButton) findViewById(R.id.button5);
        button6 = (ImageButton) findViewById(R.id.button6);
        button7 = (ImageButton) findViewById(R.id.button7);
        button8 = (ImageButton) findViewById(R.id.button8);
        button9 = (ImageButton) findViewById(R.id.button9);
        button0 = (ImageButton) findViewById(R.id.buttonzero);
        buttonstar = (ImageButton) findViewById(R.id.buttonstar);
        buttonhex = (ImageButton) findViewById(R.id.buttonhex);
        buttonclear = (ImageButton) findViewById(R.id.clear);
        buttonok = (ImageButton) findViewById(R.id.ok);
        buttoncancel = (ImageButton) findViewById(R.id.cancel);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "1";
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "2";
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "3";
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "4";
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "5";
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "6";
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "7";
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "8";
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "9";
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "0";
            }
        });

        buttonstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "*";
            }
        });
        buttonhex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "#";
            }
        });

        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateNumberAndEnter();
            }
        });

        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });



        //EditText text = (EditText) findViewById(R.id.editTextId);
        //PhoneNumberUtils.formatNumber(text.getText().toString());
        //text.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void clearField() {
        mNumber = "";
    }

    private void validateNumberAndEnter() {
    }
}
