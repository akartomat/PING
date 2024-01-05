package com.example.latalarm.CreateAlarm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.latalarm.Model.Alarm
import com.example.latalarm.R
import com.example.latalarm.ViewModel.AlarmViewModel
import com.example.latalarm.databinding.FragmentCreateBinding


class CreateFragment : Fragment() {

   lateinit var binding: FragmentCreateBinding
   lateinit var viewModel: AlarmViewModel
   lateinit var spinnerMedicine: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var alarm = Alarm()
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        binding.toggleGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener
        { radioGroup, i ->
            when(i){
                binding.cb1.id -> alarm.mon 
                binding.cb2.id -> alarm.tue
                binding.cb3.id -> alarm.wed
                binding.cb4.id -> alarm.thu
                binding.cb5.id -> alarm.fri
                binding.cb6.id -> alarm.sat
                binding.cb7.id -> alarm.sun
            }
        })


        // pengaturan spinner

        /*spinnerMedicine = findViewById(R.id.editTextName)
        val medicineList = listOf("Insulin", "Metformil", "Pioglitazone", "Forxiga")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, medicineList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMedicine.adapter = adapter

        // Set salah satu data yang terpilih dari spinner ke posisi awal
        spinnerMedicine.setSelection(0, false)*/
        val medicineList = listOf("Insulin", "Metformin", "Pioglitazone", "Forxiga")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, medicineList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spObat.adapter = adapter
        binding.spObat.setSelection(0, false)


        binding.editTime.setOnClickListener {
            binding.fCardViewTime.visibility = View.VISIBLE
            /*timeCard.visibility = View.VISIBLE*/
        }
        //button back
        val backBtn = binding.myImageView
        backBtn.setOnClickListener {
            binding.fCardViewTime.visibility = View.INVISIBLE
            /*timeCard.visibility = View.INVISIBLE*/
        }


        /*binding.cb1.setOnCheckedChangeListener { compoundButton, b ->
            alarm.mon
        }
        binding.cb2.setOnCheckedChangeListener { compoundButton, b ->
            alarm.tue
        }
        binding.cb3.setOnCheckedChangeListener { compoundButton, b ->
            alarm.wed
        }
        binding.cb4.setOnCheckedChangeListener { compoundButton, b ->
            alarm.thu
        }
        binding.cb5.setOnCheckedChangeListener { compoundButton, b ->
            alarm.fri
        }
        binding.cb6.setOnCheckedChangeListener { compoundButton, b ->
            alarm.sat
        }
        binding.cb7.setOnCheckedChangeListener { compoundButton, b ->
            alarm.sun
        }
*/

        /*binding.ToggleGroup.addButtonCheckedListener({
            group, checkedId, isChecked ->
            when(checkedId){
                binding.btnM.id -> alarm.mon = isChecked
                binding.btnTue.id -> alarm.tue = isChecked
                binding.btnWed.id -> alarm.wed = isChecked
                binding.btnThu.id -> alarm.thu = isChecked
                binding.btnFri.id -> alarm.fri = isChecked
                binding.btnSat.id -> alarm.sat = isChecked
                binding.btnSun.id -> alarm.sun = isChecked
            }
        })*/

        binding.btnSimpan.setOnClickListener{
            alarm.hour = TimePickerUtil.getHour(binding.timePicker)
            alarm.minute = TimePickerUtil.getMinute(binding.timePicker)
            /*alarm.start = true*/
            alarm.schedule(requireContext())
            binding.fCardViewTime.visibility = View.INVISIBLE

        }


        binding.buttonSubmit.setOnClickListener{
            alarm.obat = binding.spObat.selectedItem.toString()
            alarm.dosis = binding.editTextDosage.text.toString()
            alarm.petunjuk = binding.editTextGuide.text.toString()

            Log.d("dosis: ",alarm.dosis)
            Log.d("petunjuk: ",alarm.petunjuk)

            viewModel.insert(alarm)
            Navigation.findNavController(binding.buttonSubmit).navigate(R.id.action_createFragment2_to_alarmListFragment)
        }
    }



}