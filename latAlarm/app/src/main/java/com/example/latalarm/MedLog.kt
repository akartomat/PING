package com.example.latalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latalarm.ViewModel.AlarmViewModel
import com.example.latalarm.databinding.ActivityMedLogBinding
import com.example.latalarm.databinding.FragmentCreateBinding

class MedLog : AppCompatActivity() {

    private lateinit var medicineViewModel: AlarmViewModel
    private lateinit var activeRecyclerView: RecyclerView
    private lateinit var inactiveRecyclerView: RecyclerView
    private lateinit var activeAdapter: MedLogAdapter
    private lateinit var inactiveAdapter: MedLogAdapter
    private lateinit var insulinStatus: MutableLiveData<Boolean>
    private lateinit var metforminStatus: MutableLiveData<Boolean>
    private lateinit var pioglitazoneStatus: MutableLiveData<Boolean>
    private lateinit var forxigaStatus: MutableLiveData<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_log)

        insulinStatus = MutableLiveData(false)
        metforminStatus = MutableLiveData(false)
        pioglitazoneStatus = MutableLiveData(false)
        forxigaStatus = MutableLiveData(false)

        medicineViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        activeRecyclerView = findViewById(R.id.activeRecyclerView)
        /*inactiveRecyclerView = findViewById(R.id.inactiveRecyclerView)*/

        // Setup RecyclerView for "Active" section
        activeAdapter = MedLogAdapter("Active")
        activeRecyclerView.adapter = activeAdapter
        activeRecyclerView.layoutManager = LinearLayoutManager(this)

        val backBtn : ImageView = findViewById<ImageView>(R.id.myImageView)
        backBtn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        // Observe changes in the medicine list
        medicineViewModel.list.observe(this, Observer { medicines ->
            val groupedMedicines = medicines.groupBy { it.obat }
            val activeMedicines = groupedMedicines.values
                .filter { medicinesGroup -> medicinesGroup.any { it.hasActiveAlarm() } }
                .flatten()

            val inactiveMedicines = groupedMedicines.values
                .filter { medicinesGroup -> medicinesGroup.all { !it.hasActiveAlarm() } }
                .flatten()

            // Update adapters with the latest medicine lists
            activeAdapter.setMedicines(activeMedicines)
            /*inactiveAdapter.setMedicines(inactiveMedicines)*/

            // Update status obat
            insulinStatus.value = groupedMedicines.containsKey("Insulin")
            metforminStatus.value = groupedMedicines.containsKey("Metformin")
            pioglitazoneStatus.value = groupedMedicines.containsKey("Pioglitazone")
            forxigaStatus.value = groupedMedicines.containsKey("Forxiga")

            val medicinesToCheck = listOf("Insulin", "Metformin", "Pioglitazone", "Forxiga")
            for (medicineName in medicinesToCheck) {
                val medicineExists = medicines.any { it.obat == medicineName }
                if (!medicineExists) {
                    // Show a special card for the missing medicine in "Inactive"
                    showMissingMedicineCard(medicineName)
                } else {
                    // Hide the special card if the medicine exists in the database
                    hideMissingMedicineCard(medicineName)
                }
            }

            // Check if any medicine in "Inactive" is already in the database
            for (inactiveMedicine in inactiveMedicines) {
                val medicineName = inactiveMedicine.obat
                val medicineExistsInDatabase = medicines.any { it.obat == medicineName }
                if (medicineExistsInDatabase) {
                    hideMissingMedicineCard(medicineName)
                }
            }


        })






    }

    private fun showMissingMedicineCard(medicineName: String) {
        when (medicineName) {
            "Insulin" -> findViewById<CardView>(R.id.insulin)?.visibility = View.VISIBLE
            "Metformin" -> findViewById<CardView>(R.id.metformin)?.visibility = View.VISIBLE
            "Pioglitazone" -> findViewById<CardView>(R.id.pioglitazone)?.visibility = View.VISIBLE
            "Forxiga" -> findViewById<CardView>(R.id.forxiga)?.visibility = View.VISIBLE
            // Add more cases for other medicines as needed
        }
    }

    private fun hideMissingMedicineCard(medicineName: String) {
        when (medicineName) {
            "Insulin" -> findViewById<CardView>(R.id.insulin)?.visibility = View.GONE
            "Metformin" -> findViewById<CardView>(R.id.metformin)?.visibility = View.GONE
            "Pioglitazone" -> findViewById<CardView>(R.id.pioglitazone)?.visibility = View.GONE
            "Forxiga" -> findViewById<CardView>(R.id.forxiga)?.visibility = View.GONE
            // Add more cases for other medicines as needed
        }
    }

}