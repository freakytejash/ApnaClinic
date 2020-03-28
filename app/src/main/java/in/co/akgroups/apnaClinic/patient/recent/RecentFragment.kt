package `in`.co.akgroups.apnaClinic.patient.recent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.dashboard.DashboardFragment
import `in`.co.akgroups.apnaClinic.patient.recent.adapters.RecentTabAdapter
import `in`.co.akgroups.apnaClinic.patient.recent.model.PatientRequest
import `in`.co.akgroups.apnaClinic.utils.Utils

class RecentFragment : Fragment(), RecentContract.View{

    override lateinit var presenter: RecentContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle): RecentFragment {
            var recentFragment = RecentFragment()
            recentFragment.arguments = bundle
            return recentFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_recent, container, false)

        val recyclerView = rootView.findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val patientData = arrayOf(
            PatientRequest(
                "Request#1 Video Consultation",
                "Request#",
                "18 April 2018",
                "Video Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending"
            ),
            PatientRequest(
                "Request#2 Audio Consultation",
                "Request#",
                "19 April 2018",
                "Audio Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending"
            ),
            PatientRequest(
                "Request#3 Text Consultation",
                "Request#",
                "20 April 2018",
                "Text Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending"
            ),
            PatientRequest(
                "Request#4 Audio Consultation",
                "Request#",
                "21 April 2018",
                "Audio Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending"
            ),
            PatientRequest(
                "Request#5 Pending Consultation",
                "Request#",
                "22 April 2018",
                "Pending Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending"
            )
        )


        val mAdapter = RecentTabAdapter(patientData)
        recyclerView.adapter = mAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        return rootView
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
