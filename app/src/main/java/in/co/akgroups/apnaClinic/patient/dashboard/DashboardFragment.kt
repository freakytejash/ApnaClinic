package `in`.co.akgroups.apnaClinic.patient.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.utils.Utils

class DashboardFragment : Fragment(), DashboardContract.View {

    override lateinit var presenter: DashboardContract.Presenter
    companion object {
        fun newInstance(bundle: Bundle): DashboardFragment {
            var dashboardFragment = DashboardFragment()
            dashboardFragment.arguments = bundle
            return dashboardFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.doctor_dashboard, container, false)

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
