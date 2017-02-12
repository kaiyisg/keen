package com.noc.keen.helper;

import android.accounts.Account;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.util.CloverAuth;

/**
 * Created by kaiyilee on 2/12/17.
 */

public class AccountManager {

    public static final String TAG = "AccountManager";

    private static Account mAccount;
    private static CloverAuth.AuthResult mCloverAuth;
    private static String mToken;

    public static Account getAccount(Context ctx) {
        if (mAccount == null) {
            mAccount = CloverAccount.getAccount(ctx);
        }
        return mAccount;
    }

    public static String getToken() {
        if (mToken == null) {
            return "";
        }
        return mToken;
    }

    public static void getCloverAuth(final Context ctx, final Account account) {
        // This needs to be done on a background thread
        new AsyncTask<Void, Void, CloverAuth.AuthResult>() {
            @Override
            protected CloverAuth.AuthResult doInBackground(Void... params) {
                try {
                    return CloverAuth.authenticate(ctx, account);
                } catch (OperationCanceledException e) {
                    Log.e(TAG, "Authentication cancelled", e);
                } catch (Exception e) {
                    Log.e(TAG, "Error retrieving authentication", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(CloverAuth.AuthResult result) {
                mCloverAuth = result;

                // To get a valid auth result you need to have installed the app from the App Market. The Clover servers
                // only creates the token once installed the first time.
                if (mCloverAuth != null && mCloverAuth.authToken != null) {
                    mToken = mCloverAuth.authToken;
                } else {
                    mToken = null;
                }
            }
        }.execute();
    }
}
