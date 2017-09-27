package ezjob.ghn.com.ezjob.utils

import android.content.Context

/**
 * Created by Dell on 5/15/2017.
 */

interface NetworkMonitor {
    val isConnected: Boolean

    val context: Context
}
