package com.sun.android.implicit_intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Toast
import com.sun.android.databinding.ActivityMainImplicitBinding


class MainActivityImplicit : AppCompatActivity() {
    val binding by lazy { ActivityMainImplicitBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonOpenWeb.setOnClickListener {
            if (URLUtil.isValidUrl(binding.editTextURL.text.toString())) {
                val webpage: Uri = Uri.parse(binding.editTextURL.text.toString())
                val webSearch = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(webSearch)
            } else {
                Toast.makeText(this, "Wrong URL", Toast.LENGTH_LONG).show()
            }
        }
        binding.buttonOpenLocate.setOnClickListener{
            val myLocation = binding.editTextLocation.text.toString()
            val gmmIntentUri = Uri.parse("geo:0,0?q=$myLocation")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        binding.buttonShare.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.editTextShare.text.toString())
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }
}
