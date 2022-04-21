package com.xl.mycompose

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class SHNotifyService : Service() {
    override fun onCreate() {
        super.onCreate()
        startFG()
        thread {
            while (true) {
                Thread.sleep(1000 * 2)
                Log.e("TAG", "onCreate: ")
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun startFG() {
        Log.e("TAG", "启动服务")
        val channel = NotificationChannel(
            "com.xl.mycopmpose.CHANNEL_ID",
            "MyCompose",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
        val notification = NotificationCompat.Builder(this, "com.xl.mycopmpose.CHANNEL_ID")
            .setContentTitle("MyComposeTitle")
            .setContentText("正在使用蓝牙").build()

        notification.flags = Notification.FLAG_ONGOING_EVENT
        notification.flags = notification.flags or Notification.FLAG_NO_CLEAR
        notification.flags = notification.flags or Notification.FLAG_FOREGROUND_SERVICE
        startForeground(1, notification)
    }
}