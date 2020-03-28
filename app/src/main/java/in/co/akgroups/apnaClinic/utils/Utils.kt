package `in`.co.akgroups.apnaClinic.utils

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun isOnline(context: Context): Boolean {
        if (context != null) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
        return false;
    }

    var isDoctor = false
}