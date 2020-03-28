package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.CustomButton
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
import kotlinx.android.synthetic.main.layout_doctor_qualification.*

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorQualificationFragment : Fragment(), DoctorQualificationContract.View, View.OnClickListener {

    override lateinit var presenter: DoctorQualificationContract.Presenter
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    lateinit var mUserData: UserData
    lateinit var etMedicalBoard: TextInputLayout
    lateinit var etRegnNo: TextInputLayout
    lateinit var etPostgraduate: TextInputLayout
    lateinit var etGraduate: TextInputLayout
    lateinit var btnEditProfile: CustomButton

    companion object {
        fun newInstance(bundle: Bundle): DoctorQualificationFragment {
            val fragment = DoctorQualificationFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.layout_doctor_qualification, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)!!
        }
        findViewById(rootView)
        btnEditProfile.setOnClickListener(this)
        return rootView
    }

    private fun findViewById(rootView: View) {

        etMedicalBoard = rootView.findViewById(R.id.et_medicalBoard)
        etRegnNo = rootView.findViewById(R.id.et_regnNo)
        etPostgraduate = rootView.findViewById(R.id.et_postgraduate)
        etGraduate = rootView.findViewById(R.id.et_graduate)
        btnEditProfile = rootView.findViewById(R.id.btn_edit_profile)
    }

    private fun enableEditOption() {
        etMedicalBoard.editText?.isFocusable = true
        etRegnNo.editText?.isFocusable = true
        etPostgraduate.editText?.isFocusable = true
        etGraduate.editText?.isFocusable = true

        etMedicalBoard.editText?.requestFocus()
        etRegnNo.editText?.requestFocus()
        etPostgraduate.editText?.requestFocus()
        etGraduate.editText?.requestFocus()
    }

    private fun disableEditOption() {
        etMedicalBoard.editText?.isFocusable = false
        etRegnNo.editText?.isFocusable = false
        etPostgraduate.editText?.isFocusable = false
        etGraduate.editText?.isFocusable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toast = Toast(context!!)
        presenter = DoctorQualificationPresenter(
                    Injection.provideDataRepository(activity!!),
                    this,
                    mUserData,
                    AppPreferenceHelper.getInstance(activity!!))

        presenter.fetchDoctorQualification(mUserData)
    }

    override fun showDoctorQualification(userData: UserData) {
        etMedicalBoard.editText?.setText(userData.medicalboard)
        etRegnNo.editText?.setText(userData.registrationno)
        etPostgraduate.editText?.setText(userData.postgraduate)
        etGraduate.editText?.setText(userData.graduate)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btn_edit_profile -> {
                updateDoctorQualification()
            }
        }
    }

    private fun updateDoctorQualification() {
        presenter.updateDoctorQualification(mUserData,
                etMedicalBoard.editText?.text.toString(),
                etRegnNo.editText?.text.toString(),
                etGraduate.editText?.text.toString(),
                etPostgraduate.editText?.text.toString())

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

    override fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
}