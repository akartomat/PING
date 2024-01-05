package com.example.latalarm.Application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.latalarm.Model.Alarm
import com.example.latalarm.Model.AlarmDao
import com.example.latalarm.Model.AlarmDatabase
import com.example.latalarm.ViewModel.AlarmViewModel

class App : Application() {

    companion object {
        val ID = "message_channel"
        lateinit var alarmDao: AlarmDao
            private set
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        createChannel()
        initDatabase()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            ID,
            "Alarm Service",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun initDatabase() {
        val appDatabase = Room.databaseBuilder(applicationContext, AlarmDatabase::class.java, "your_database_name.db")
            .build()

        alarmDao = appDatabase.alarmDao()
    }
}
