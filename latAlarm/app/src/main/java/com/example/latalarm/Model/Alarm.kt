package com.example.latalarm.Model


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.latalarm.BroadCastReceiver.AlarmBroadCastReceiver
import java.util.Calendar
import java.util.Random


@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true) val uid : Long?,
    @ColumnInfo var hour : Int,
    @ColumnInfo var minute : Int,
    @ColumnInfo var obat : String,
    @ColumnInfo var dosis : String,
    @ColumnInfo var petunjuk : String,
    @ColumnInfo var mon : Boolean,
    @ColumnInfo var tue : Boolean,
    @ColumnInfo var wed : Boolean,
    @ColumnInfo var thu : Boolean,
    @ColumnInfo var fri : Boolean,
    @ColumnInfo var sat : Boolean,
    @ColumnInfo var sun : Boolean,
    @ColumnInfo var start : Boolean,
    var timeInMillis: Long = 0  //var buat nyimpen alarm
) {
    constructor(id: Long,hour: Int,minute: Int, obat: String, dosis: String, petunjuk: String) : this (
        id,
        hour,
        minute,
        obat,
        dosis,
        petunjuk,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false)

    constructor() : this (
        Random().nextLong(),
        0,
        0,
        "",
        "",
        ""
    )

    fun hasActiveAlarm(): Boolean {
        // Implement your logic to determine if the medicine has an active alarm
        // For example, check if hour and minute are set
        return hour != -1 && minute != -1
    }


    /*constructor() : this(
        null,
        0,
        0,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )*/

    fun calculateTimeInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.timeInMillis
    } // menghitung waktu dalam milisecond


    fun getTime(): String{
        return "$hour:$minute"
    }

    fun getRepeat(): String {
        var builder = StringBuilder()
        if(mon){
            builder.append("Mon")
        }
        if(tue){
            builder.append(",Tue")
        }
        if(wed){
            builder.append(",Wed")
        }
        if(thu){
            builder.append(",Thu")
        }
        if(fri){
            builder.append(",Fri")
        }
        if(sat){
            builder.append(",Sat")
        }
        if(sun){
            builder.append(",Sun")
        }
        return builder.toString()
    }

    fun getMed(): String{
        return "$obat"
    }

    fun getDos(): String{
        return "$dosis"
    }

    fun getGuide(): String{
        return "$petunjuk"
    }
    fun schedule (context: Context){
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context,AlarmBroadCastReceiver::class.java)
        intent.putExtra(AlarmBroadCastReceiver.MONDAY,mon)
        intent.putExtra(AlarmBroadCastReceiver.TUESDAY,tue)
        intent.putExtra(AlarmBroadCastReceiver.WEDNESDAY,wed)
        intent.putExtra(AlarmBroadCastReceiver.THURSDAY,thu)
        intent.putExtra(AlarmBroadCastReceiver.FRIDAY,fri)
        intent.putExtra(AlarmBroadCastReceiver.SATURDAY,sat)
        intent.putExtra(AlarmBroadCastReceiver.SUNDAY,sun)
        intent.putExtra(AlarmBroadCastReceiver.RECURRING,isLoop())
        


        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context,uid?.toInt()!!,intent,PendingIntent.FLAG_IMMUTABLE)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY,hour)
        calendar.set(Calendar.MINUTE,minute)
        calendar.set(Calendar.SECOND,0)
        calendar.set(Calendar.MILLISECOND,0)

        if(calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_WEEK,calendar.get(Calendar.DAY_OF_WEEK) + 1)
        }
        val oneDay : Long = 24 * 60 * 60 * 1000
        if (!isLoop()){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
        }
        else{
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,oneDay,pendingIntent)
                                   
        }
        start = true
    }

    private fun isLoop(): Boolean{
        return mon || tue || wed || fri || sat || sun
    }

}


