package com.xl.mycompose

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import kotlin.concurrent.thread

class ExpeditedWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            1, createNotification()
        )
    }

    override suspend fun doWork(): Result {

        thread {
            while (true) {
                Thread.sleep(1000 * 2)
                Log.e("TAG", "onCreate: ")
            }
        }


        return Result.success()
    }

    private fun createNotification(): Notification {
        val channel = NotificationChannel(
            "com.xl.mycopmpose.CHANNEL_ID",
            "MyCompose",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        (applicationContext.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        return NotificationCompat.Builder(applicationContext, "com.xl.mycopmpose.CHANNEL_ID")
            .setContentTitle("MyComposeTitle")
            .setContentText("正在使用蓝牙").build()
    }
}
