package com.example.latalarm.Model

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
/*import org.junit.Assert.**/
import org.hamcrest.MatcherAssert.assertThat

import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlarmDatabaseTest : TestCase() {

    private lateinit var alarmDao: AlarmDao
    private lateinit var db : AlarmDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp(){
        var context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,AlarmDatabase::class.java).build()
        alarmDao = db.alarmDao()
    }

    @After
    public override fun tearDown(){
        db.close()
    }

    @Test
    fun testInsertAndGetAll() = runBlocking{
        val alarm = Alarm(1,10,10,"Insulin","2x1","sebelum makan")
        alarmDao.insert(alarm)
        var alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms,hasItem(alarm))
    }

    @Test
    fun testDeleteAndGetAll() = runBlocking {
        val alarm_1 = Alarm(1,10,10,"Insulin","2x1","sebelum makan")
        val alarm_2 = Alarm(2,20,20,"Insulin","2x1","sebelum makan")
        alarmDao.insert(alarm_1)
        alarmDao.insert(alarm_2)
        alarmDao.delete(alarm_1)
        val alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms,not(hasItem(alarm_1)))
    }

    @Test
    fun testUpdateAndGetAll() = runBlocking {
        val alarm_1 = Alarm(1,10,10,"Insulin","2x1","sebelum makan")
        val test = Alarm(1,20,20,"Insulin","2x1","sebelum makan")
        alarmDao.insert(alarm_1)
        alarm_1.hour = 20
        alarm_1.minute = 20
        alarmDao.update(alarm_1)
        var alarms = alarmDao.getAll().getOrAwaitValue()
        assertThat(alarms, hasItem(test))


    }
}