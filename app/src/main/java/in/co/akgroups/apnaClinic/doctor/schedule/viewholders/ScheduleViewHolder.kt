package `in`.co.akgroups.apnaClinic.doctor.schedule.viewholders

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.base.BaseViewHolder
import `in`.co.akgroups.apnaClinic.doctor.schedule.model.ScheduleData
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ScheduleViewHolder(val context: Context, rootView: View) : BaseViewHolder(rootView), View.OnClickListener {

    var ivDelete: ImageView
    var tvDate: TextView
    var tvStartTime: TextView
    var tvEndTime: TextView
    var tvMode: TextView

    init {
        ivDelete = rootView.findViewById(R.id.iv_delete)
        tvDate = rootView.findViewById(R.id.tv_date)
        tvStartTime = rootView.findViewById(R.id.tv_start_time)
        tvEndTime = rootView.findViewById(R.id.tv_end_time)
        tvMode = rootView.findViewById(R.id.tv_mode)

        ivDelete.setOnClickListener(this)
    }

    fun bind(scheduleData: ScheduleData){
        tvDate.text = scheduleData.date
        tvMode.text = scheduleData.mode.name
        tvStartTime.text = scheduleData.startTime
        tvEndTime.text = scheduleData.endTime

    }
    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.iv_delete -> {

            }
        }
    }

}