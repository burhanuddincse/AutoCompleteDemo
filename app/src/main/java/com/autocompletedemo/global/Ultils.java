package com.autocompletedemo.global;

import android.content.Context;
import android.net.ConnectivityManager;

import org.json.JSONObject;

/**
 * Created by Android on 25-09-2015.
 */
public class Ultils
{
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static boolean checkInternetConnection(Context context)
    {
        ConnectivityManager con_manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
