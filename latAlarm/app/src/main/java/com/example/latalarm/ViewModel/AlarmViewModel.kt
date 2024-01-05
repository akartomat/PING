package com.example.latalarm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.latalarm.Model.Alarm
import com.example.latalarm.Model.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: AlarmRepository
    var list: LiveData<List<Alarm>>

    init {
        repository = AlarmRepository(application)
        list = repository.list
    }

    fun insert(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(alarm)
        }
    }

    fun delete(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(alarm)
        }
    }

    fun update(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(alarm)
        }
    }

    fun getUpcomingAlarm(): LiveData<Alarm?> {
        val currentTime = Calendar.getInstance()
        return repository.getUpcomingAlarm(
            currentHour = currentTime.get(Calendar.HOUR_OF_DAY),
            currentMinute = currentTime.get(Calendar.MINUTE)
        )
    }
}
