package jeremypacabis.mlist.utils

import android.content.Context
import android.net.ConnectivityManager
import jeremypacabis.mlist.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jeremypacabis on Aug 18, 2019 @ 21:29.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist.utils <mlist>
 */
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun Context.getLastViewedTimestamp(): String {
    return SimpleDateFormat(
        getString(R.string.view_last_visited_timestamp_format),
        Locale.getDefault()
    ).format(Date())
}