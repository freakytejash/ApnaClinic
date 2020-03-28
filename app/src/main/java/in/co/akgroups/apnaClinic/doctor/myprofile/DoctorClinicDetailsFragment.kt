package `in`.co.akgroups.apnaClinic.doctor.myprofile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorClinicDetailsFragment: Fragment(),DoctorClinicDetailsContract.View {
    override lateinit var presenter: DoctorClinicDetailsContract.Presenter

    override fun isConnectedToInternet(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    companion object {
        fun newInstance(bundle: Bundle): DoctorClinicDetailsFragment {
            val fragment = DoctorClinicDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}