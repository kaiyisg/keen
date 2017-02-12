package com.noc.keen.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.Intents;
import com.clover.sdk.v1.tender.TenderConnector;
import com.clover.sdk.v3.base.Tender;
import com.clover.sdk.v3.order.Order;
import com.clover.sdk.v3.order.OrderConnector;
import com.clover.sdk.v3.payments.ServiceChargeAmount;
import com.noc.keen.R;
import com.noc.keen.helper.AccountManager;
import com.noc.keen.helper.Utils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CustomTenderActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    private OrderConnector orderConnector;
    private Account account;
    private TextView mTextView;

    public static final int OAUTH_REQUEST_CODE = 0;

    public static final String AUTH_CODE = "auth_code";
    public static final String MERCHANT_ID_KEY = "merchant_id";
    public static final String EMPLOYEE_ID_KEY = "employee_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_custom_tender);

        setResult(RESULT_CANCELED);

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

        mTextView = (TextView) findViewById(R.id.orderText);

        // Customer Facing specific fields
        final long tipAmount = getIntent().getLongExtra(Intents.EXTRA_TIP_AMOUNT, 0);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Starts intent to fetch OAuth 2.0 information
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivityForResult(intent, OAUTH_REQUEST_CODE);
            }
        });

        setupViews(amount, currency, orderId, merchantId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == OAUTH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Access data from the completed intent
            String token = data.getStringExtra(AUTH_CODE);
            String merchantId = data.getStringExtra(MERCHANT_ID_KEY);
            String employeeId = data.getStringExtra(EMPLOYEE_ID_KEY);
            Toast.makeText(CustomTenderActivity.this, token, Toast.LENGTH_LONG).show();

            Button btn = (Button)findViewById(R.id.button);
            btn.setVisibility(View.GONE);

            TextView txtView = (TextView)findViewById(R.id.textView);
            txtView.setText("Auth Token = " + token + "\nMerchant Id = " + merchantId +"\nEmployee Id = " + employeeId);
        }
        else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        orderConnector = new OrderConnector(this, account, null);

        RequestQueue queue = Volley.newRequestQueue(this);
        String baseurl = getResources().getString(R.string.base_url);
        String orderurl = getResources().getString(R.string.order_url);
        String url = baseurl + orderurl;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }})
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Authorization", "Bearer "+AccountManager.getToken());
                return map;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    protected void onPause() {
        if (orderConnector != null) {
            orderConnector.disconnect();
            orderConnector = null;
        }
        super.onPause();
    }

    public void setupViews(final long amount, Currency currency, String orderId, String merchantId) {
        /*TextView amountText = (TextView) findViewById(R.id.text_amount);
        amountText.setText(Utils.longToAmountString(currency, amount));

        TextView orderIdText = (TextView) findViewById(R.id.text_orderid);
        orderIdText.setText(orderId);
        TextView merchantIdText = (TextView) findViewById(R.id.text_merchantId);
        merchantIdText.setText(merchantId);*/

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
