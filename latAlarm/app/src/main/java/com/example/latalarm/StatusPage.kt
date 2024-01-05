package com.example.latalarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.latalarm.DiaryDataHolder.Dataholder

class StatusPage : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var dateTextView: TextView
    private lateinit var conditionTextView: TextView
    private lateinit var drugTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statuspage)

        backButton = findViewById(R.id.myImageView)
        dateTextView = findViewById(R.id.textViewDate)
        conditionTextView = findViewById(R.id.textViewCondition)
        drugTextView = findViewById(R.id.obat)

        dateTextView.text = Dataholder.tanggal
        conditionTextView.text = when (Dataholder.kondisi) {
            1 -> "Kondisi membaik"
            2 -> "Kondisi memburuk"
            else -> "Kondisi tidak diketahui"
        }

        // Mengatur teks untuk obat
        drugTextView.text = Dataholder.obat.joinToString(separator = "\n")

        backButton.setOnClickListener {
            finish()
        }
    }
}
