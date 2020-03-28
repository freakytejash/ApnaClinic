package `in`.co.akgroups.apnaClinic.patient.consult

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.utils.Utils

class ConsultFragment : Fragment(), ConsultContract.View {

    override lateinit var presenter: ConsultContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle): ConsultFragment {
            var consultFragment = ConsultFragment()
            consultFragment.arguments = bundle
            return consultFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.fragment_consult, container, false)
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
