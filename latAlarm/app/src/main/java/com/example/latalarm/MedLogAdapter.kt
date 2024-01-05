package com.example.latalarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.latalarm.Model.Alarm

class MedLogAdapter(private val section: String) : RecyclerView.Adapter<MedLogAdapter.MedicineViewHolder>() {

    private var medicinesMap = mutableMapOf<String, List<Alarm>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_medlog_card_list, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicineList = medicinesMap.values.toList()[position]
        holder.bind(medicineList)
    }

    override fun getItemCount(): Int {
        return medicinesMap.size
    }

    fun setMedicines(medicines: List<Alarm>) {
        // Group medicines by name
        medicinesMap = medicines.groupBy { it.obat }.toMutableMap()
        notifyDataSetChanged()
    }

    inner class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.medicineNameTextView)
        private val detailsTextView: TextView = itemView.findViewById(R.id.medicineDetailsTextView)

        fun bind(medicineList: List<Alarm>) {
            val dosesInstructionsTimes = medicineList.joinToString("\n") {
                "${it.dosis}, ${it.petunjuk} [${formatTime(it.hour, it.minute)}]"
            }

            nameTextView.text = medicineList.firstOrNull()?.obat.orEmpty()
            detailsTextView.text = dosesInstructionsTimes
        }

        private fun formatTime(hour: Int, minute: Int): String {
            return String.format("%02d:%02d", hour, minute)
        }
    }
}