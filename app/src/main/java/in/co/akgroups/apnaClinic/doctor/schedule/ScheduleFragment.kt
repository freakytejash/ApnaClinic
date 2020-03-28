package `in`.co.akgroups.apnaClinic.doctor.schedule

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.doctor.schedule.adapter.ScheduleAdapter
import `in`.co.akgroups.apnaClinic.doctor.schedule.model.ScheduleData
import `in`.co.akgroups.apnaClinic.utils.AppointmentMode
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_doctor_schedule.*

class ScheduleFragment : Fragment(), ScheduleContract.View, View.OnClickListener {

    override lateinit var presenter: ScheduleContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle): ScheduleFragment {
            val appointmentsFragment = ScheduleFragment()
            appointmentsFragment.arguments = bundle
            return appointmentsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.layout_doctor_schedule, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(activity)

        var scheduleList  = ArrayList<ScheduleData>()
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))
        scheduleList.add(ScheduleData("10 Nov 2018","06.45PM", "07.45 PM", AppointmentMode.AUDIO))

        recycler_view.adapter = ScheduleAdapter(context!!, scheduleList)
        recycler_view.itemAnimator = DefaultItemAnimator()

        btn_add_schedule.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_add_schedule -> {

            }
        }
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }
}