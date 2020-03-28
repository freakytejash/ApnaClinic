package `in`.co.akgroups.apnaClinic.doctor.schedule.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.base.BaseViewHolder
import `in`.co.akgroups.apnaClinic.doctor.schedule.model.ScheduleData
import `in`.co.akgroups.apnaClinic.doctor.schedule.viewholders.ScheduleViewHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ScheduleAdapter (val context: Context, val scheduleList : ArrayList<ScheduleData>): RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_schedule, parent, false)
        return ScheduleViewHolder(itemView.context, itemView)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, p1: Int) {
        if(holder is ScheduleViewHolder){
            holder.bind(scheduleList[holder.adapterPosition])
        }
    }

}