package com.noc.keen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.Intents;
import com.clover.sdk.v1.tender.Tender;
import com.clover.sdk.v1.tender.TenderConnector;
import com.clover.sdk.v3.payments.ServiceChargeAmount;
import com.noc.keen.helper.Utils;

import java.util.ArrayList;
import java.util.Currency;

public class CustomTenderActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tender);

        setResult(RESULT_CANCELED);

        // Necessary for Customer Facing user experiences
        setSystemUiVisibility();

        /**
         * @see Intents.ACTION_CUSTOMER_TENDER
         */
        final long amount = getIntent().getLongExtra(Intents.EXTRA_AMOUNT, 0);
        final Currency currency = (Currency) getIntent().getSerializableExtra(Intents.EXTRA_CURRENCY);
        final long taxAmount = getIntent().getLongExtra(Intents.EXTRA_TAX_AMOUNT, 0);
        final ArrayList taxableAmounts = getIntent().getParcelableArrayListExtra(Intents.EXTRA_TAXABLE_AMOUNTS);
        final ServiceChargeAmount serviceCharge = getIntent().getParcelableExtra(Intents.EXTRA_SERVICE_CHARGE_AMOUNT);

        final String orderId = getIntent().getStringExtra(Intents.EXTRA_ORDER_ID);
        final String employeeId = getIntent().getStringExtra(Intents.EXTRA_EMPLOYEE_ID);
        final String merchantId = getIntent().getStringExtra(Intents.EXTRA_MERCHANT_ID);

        final Tender tender = getIntent().getParcelableExtra(Intents.EXTRA_TENDER);

        // Customer Facing specific fields
        final long tipAmount = getIntent().getLongExtra(Intents.EXTRA_TIP_AMOUNT, 0);

        setupViews(amount, currency, orderId, merchantId);
    }

    public void setSystemUiVisibility() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void setupViews(final long amount, Currency currency, String orderId, String merchantId) {
        TextView amountText = (TextView) findViewById(R.id.text_amount);
        amountText.setText(Utils.longToAmountString(currency, amount));

        TextView orderIdText = (TextView) findViewById(R.id.text_orderid);
        orderIdText.setText(orderId);
        TextView merchantIdText = (TextView) findViewById(R.id.text_merchantId);
        merchantIdText.setText(merchantId);

        Button approveButton = (Button) findViewById(R.id.acceptButton);
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(Intents.EXTRA_AMOUNT, amount);
                data.putExtra(Intents.EXTRA_CLIENT_ID, Utils.nextRandomId());
                data.putExtra(Intents.EXTRA_NOTE, "Transaction Id: " + Utils.nextRandomId());

                setResult(RESULT_OK, data);
                finish();
            }
        });

        Button declineButton = (Button) findViewById(R.id.declineButton);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(Intents.EXTRA_DECLINE_REASON, "You pressed the decline button");

                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }


    private void createTenderType(final Context context) {
        new AsyncTask<Void, Void, Exception>() {

            private TenderConnector tenderConnector;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tenderConnector = new TenderConnector(context, CloverAccount.getAccount(context), null);
                tenderConnector.connect();
            }

            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    tenderConnector.checkAndCreateTender(getString(R.string.tender_name), getPackageName(), true, false);
                } catch (Exception exception) {
                    Log.e(TAG, exception.getMessage(), exception.getCause());
                    return exception;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                tenderConnector.disconnect();
                tenderConnector = null;
            }
        }.execute();
    }
}
