package com.sun.android.menus_picker

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.databinding.ActivityOrderBinding


class OrderActivity : AppCompatActivity() {
    val binding by lazy { ActivityOrderBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}
