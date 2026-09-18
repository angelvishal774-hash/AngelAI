package com.vishal.angelai

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class AngelForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "AngelForegroundServiceChannel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Angel AI चालू आहे")
            .setContentText("तुमची सेवा बॅकग्राउंडमध्ये कार्यरत आहे.")
            .setSmallIcon(android.R.drawable.ic_menu_compass) // तुम्ही तुमच्या ॲपचा आयकॉन लावू शकता
            .build()

        startForeground(NOTIFICATION_ID, notification)

        // येथे तुमचा मुख्य बॅकग्राउंड लॉजिक किंवा सेवा सुरू ठेवण्याचे कोडिंग लिहू शकता.

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Angel Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
}

