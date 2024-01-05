package com.example.latalarm.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: Alarm)

    @Insert
    fun insertAll(alarmList: List<Alarm>)

    @Update
    suspend fun update(alarm: Alarm)

    @Delete
    suspend fun delete(alarm: Alarm)

    @Query("SELECT * FROM ALARM")
    fun getAll() : LiveData<List<Alarm>>
    /*fun getAlarmsByCriteria(obat: String, dosis: String, petunjuk: String): LiveData<List<Alarm>>*/

    @Query("SELECT * FROM ALARM WHERE hour > :currentHour OR (hour = :currentHour AND minute > :currentMinute) ORDER BY hour ASC, minute ASC")
    fun getUpcomingAlarm(currentHour: Int, currentMinute: Int, ): LiveData<Alarm?>

    // Tambahkan metode untuk memeriksa apakah data kosong
    @Query("SELECT COUNT(*) FROM ALARM")
    fun isEmpty(): Boolean

}