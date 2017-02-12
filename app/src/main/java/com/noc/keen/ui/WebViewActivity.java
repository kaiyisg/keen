package com.noc.keen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.noc.keen.R;
import com.noc.keen.helper.Config;


/**
 * Created by Zach J. on 8/22/14.
 */
public class WebViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_web_view);

        WebView webView;

        // The URL that will fetch the Access Token, Merchant ID, and Employee ID
        String url = "https://clover.com/oauth/authorize" +
                "?client_id=" + Config.APP_ID +
                "&redirect_uri=" + Config.APP_DOMAIN;

        // Creates the WebView
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Parses the fetched URL
                String authCodeFragment = "?code=";
                String merchantIdFragment = "&merchant_id=";
                String employeeIdFragment = "&employee_id=";

                int authCodeStart = url.indexOf(authCodeFragment);
                int merchantIdStart = url.indexOf(merchantIdFragment);
                int employeeIdStart = url.indexOf(employeeIdFragment);
                if (authCodeStart > -1) {
                    String accessToken = url.substring(authCodeStart + authCodeFragment.length(), merchantIdStart);
                    String merchantId = url.substring(merchantIdStart + merchantIdFragment.length(), employeeIdStart);
                    String employeeId = url.substring(employeeIdStart + employeeIdFragment.length(), url.length());

                    // Sends the info back to the MainActivity
                    Intent output = new Intent();
                    output.putExtra(CustomTenderActivity.AUTH_CODE, accessToken);
                    output.putExtra(CustomTenderActivity.MERCHANT_ID_KEY, merchantId);
                    output.putExtra(CustomTenderActivity.EMPLOYEE_ID_KEY, employeeId);
                    setResult(RESULT_OK, output);
                    finish();
                }
            }
        });
        // Loads the WebView
        webView.loadUrl(url);
    }
}
