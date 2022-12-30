package com.sun.android.internet_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.sun.android.R
import com.sun.android.databinding.ActivityWhoWroteItBinding
import org.json.JSONException
import org.json.JSONObject


class WhoWroteItActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    val binding by lazy { ActivityWhoWroteItBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(supportLoaderManager.getLoader<String>(0) != null){
            supportLoaderManager.initLoader(0,null,this)
        }

        binding.buttonSearch.setOnClickListener {
            val queryString: String = binding.editTextBookInput.text.toString()
            val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if(inputManager != null){
                inputManager.hideSoftInputFromWindow(binding.editTextBookInput.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }
            val connMgr : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo: NetworkInfo? = null
            if (connMgr != null){
                networkInfo = connMgr.activeNetworkInfo
            }
            if(networkInfo != null && networkInfo.isConnected && queryString.length != 0){
                val queryBundle = Bundle()
                queryBundle?.putString(R.string.query.toString(),queryString)
                supportLoaderManager.restartLoader(0,queryBundle,this)
                binding.textAuthor.text = ""
                binding.textTitle.setText(R.string.loading)

            }else{
                if(queryString.isEmpty()){
                    binding.textAuthor.text = ""
                    binding.textTitle.setText(R.string.no_search_term)
                }else{
                    binding.textAuthor.text = ""
                    binding.textTitle.setText(R.string.no_network)
                }
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString = ""

        if (args != null) {
            queryString = args.getString(R.string.query.toString()).toString()
        }

        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray = jsonObject.getJSONArray(ITEMS)
            var i = 0
            var title: String? = null
            var authors: String? = null

            while (i < itemsArray.length() &&
                authors == null && title == null
            ) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject(VOLUMEINFO)
                try {
                    title = volumeInfo.getString(TITLE)
                    authors = volumeInfo.getString(AUTHORS)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                i++
            }
            if (title != null && authors != null) {
                binding.textTitle.text = title
                binding.textAuthor.text = authors
            } else {
                binding.textTitle.setText(R.string.no_results)
                binding.textAuthor.text = ""
            }
        } catch (e: Exception) {
            binding.textTitle.setText(R.string.no_results)
            binding.textAuthor.text = ""
            e.printStackTrace()
        }

    }

    override fun onLoaderReset(loader: Loader<String>) {

    }
    companion object{
        const val  ITEMS: String = "items"
        const val VOLUMEINFO: String = "volumeInfo"
        const val TITLE: String = "title"
        const val AUTHORS: String = "authors"
    }
}
