package com.example.latalarm.BroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.latalarm.RingAlarmActivity
import com.example.latalarm.Service.AlarmService
import java.util.Calendar

class AlarmBroadCastReceiver : BroadcastReceiver() {

    companion object {
        val MONDAY = "MONDAY"
        val TUESDAY = "TUESDAY"
        val WEDNESDAY = "WEDNESDAY"
        val THURSDAY = "THURSDAY"
        val FRIDAY = "FRIDAY"
        val SATURDAY = "SATURDAY"
        val SUNDAY = "SUNDAY"
        val RECURRING = "RECURRING"
        val TITLE = "TITLE"
    }

    //p0 context p1 intent
    override fun onReceive(context: Context, intent: Intent?) {
        val intentService = Intent(context, AlarmService::class.java)
        if (!intent?.getBooleanExtra(RECURRING, false)!!) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context?.startForegroundService(intentService)
            } else {
                context?.startService(intentService)
            }
        } else {
            if (isToday(intent)){
                if (!intent?.getBooleanExtra(RECURRING, false)!!) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context?.startForegroundService(intentService)
                    } else {
                        context?.startService(intentService)
                    }
                }
            }
        }

    }

    private fun isToday(intent: Intent): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val today = calendar.get(Calendar.DAY_OF_WEEK)
        when (today) {
            Calendar.MONDAY -> {
                if (intent.getBooleanExtra(MONDAY, false))
                    return true
                return false
            }
            Calendar.TUESDAY -> {
                if (intent.getBooleanExtra(TUESDAY, false))
                    return true
                return false
            }
            Calendar.WEDNESDAY -> {
                if (intent.getBooleanExtra(WEDNESDAY, false))
                    return true
                return false
            }
            Calendar.THURSDAY -> {
                if (intent.getBooleanExtra(THURSDAY, false))
                    return true
                return false
            }
            Calendar.FRIDAY -> {
                if (intent.getBooleanExtra(FRIDAY, false))
                    return true
                return false
            }
            Calendar.SATURDAY -> {
                if (intent.getBooleanExtra(SATURDAY, false))
                    return true
                return false
            }
            Calendar.SUNDAY -> {
                if (intent.getBooleanExtra(SUNDAY, false))
                    return true
                return false
            }
        }
        return false
    }
}