package com.example.latalarm.DiaryDataHolder

import java.text.SimpleDateFormat
import java.util.*

object Dataholder {
    var kondisi: Int = 0
    var tanggal: String = getCurrentDate()
    var obat: List<String> = listOf() // Menambahkan list untuk menyimpan obat

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
