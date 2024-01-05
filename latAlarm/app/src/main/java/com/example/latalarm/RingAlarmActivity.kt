package com.example.latalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.latalarm.databinding.ActivityRingAlarmBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RingAlarmActivity : AppCompatActivity()/*, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener*/ {

    private lateinit var binding : ActivityRingAlarmBinding

   /* var jam = 0
    var menit = 0
    var tanggal = 0
    var bulan = 0
    var tahun = 0
    lateinit var teksTanggal: TextView
    lateinit var teksWaktu: TextView
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingAlarmBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        //kode tambahan lain
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time // Waktu saat ini
        val currentDate = calendar.time // Tanggal saat ini


        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        val formattedTime = timeFormat.format(currentTime)
        val formattedDate = dateFormat.format(currentDate)

        // Misalkan ada dua TextView untuk menampilkan waktu dan tanggal
        val timeTextView: TextView = findViewById(R.id.tWaktu)
        val dateTextView: TextView = findViewById(R.id.tTanggal)

        timeTextView.text = "$formattedTime"
        dateTextView.text = "$formattedDate"

        binding.btnClose.setOnClickListener{
            val intent = Intent(this,Kuisioner::class.java)
            applicationContext.stopService(intent)
            startActivity(intent)
            finish()
        }
    }








    //kode tambahan dari gcr

    /*fun getSaatIni(){
        val kal: Calendar = Calendar.getInstance()
        tanggal = kal.get(Calendar.DAY_OF_MONTH)
        bulan = kal.get(Calendar.MONTH)
        tahun = kal.get(Calendar.YEAR)
        jam = kal.get(Calendar.HOUR_OF_DAY)
        menit = kal.get(Calendar.MINUTE)
    }*/



//    fun fSetTanggal(view: View){
//        getSaatIni()
//        DatePickerDialog(this, this,  tahun, bulan, tanggal).show()
//    }
//
//    fun fSetWaktu(view: View){
//        getSaatIni()
//        TimePickerDialog(this, this, jam, menit, true).show()
//    }
//
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        tanggal = dayOfMonth
//        bulan = month
//        tahun = year
//
//        teksTanggal = findViewById(R.id.tTanggal)
//        teksTanggal.text = "${tanggal} - ${bulan} - ${tahun}"
//    }
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        jam = hourOfDay
//        menit = minute
//
//        teksWaktu = findViewById(R.id.tWaktu)
//        teksWaktu.text = "${jam}:${menit}"
//    }

}