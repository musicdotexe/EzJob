package ezjob.ghn.com.ezjob.utils

import android.content.Context
import android.net.ConnectivityManager
import ezjob.ghn.com.ezjob.utils.NetworkMonitor

/**
 * Created by Dell on 5/15/2017.
 */

class LiveNetworkMonitor(override val context: Context) : NetworkMonitor {

    override val isConnected: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

}
