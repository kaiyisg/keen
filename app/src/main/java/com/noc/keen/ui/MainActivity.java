package com.noc.keen.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v1.BindingException;
import com.clover.sdk.v1.ClientException;
import com.clover.sdk.v1.ServiceException;
import com.clover.sdk.v1.merchant.Merchant;
import com.clover.sdk.v1.merchant.MerchantConnector;
import com.clover.sdk.v3.inventory.InventoryConnector;
import com.clover.sdk.v3.inventory.Item;
import com.noc.keen.R;
import com.noc.keen.helper.Utils;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Account mAccount;
    private InventoryConnector mInventoryConnector;
    private MerchantConnector mMerchantConnector;
    private TextView mMerchantTextView;
    private TextView mInventoryTextView;

    private RippleView mBuynowButton;
    private RippleView mMoreinfoButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSystemUiVisibility(this);
        setContentView(R.layout.activity_main);
        mContext = this;

        mBuynowButton = (RippleView) findViewById(R.id.buy_now_button);
        mMoreinfoButton = (RippleView) findViewById(R.id.more_info_button);

        mBuynowButton.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(mContext, CustomTenderActivity.class);
                startActivity(intent);
            }
        });

        mMoreinfoButton.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(mContext, ItemDetailActivity.class);
                startActivity(intent);
            }
        });

        /*mBuynowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomTenderActivity.class);
                startActivity(intent);
            }
        });*/

        mMoreinfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public boolean onTouch(View v, MotionEvent event) {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //mMerchantTextView = (TextView) findViewById(R.id.merchantName);
        //mInventoryTextView = (TextView) findViewById(R.id.inventoryItem);

        //Retrieve Clover account
        if (mAccount == null) {
            mAccount = CloverAccount.getAccount(this);

            if (mAccount == null) {
                return;
            }
        }

        //connectInventory();
        //connectMerchant();

        //new MerchantAsyncTask().execute();
        //new InventoryAsyncTask().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectInventory();
        disconnectMerchant();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void connectMerchant() {
        disconnectMerchant();

        if (mAccount != null) {
            mMerchantConnector = new MerchantConnector(this, mAccount, null);
            mMerchantConnector.connect();
        }
    }

    private void disconnectMerchant() {
        if (mMerchantConnector != null) {
            mMerchantConnector.disconnect();
            mMerchantConnector = null;
        }
    }

    private void connectInventory() {
        disconnectInventory();

        if (mAccount != null ) {
            mInventoryConnector = new InventoryConnector(this, mAccount, null);
            mInventoryConnector.connect();
        }
    }

    private void disconnectInventory() {
        if (mInventoryConnector != null) {
            mInventoryConnector.disconnect();
            mInventoryConnector = null;
        }
    }

    private class InventoryAsyncTask extends AsyncTask<Void, Void, Item> {

        @Override
        protected Item doInBackground(Void... voids) {
            try {
                return mInventoryConnector.getItems().get(0);
            } catch (RemoteException | ClientException | ServiceException | BindingException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected final void onPostExecute(Item item) {
            super.onPostExecute(item);
            if(item != null) {
                mInventoryTextView.setText("Item: " + item.getName());
            }
        }

    }

    private class MerchantAsyncTask extends AsyncTask<Void, Void, Merchant> {

        @Override
        protected Merchant doInBackground(Void... voids) {
            try {
                return mMerchantConnector.getMerchant();
            } catch (ServiceException | BindingException | ClientException | RemoteException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Merchant merchant) {
            super.onPostExecute(merchant);
            mMerchantTextView.setText("Merchant Name: " + merchant.getName());
        }
    }
}
