package com.xl.mycompose

import android.app.Application
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest


class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        startForegroundService(Intent(this, SHNotifyService::class.java))

        val myWorkRequest: WorkRequest =
            OneTimeWorkRequest.Builder(ExpeditedWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)
    }
}