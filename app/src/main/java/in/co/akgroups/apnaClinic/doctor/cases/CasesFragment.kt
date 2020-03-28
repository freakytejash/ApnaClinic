package `in`.co.akgroups.apnaClinic.doctor.cases

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.cases.adapters.CasesAdapter
import `in`.co.akgroups.apnaClinic.patient.cases.model.CaseData
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CasesFragment : Fragment(), CasesContract.View {

    override lateinit var presenter: CasesContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle): CasesFragment {
            val appointmentsFragment = CasesFragment()
            appointmentsFragment.arguments = bundle
            return appointmentsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.layout_doctor_cases, container, false)
        val recyclerView = rootView.findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val caseData = arrayOf(
            CaseData(
                "Case#1",
                "18 April 2018",
                "Video Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending",
                "open"
            ),
            CaseData(
                "Case#2",
                "19 April 2018",
                "Audio Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending",
                "closed"
            ),
            CaseData(
                "Case#3",
                "20 April 2018",
                "Text Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending",
                "closed"
            ),
            CaseData(
                "Case#4",
                "21 April 2018",
                "Audio Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending",
                "expired"
            ),
            CaseData(
                "Case#5",
                "22 April 2018",
                "Pending Consultation request with doctor batra is scheduled on 10th May 2018 at 10 am is pending",
                "open"
            )
        )

        val mAdapter = CasesAdapter(caseData, context!!)
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