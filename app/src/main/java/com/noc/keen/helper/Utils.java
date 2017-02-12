package com.noc.keen.helper;

/**
 * Created by kaiyilee on 2/11/17.
 */
        import android.app.Activity;
        import android.view.View;
        import android.view.WindowManager;

        import java.math.BigInteger;
        import java.security.SecureRandom;
        import java.text.NumberFormat;
        import java.util.Currency;

/**
 * Created by mmaietta on 9/9/15.
 */
public class Utils {

    public static String nextRandomId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public static String longToAmountString(Currency currency, long amt) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        if (currency != null)
            format.setCurrency(currency);

        double currencyAmount = (double) amt / Math.pow(10.0D, (double) format.getCurrency().getDefaultFractionDigits());

        return format.format(currencyAmount);
    }

    public static void setSystemUiVisibility(Activity act) {
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        act.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}