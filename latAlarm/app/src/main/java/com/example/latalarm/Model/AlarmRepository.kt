package com.example.latalarm.Model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map


class AlarmRepository {
    private var alarmDao : AlarmDao
    var list : LiveData<List<Alarm>>

    constructor(application: Application){
        this.alarmDao = AlarmDatabase.getInstance(application).alarmDao()
        this.list = alarmDao.getAll()
    }

    suspend fun insert(alarm: Alarm){
        alarm.timeInMillis = alarm.calculateTimeInMillis()
        alarmDao.insert(alarm)
    }

    suspend fun delete(alarm: Alarm){
        alarmDao.insert(alarm)
    }

    suspend fun update(alarm: Alarm){
        alarmDao.update(alarm)
    }

    fun getUpcomingAlarm(currentHour: Int, currentMinute: Int): LiveData<Alarm?> {
        // Implementasi logika untuk mendapatkan alarm berikutnya berdasarkan waktu terdekat
        // Misalnya, Anda dapat menggunakan metode dari DAO yang memfilter alarm yang waktu terjadwal lebih besar dari waktu saat ini
        return alarmDao.getUpcomingAlarm(currentHour, currentMinute)
    }

    /*fun getAlarmsByCriteria(obat: String, dosis: String, petunjuk: String): LiveData<List<Alarm>> {
        return alarmDao.getAlarmsByCriteria(obat, dosis, petunjuk)
    }*/

}