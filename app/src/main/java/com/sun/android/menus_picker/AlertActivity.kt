package com.sun.android.menus_picker

import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.R
import com.sun.android.databinding.ActivityAlertBinding


class AlertActivity : AppCompatActivity() {
    val binding by lazy { ActivityAlertBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonAlert.setOnClickListener{
            var myAlertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            with(myAlertBuilder){
                setTitle(R.string.alert)
                setMessage(R.string.alertMessage)
                setPositiveButton(R.string.OK) { dialog, which ->
                    Toast.makeText(
                        applicationContext, R.string.okAction,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setNegativeButton(R.string.Cancel) { dialog, which ->
                    Toast.makeText(
                        applicationContext, R.string.cancelAction,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                show()
            }

        }
    }
}
