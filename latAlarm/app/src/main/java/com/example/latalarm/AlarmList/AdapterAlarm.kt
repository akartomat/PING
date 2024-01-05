package com.example.latalarm.AlarmList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latalarm.Model.Alarm
import com.example.latalarm.databinding.ItemalarmBinding

class AdapterAlarm : RecyclerView.Adapter<AdapterAlarm.AlarmViewHolder>(){

    private var mList = ArrayList<Alarm>()

    /*init{
        // Fake data
        for (i in 0..10){
            mList.add(Alarm(i.toLong(),
                12,
                30,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true))
        }
    }*/

    class AlarmViewHolder(var binding: ItemalarmBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(alarm: Alarm) {
            binding.txtTime.text = alarm.getTime()
            binding.txtWeek.text = alarm.getRepeat()
            binding.txtObat.text = alarm.getMed()
            binding.txtDosis.text = alarm.getDos()
            binding.txtPetunjuk.text = alarm.getGuide()
            /*binding.swStart.isChecked = alarm.start*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(ItemalarmBinding.inflate(LayoutInflater.from(parent.context),
            parent,
        false))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(it: List<Alarm>?) {
        mList = it as ArrayList<Alarm>
        notifyDataSetChanged()

    }
}