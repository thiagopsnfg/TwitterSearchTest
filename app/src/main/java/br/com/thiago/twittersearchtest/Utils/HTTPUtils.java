package br.com.thiago.twittersearchtest.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Thiago on 20/11/2015.
 */
public class HTTPUtils {

    public static boolean isNetworkAvailable(Activity view){
        ConnectivityManager connMgr = (ConnectivityManager) view.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
