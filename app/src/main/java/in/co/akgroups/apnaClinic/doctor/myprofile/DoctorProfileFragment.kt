package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_user_profile.*
import kotlinx.android.synthetic.main.lyt_profile_scrolling.*

/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorProfileFragment: Fragment(), DoctorProfileContract.View, View.OnClickListener {

    private lateinit var mUserData: UserData
    private lateinit var etFirstName: TextInputLayout
    private lateinit var etLastName: TextInputLayout
    private lateinit var etEmail: TextInputLayout
    private lateinit var etYearsOfExp: TextInputLayout
    private lateinit var etSpeciality: TextInputLayout
    private lateinit var etLanguage: TextInputLayout
    private lateinit var etMedicalBoard: TextInputLayout
    private lateinit var etRegnNo: TextInputLayout
    private lateinit var etPostgraduate: TextInputLayout
    private lateinit var etGraduate: TextInputLayout
    private lateinit var btnEditProfile: Button
    private lateinit var expandedImage: ImageView
    companion object {
        fun newInstance(bundle: Bundle): DoctorProfileFragment {
            val fragment = DoctorProfileFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.layout_doctor_profile, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)!!
        }

        findViewById(rootView)
        btnEditProfile.setOnClickListener(this)
        return rootView
    }

    private fun findViewById(rootView: View){
        etFirstName = rootView.findViewById(R.id.et_first_name)
        etLastName = rootView.findViewById(R.id.et_last_name)
        etEmail = rootView.findViewById(R.id.et_email)
        etYearsOfExp = rootView.findViewById(R.id.et_experience)
        etSpeciality = rootView.findViewById(R.id.et_specility)
        etLanguage = rootView.findViewById(R.id.et_language)
        etMedicalBoard = rootView.findViewById(R.id.et_medicalBoard)
        etRegnNo = rootView.findViewById(R.id.et_regnNo)
        etPostgraduate = rootView.findViewById(R.id.et_postgraduate)
        etGraduate = rootView.findViewById(R.id.et_graduate)
        btnEditProfile = rootView.findViewById(R.id.btn_edit_profile)
        expandedImage = rootView.findViewById(R.id.expandedImage)
    }

    private fun enableEditOption(){
        etFirstName.editText?.isFocusable = true
        etLastName.editText?.isFocusable = true
        etEmail.editText?.isFocusable = true
        etYearsOfExp.editText?.isFocusable = true
        etSpeciality.editText?.isFocusable = true
        etLanguage.editText?.isFocusable = true
        etMedicalBoard.editText?.isFocusable = true
        etRegnNo.editText?.isFocusable = true
        etPostgraduate.editText?.isFocusable = true
        etGraduate.editText?.isFocusable = true

        etFirstName.editText?.requestFocus()
        etLastName.editText?.requestFocus()
        etEmail.editText?.requestFocus()
        etYearsOfExp.editText?.requestFocus()
        etSpeciality.editText?.requestFocus()
        etLanguage.editText?.requestFocus()
        etMedicalBoard.editText?.requestFocus()
        etRegnNo.editText?.requestFocus()
        etPostgraduate.editText?.requestFocus()
        etGraduate.editText?.requestFocus()
    }

    private fun disableEditOption(){
        etFirstName.editText?.isFocusable = false
        etLastName.editText?.isFocusable = false
        etEmail.editText?.isFocusable = false
        etYearsOfExp.editText?.isFocusable = false
        etSpeciality.editText?.isFocusable = false
        etLanguage.editText?.isFocusable = false
        etMedicalBoard.editText?.isFocusable = false
        etRegnNo.editText?.isFocusable = false
        etPostgraduate.editText?.isFocusable = false
        etGraduate.editText?.isFocusable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!::presenter.isInitialized){
            presenter = DoctorProfilePresenter(
                Injection.provideDataRepository(activity!!),
                this,
                mUserData
            )
            presenter.fetchDoctorProfile(mUserData)
            toolbar_layout.title = mUserData.doctorTitle
            toolbar_layout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar)
        }
    }

    override fun showProgressBar() {
        if(pb_loading != null){
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if(pb_loading != null){
            pb_loading.visibility = View.GONE
        }
    }

    override fun showDoctorProfile(userData: UserData) {
        etFirstName.editText?.setText(userData.first_name)
        etLastName.editText?.setText(userData.last_name)
        etEmail.editText?.setText(userData.email)
        val exp = userData.yearsofexperience.toString()
        etYearsOfExp.editText?.setText(exp)
        etSpeciality.editText?.setText(userData.speciality)
        etLanguage.editText?.setText(userData.language)
        etMedicalBoard.editText?.setText(userData.medicalboard)
        etRegnNo.editText?.setText(userData.registrationno)
        etPostgraduate.editText?.setText(userData.postgraduate)
        etGraduate.editText?.setText(userData.graduate)
        setImage(expandedImage, userData.picture,userData.picture)
    }

    private fun setImage(imageView: ImageView, thumbnailUrl: String, highResImageUrl: String) {

        if (TextUtils.isEmpty(highResImageUrl)) {
            ImageDownloader
                .getImageDownloader()
                .setImageRoundedTransformation(
                    context!!,
                    thumbnailUrl,
                    imageView,
                    R.drawable.ic_user
                )
        } else {
            ImageDownloader
                .getImageDownloader()
                .setImageRoundedTransformation(
                    context!!,
                    thumbnailUrl,
                    highResImageUrl,
                    imageView,
                    R.drawable.ic_user
                )
        }
    }
    override fun showSnackbarMessage(message: String) {
        val snackbar = Snackbar.make(constraint_layout, message, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }

    override lateinit var presenter: DoctorProfileContract.Presenter
    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_edit_profile -> {
                enableEditOption()
            }
        }
    }
}