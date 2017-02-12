package com.noc.keen.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.noc.keen.R;
import com.noc.keen.helper.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    TextView tv;
    String mNumber = "";
    Matcher mMatcher;


    Pattern pattern;

    String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_phone_entry);

        pattern = Pattern.compile(regex);

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
        tv = (TextView) findViewById(R.id.textView2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "1";
                tv.setText(mNumber);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "2";
                tv.setText(mNumber);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "3";
                tv.setText(mNumber);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "4";
                tv.setText(mNumber);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "5";
                tv.setText(mNumber);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "6";
                tv.setText(mNumber);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "7";
                tv.setText(mNumber);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "8";
                tv.setText(mNumber);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "9";
                tv.setText(mNumber);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "0";
                tv.setText(mNumber);
            }
        });

        buttonstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "*";
                tv.setText(mNumber);
            }
        });
        buttonhex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber += "#";
                tv.setText(mNumber);
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
                deleteItem();
                tv.setText(mNumber);
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
                tv.setText(mNumber);
            }
        });



        //EditText text = (EditText) findViewById(R.id.editTextId);
        //PhoneNumberUtils.formatNumber(text.getText().toString());
        //text.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public String method(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    private void deleteItem() {
        mNumber = method(mNumber);
    }

    private void clearField() {
        mNumber = "";
    }

    private void validateNumberAndEnter() {
        mMatcher = pattern.matcher(mNumber);
        if (mMatcher.matches()) {
            Intent intent = new Intent(this, EndingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.number_entry_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
