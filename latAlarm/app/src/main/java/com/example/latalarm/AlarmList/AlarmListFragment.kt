package com.example.latalarm.AlarmList

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latalarm.Dashboard
import com.example.latalarm.R
import com.example.latalarm.Service.AlarmService
import com.example.latalarm.ViewModel.AlarmViewModel
import com.example.latalarm.databinding.FragmentAlarmListBinding


class AlarmListFragment : Fragment() {

    private lateinit var binding: FragmentAlarmListBinding
    private lateinit var adapter: AdapterAlarm
    private lateinit var viewModel: AlarmViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)
        adapter = AdapterAlarm()

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })




        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)



        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(binding.floatingActionButton).navigate(R.id.action_alarmListFragment_to_createFragment22)
            //Tets Service
            /*val intent = Intent(context,AlarmService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context?.startForegroundService(intent)
            } else{
            context?.startService(intent)
            }*/
        }

        binding.backBtn.setOnClickListener{
            val intent = Intent(context, Dashboard::class.java)
            context?.startActivity(intent)
        }


    }
}