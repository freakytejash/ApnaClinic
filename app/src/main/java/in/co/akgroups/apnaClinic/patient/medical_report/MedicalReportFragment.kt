package `in`.co.akgroups.apnaClinic.patient.medical_report

import `in`.co.akgroups.apnaClinic.App
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.medical_report.adapter.MedicalReportAdapter
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import `in`.co.akgroups.apnaClinic.utils.replaceFragmentInFragmnentWithTag
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_medical_report.*
import kotlinx.android.synthetic.main.toolbar.*

class MedicalReportFragment : Fragment(), MedicalReportContract.View, View.OnClickListener {

    private val TAG = "MedicalReportFragment"
    lateinit var mUserData: UserData
    override lateinit var presenter: MedicalReportContract.Presenter
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    private lateinit var reportDetailFragment: ReportDetailFragment
    companion object {
        fun newInstance(bundle: Bundle): MedicalReportFragment {
            var medicalReportFragment = MedicalReportFragment()
            medicalReportFragment.arguments = bundle
            return medicalReportFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_medical_report, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }
        toast = Toast(context!!)
        return rootView
    }

    private fun setToolbar() {
        img_left.visibility = View.VISIBLE
        img_right.visibility = View.GONE
        txt_title.visibility = View.VISIBLE
        img_filter.visibility = View.GONE

        img_left.setImageResource(R.drawable.ic_back)
        txt_title.text = getString(R.string.medical_reports)

        DrawableCompat.setTint(img_left.drawable, ContextCompat.getColor(context!!, R.color.colorPrimary))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::presenter.isInitialized) {
            presenter = MedicalReportPresenter(
                Injection.provideDataRepository(context!!),
                this,
                mUserData
            )
        }
        setToolbar()
        setClickListener()
        presenter.start()
        rv_report.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    override fun showMedicalReport(list: List<PatientMedicalReport>) {
        val medicalReportAdapter = MedicalReportAdapter(
            App.applicationContext(),
            list.toMutableList(),
            presenter,
            this
        )
        rv_report.adapter = medicalReportAdapter
    }

    override fun showNoReportLayout() {
        rv_report.visibility = View.GONE
        tv_empty_report.visibility = View.VISIBLE
    }

    override fun hideNoReportLayout() {
        tv_empty_report.visibility = View.GONE
        rv_report.visibility = View.VISIBLE
    }

    override fun popFragment() {
        val fragment = fragmentManager?.findFragmentByTag(TAG)
        if(fragment != null){
            fragmentManager?.popBackStack()
        }
    }

    override fun openReportDetailPage(patientMedicalReport: PatientMedicalReport) {
        popFragment()
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        reportDetailFragment = ReportDetailFragment.newInstance(bundle, patientMedicalReport)
        replaceFragmentInFragmnentWithTag(reportDetailFragment, R.id.container_frame, TAG)
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

    override fun showProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    private fun setClickListener() {
        img_left.setOnClickListener(this)
        tv_empty_report.setOnClickListener(this)
    }

    override fun hideProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.GONE
        }
    }

    override fun showError(message: String) {

    }

    override fun showSnackBarMessage(message: String) {
    }

    override fun showRetry() {

    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_left -> {
                activity?.onBackPressed()
            }
            R.id.tv_empty_report -> {

            }
        }
    }

    fun onBackPressed(): Boolean{
        if(::reportDetailFragment.isInitialized){
            val isImageOpened = reportDetailFragment.onBackPressed()
            if(isImageOpened){
                return true
            }
        }
        return false
    }
}