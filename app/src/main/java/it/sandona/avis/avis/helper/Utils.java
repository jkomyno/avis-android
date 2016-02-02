package it.sandona.avis.avis.helper;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Root on 27/01/2016.
 */
public class Utils {

    public static String protocol = "http://";
    public static String domain = "www.avissandona.it";
    public static String css = "/?app=1";
    public static String telSegreteria1 = "0421-53839";
    public static String telSegreteria2 = "373-8536578";
    public static String telPresidenza1 = "328-8832476";

    private Context mContext;

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
