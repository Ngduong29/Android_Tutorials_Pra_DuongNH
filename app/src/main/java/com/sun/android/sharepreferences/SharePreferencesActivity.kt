package com.sun.android.sharepreferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sun.android.R
import com.sun.android.databinding.ActivitySharePreferencesBinding

class SharePreferencesActivity : AppCompatActivity() {
    val binding by lazy { ActivitySharePreferencesBinding.inflate(layoutInflater) }
    private var mCount: Int = 0
    private var mColor: Int = 0
    private val COUNT_KEY: String = "count"
    private val COLOR_KEY: String = "color"
    private val mPreferences by lazy {getSharedPreferences(sharePrefFile, MODE_PRIVATE)}
    private val sharePrefFile: String = "com.example.android.hellosharedprefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mColor = ContextCompat.getColor(this, R.color.default_background)

        mCount = mPreferences.getInt(COUNT_KEY,0)
        binding.textCount.text = String.format("%s",mCount)
        mColor = mPreferences.getInt(COLOR_KEY,mColor)
        binding.textCount.setBackgroundColor(mColor)

        binding.buttonCount.setOnClickListener {
            mCount++
            binding.textCount.text = String.format("%s",mCount)
        }
        binding.buttonReset.setOnClickListener {
            mCount = 0
            binding.textCount.text = String.format("%s",mCount)

            mColor = ContextCompat.getColor(this,R.color.default_background)
            binding.textCount.setBackgroundColor(mColor)

            val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
            preferencesEditor.clear()
            preferencesEditor.apply()
        }
    }

    fun changeBackGround(view: View){
        val color = (view.background as? ColorDrawable)?.color
        color?.let {
            binding.textCount.setBackgroundColor(it)
            mColor = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY,mCount)
        mColor.let { outState.putInt(COLOR_KEY, it) }
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
        preferencesEditor.putInt(COUNT_KEY,mCount)
        preferencesEditor.putInt(COLOR_KEY,mColor)
        preferencesEditor.apply()
    }
}
