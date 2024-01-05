package com.example.latalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.latalarm.AlarmList.AlarmListFragment
import com.example.latalarm.DiaryDataHolder.Dataholder
import com.example.latalarm.Service.AlarmService
import com.example.latalarm.databinding.ActivityKuisionerBinding
import com.example.latalarm.databinding.FragmentCreateBinding

class Kuisioner : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuisioner)


        val bBaik = findViewById<Button>(R.id.btnBaik)
        val bBuruk = findViewById<Button>(R.id.btnBuruk)



        bBaik.setOnClickListener {
            Dataholder.kondisi = 1
            Dataholder.tanggal =
                Dataholder.getCurrentDate() // Perbarui tanggal ketika kondisi ditetapkan
            navDash()
        }

        bBuruk.setOnClickListener {
            Dataholder.kondisi = 2
            Dataholder.tanggal =
                Dataholder.getCurrentDate() // Perbarui tanggal ketika kondisi ditetapkan
            navDash()
        }
        // Di tempat di aplikasi Anda di mana Anda menentukan obat apa yang dikonsumsi
        Dataholder.obat = listOf("Insulin", "Metformil", "Pioglitazone", "Forxiga" )

    }

    private fun navDash() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
        finish()
    }
}