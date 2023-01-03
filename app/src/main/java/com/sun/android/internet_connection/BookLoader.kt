package com.sun.android.internet_connection

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, queryString: String) : AsyncTaskLoader<String>(context) {
    val mQueryString = queryString

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)

    }
}
