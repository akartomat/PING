package com.example.latalarm.Service


import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.latalarm.Application.App
import com.example.latalarm.R
import com.example.latalarm.RingAlarmActivity

class AlarmService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("AlarmService", "Service started")

        val intent = Intent(this,RingAlarmActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)
        val notification = NotificationCompat.Builder(this, App.ID)
            .setSmallIcon(R.drawable.ic_time)
            .setContentTitle("Waktunya minum obat")
            .setContentText("Tekan untuk melanjutkan")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}