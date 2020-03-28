package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_doctor_experience.*

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorExperienceDetailsFragment : Fragment(), DoctorExperienceDetailsContract.View, View.OnClickListener {

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    lateinit var mUserData: UserData
    lateinit var btnEditProfile: Button
    lateinit var etYearsExp: TextInputLayout
    lateinit var etSpeciality: TextInputLayout
    lateinit var etLanguage: TextInputLayout
    override lateinit var presenter: DoctorExperienceDetailsContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle): DoctorExperienceDetailsFragment {
            val fragment = DoctorExperienceDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.layout_doctor_experience, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)!!
        }
        findViewById(rootView)
        btnEditProfile.setOnClickListener(this)
        return rootView
    }

    private fun findViewById(rootView: View) {
        etYearsExp = rootView.findViewById(R.id.et_yearsExp)
        etSpeciality = rootView.findViewById(R.id.et_specility)
        etLanguage = rootView.findViewById(R.id.et_language)
        btnEditProfile = rootView.findViewById(R.id.btn_edit_profile)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toast = Toast(context!!)
        presenter = DoctorExperienceDetailsPresenter(
                Injection.provideDataRepository(activity!!),
                this,
                mUserData,
                AppPreferenceHelper.getInstance(activity!!))

        presenter.fetchDoctorExperience(mUserData)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btn_edit_profile -> {
                updateDoctorExperience()
            }
        }
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    override fun showProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.GONE
        }
    }

    override fun showDoctorExperience(userData: UserData) {
        etYearsExp.editText?.setText(userData.yearsofexperience)
        etSpeciality.editText?.setText(userData.speciality)
        etLanguage.editText?.setText(userData.language)
    }

    private fun updateDoctorExperience() {
        presenter.updateDoctorExperience(mUserData,
                etYearsExp.editText?.text.toString(),
                etSpeciality.editText?.text.toString(),
                etLanguage.editText?.text.toString())

    }

    override fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition) {
        val toastView = layoutInflater.inflate(R.layout.toast_layout, null)
        tv_msg = toastView.findViewById(R.id.tv_mesg)
        iv_toast = toastView.findViewById(R.id.iv_toast)
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT
        tv_msg.text = message
        iv_toast.visibility = imageVisibility
        if (toastPosition == ToastPosition.CENTRE) {
            toast.setGravity(Gravity.CENTER, 0, 0)
        } else
            toast.setGravity(Gravity.CENTER, 0, 450)
        toast.show()
    }

    override fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}