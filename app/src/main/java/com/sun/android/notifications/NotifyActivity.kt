package com.sun.android.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.sun.android.R
import com.sun.android.databinding.ActivityNotifyBinding


class NotifyActivity : AppCompatActivity() {
    private var mNotifyManager: NotificationManager? = null
    private var mReceiver = NotificationReceiver()
    private fun setNotificationButtonState(
        isNotifyEnabled: Boolean,
        isUpdateEnabled: Boolean,
        isCancelEnabled: Boolean
    ) {
        binding.buttonNotify.isEnabled = isNotifyEnabled
        binding.buttonUpdate.isEnabled = isUpdateEnabled
        binding.buttonCancel.isEnabled = isCancelEnabled
    }
    val binding by lazy { ActivityNotifyBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonNotify.setOnClickListener{
            sendNotification()
        }

        binding.buttonUpdate.setOnClickListener {
            updateNotification()
        }

        binding.buttonCancel.setOnClickListener {
            cancelNotification()
        }
        createNotificationChannel()
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
        registerReceiver(mReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION))
    }

    private fun sendNotification(){
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(this,NOTIFICATION_ID,updateIntent, PendingIntent.FLAG_ONE_SHOT)
        val notifyBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notifyBuilder.addAction(R.drawable.ic_update,getString(R.string.notified_button_update_notification), updatePendingIntent)
        mNotifyManager?.notify(NOTIFICATION_ID,notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)
    }

    private fun updateNotification(){
        val androidImage: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.mascot_1)
        val notifyBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notifyBuilder.setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(androidImage)
            .setBigContentTitle(getString(R.string.notified_update)))
        mNotifyManager?.notify(NOTIFICATION_ID,notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = false, isCancelEnabled = true)
    }

    private fun cancelNotification(){
        mNotifyManager?.cancel(NOTIFICATION_ID)
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)

    }

    private fun createNotificationChannel(){
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID,getString(R.string.mascot_notifition), NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notified_description)
            mNotifyManager?.createNotificationChannel(notificationChannel)
        }
    }

    private val notificationIntent = Intent(this,NotifyActivity::class.java)
    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationPendingIntent = PendingIntent.getActivity(this,NOTIFICATION_ID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notifyBuilder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle(getString(R.string.notified_title))
            .setContentText(getString(R.string.notified_msg))
            .setSmallIcon(R.drawable.ic_notification)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        return notifyBuilder
    }

    inner class NotificationReceiver: BroadcastReceiver() {
        override fun onReceive(content: Context, intent: Intent) {
            updateNotification()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }
    companion object{
        private const val PRIMARY_CHANNEL_ID: String = "pimary_notification_channel"
        private const val NOTIFICATION_ID: Int = 0
        private const val ACTION_UPDATE_NOTIFICATION: String = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION"
    }
}
