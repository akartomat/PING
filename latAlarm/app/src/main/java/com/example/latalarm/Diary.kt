package com.example.latalarm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.latalarm.DiaryDataHolder.Dataholder

class Diary : AppCompatActivity() {

    private lateinit var dateTextView: TextView
    private lateinit var conditionTextView: TextView
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        dateTextView = findViewById(R.id.textViewDate)
        conditionTextView = findViewById(R.id.textViewCondition)
        backButton = findViewById(R.id.myImageView)

        dateTextView.text = Dataholder.tanggal
        conditionTextView.text = if (Dataholder.kondisi == 1) "Kondisi membaik" else "Kondisi memburuk"

        // Pengaturan OnClickListener untuk membuka StatusPage
        val listener = View.OnClickListener {
            val intent = Intent(this, StatusPage::class.java)
            startActivity(intent)
        }

        dateTextView.setOnClickListener(listener)
        conditionTextView.setOnClickListener(listener)

        backButton.setOnClickListener {
            finish()
        }
    }
}
