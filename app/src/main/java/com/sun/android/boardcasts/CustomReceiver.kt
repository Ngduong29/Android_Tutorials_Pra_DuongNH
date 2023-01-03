package com.sun.android.boardcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sun.android.BuildConfig
import com.sun.android.R

class CustomReceiver : BroadcastReceiver() {
    private val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID + (R.string.action_custom)
    override fun onReceive(context: Context, intent: Intent) {
      val intentAction: String = intent.action.toString()
        if(intentAction != null){
            var toastMessage = (R.string.unknown_toast)
            when (intentAction){
                Intent.ACTION_POWER_CONNECTED -> toastMessage = (R.string.power_connect)
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = (R.string.power_disconnect)
                ACTION_CUSTOM_BROADCAST -> toastMessage = (R.string.custom_boardcast)
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }


    }
}
