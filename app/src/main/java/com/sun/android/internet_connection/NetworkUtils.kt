package com.sun.android.internet_connection

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkUtils {

    companion object{
        private val LOG_TAG: String = NetworkUtils::class.java.simpleName
        private val BOOK_BASE_URL: String = "https://www.googleapis.com/books/v1/volumes?"
        private val QUERY_PARAM: String = "q"
        private val MAX_RESULTS: String = "maxResults"
        private val PRINT_TYPE : String = "printType"
        fun getBookInfo (queryString: String) : String? {
            var urlConnection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            var bookJSONString: String? = null
            val builtURI: Uri = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build()
            try {
                val requestURL = URL(builtURI.toString())
                urlConnection = requestURL.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()
                val inputStream: InputStream = urlConnection.inputStream
                val reader = BufferedReader( InputStreamReader(inputStream))
                val builder: StringBuilder = StringBuilder()
                var line: String?
                while ((reader.readLine().also { line = it }) != null){
                    builder.append(line)
                    builder.append("\n")
                }
                if (builder.length == 0){
                    return null
                }
                bookJSONString = builder.toString()

            }catch (e: IOException){
                e.printStackTrace()
            }finally {
                urlConnection?.disconnect()
                if (reader != null){
                    try{
                        reader.close()
                    } catch (e: IOException){
                        e.printStackTrace()
                    }
                }

            }
            bookJSONString?.let { Log.d(LOG_TAG, it) }
            return bookJSONString
        }
    }
}
