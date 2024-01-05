package com.example.latalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.content.Intent
import android.opengl.Visibility
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.latalarm.Application.App
import com.example.latalarm.Model.Alarm
import com.example.latalarm.R
import com.example.latalarm.ViewModel.AlarmViewModel
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Dashboard : AppCompatActivity() {
    private lateinit var alarmViewModel: AlarmViewModel
    private lateinit var nextAlarmtxt: TextView
    private lateinit var Obatxt: TextView
    private lateinit var dosPetxt: TextView
    private lateinit var tulisanKosong: TextView
    private lateinit var garis: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        /*val settingBtn = findViewById<ImageButton>(R.id.btnSetting)
        settingBtn.setOnClickListener(View.OnClickListener {
            showSetting()
        })*/

        val currentTime = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val dateFormatPattern = "EEEE, dd MMMM yyyy"
        val date = Date()
        val formattedDate = SimpleDateFormat(dateFormatPattern).format(date)

        /*val tanggal = findViewById<TextView>(R.id.textTanggal)
        tanggal.text = formattedDate*/

        val scheduleButton = findViewById<LinearLayout>(R.id.buttonSchedule)
        scheduleButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val diaryButton = findViewById<LinearLayout>(R.id.buttonDiary)
        diaryButton.setOnClickListener{
            val intent = Intent(this, Diary::class.java)
            startActivity(intent)
        }

        val logButton = findViewById<LinearLayout>(R.id.buttonLog)
        logButton.setOnClickListener{
            val intent = Intent(this, MedLog::class.java)
            startActivity(intent)
        }

        alarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        nextAlarmtxt = findViewById(R.id.textJam)
        Obatxt = findViewById(R.id.textMed)
        dosPetxt = findViewById(R.id.textDospet)
        tulisanKosong = findViewById(R.id.textALkosong)
        garis = findViewById(R.id.garisJam)



        alarmViewModel.getUpcomingAlarm().observe(this) { nextAlarm ->
            if (nextAlarm != null) {
                val formattedTime =
                    SimpleDateFormat("HH:mm", Locale.getDefault()).format(nextAlarm.timeInMillis)
                val obat = nextAlarm.obat
                val dosis = nextAlarm.dosis
                val petunjuk = nextAlarm.petunjuk
                nextAlarmtxt.visibility = View.VISIBLE
                nextAlarmtxt.text = "$formattedTime"
                Obatxt.text = "$obat"
                dosPetxt.text = "$dosis, $petunjuk"
                garis.visibility = View.VISIBLE
                tulisanKosong.visibility = View.INVISIBLE
            } else {
                nextAlarmtxt.visibility = View.INVISIBLE
                garis.visibility = View.INVISIBLE
                tulisanKosong.visibility = View.VISIBLE
            }
        }
    }

    /*private fun showSetting(){
        val builder = AlertDialog.Builder(this)
        val customLayout = layoutInflater.inflate(R.layout.activity_setting_popup, null)
        builder.setView(customLayout)

        val dialog = builder.create()

        val closeButton = customLayout.findViewById<ImageButton>(R.id.closeBtn)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        val logoutButton = customLayout.findViewById<TextView>(R.id.logout)
        logoutButton.setOnClickListener{
            val intent = Intent(this, PlaceholderActivity::class.java)
            startActivity(intent)
        }

        val openKonfHapus = customLayout.findViewById<TextView>(R.id.hapusAkun)
        openKonfHapus.setOnClickListener{
            dialog.dismiss()
            showKonfHapus()
        }

        dialog.show(
    }*/

    /*private fun showKonfHapus(){
        val builder = AlertDialog.Builder(this)
        val customLayout = layoutInflater.inflate(R.layout.activity_hapus_popup, null)
        builder.setView(customLayout)

        val dialog = builder.create()

        val openSetting = customLayout.findViewById<ImageButton>(R.id.backBtn)
        openSetting.setOnClickListener{
            dialog.dismiss()
            showSetting()
        }

        dialog.show()
    }*/

    /*private fun fetchAndDisplayAlarms() {
        // Lakukan query ke database dan tampilkan hasilnya
        val currentTime = System.currentTimeMillis()
        val query = "SELECT * FROM alarms WHERE jam > $currentHour OR (jam = $currentHour AND menit > $currentMinute) ORDER BY jam ASC, menit ASC"
        val cursor = database.rawQuery(query, null)

        // Memindahkan cursor ke posisi pertama
        if (cursor.moveToFirst()) {
            do {
                // Mendapatkan nilai kolom dari cursor
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val jam = cursor.getInt(cursor.getColumnIndex("jam"))
                val menit = cursor.getInt(cursor.getColumnIndex("menit"))
                val deskripsiObat = cursor.getString(cursor.getColumnIndex("deskripsi_obat"))

                // Lakukan sesuatu dengan nilai kolom, misalnya tambahkan ke list alarmList
                alarmList.add(Alarm(id, jam, menit, deskripsiObat))

            } while (cursor.moveToNext()) // Pindah ke baris berikutnya selama masih ada data
        }

        // Tutup cursor setelah selesai menggunakannya
        cursor.close()

        // Setelah mendapatkan data alarm, lakukan sesuatu, misalnya tampilkan di UI
        if (alarmList.isNotEmpty()) {
            // Lakukan sesuatu dengan alarmList, misalnya tampilkan di UI
            val alarm = alarmList[0] // Misalnya, ambil alarm pertama
            showSecondCard(alarm)
        } else {
            // Tampilkan pesan bahwa tidak ada alarm yang ditemukan
            showFirstCard()
        }
    }*/




    /*// Metode untuk Menampilkan Card Pertama
    private fun showFirstCard() {
        // Set visibility Card pertama
        cardPertama.visibility = View.VISIBLE

        // Set visibility Card kedua menjadi GONE
        cardKedua.visibility = View.GONE
    }

    // Metode untuk Menampilkan Card Kedua
    private fun showSecondCard(alarm: Alarm) {
        // Set visibility Card kedua
        cardKedua.visibility = View.VISIBLE

        // Set visibility Card pertama menjadi GONE
        cardPertama.visibility = View.GONE

        // Tampilkan waktu dan deskripsi obat di Card kedua
        val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", alarm.jam, alarm.menit)
        waktuTextView.text = formattedTime
        deskripsiObatTextView.text = alarm.deskripsiObat
    }*/



}