package `in`.co.akgroups.apnaClinic.patient.medical_report

import `in`.co.akgroups.apnaClinic.App
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.patient.medical_report.adapter.AttachmentAdapter
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.detail_medical_report.*
import java.text.SimpleDateFormat
import java.util.*

class ReportDetailFragment : Fragment(), DetailReportContract.View, View.OnClickListener,
    DatePickerDialog.OnDateSetListener {


    override lateinit var presenter: DetailReportContract.Presenter
    private lateinit var mUserData: UserData

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    private lateinit var patientMedicalReport: PatientMedicalReport
    private var mCalender = Calendar.getInstance()

    override fun showProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun showError(message: String) {

    }

    override fun showSnackBarMessage(message: String) {

    }

    override fun showExpandedImage(attachment: PatientMedicalReport.Attachments) {
        setImageToImaveView(iv_expanded_view, attachment.imageUrl, R.drawable.default_user)
        lyt_expanded_image.visibility = View.VISIBLE
        lyt_reports.visibility = View.GONE
    }

    override fun hideExpandedImage() {
        lyt_expanded_image.visibility = View.GONE
        lyt_reports.visibility = View.VISIBLE
    }

    private fun setImageToImaveView(imageView: ImageView, url: String?, blankState: Int) {
        if (!TextUtils.isEmpty(url)) {
            if (imageView.width == 0 || imageView.height == 0) {
                imageView.post {
                    ImageDownloader
                        .getImageDownloader()
                        .setImageByPath(
                            imageView.context,
                            url!!,
                            imageView,
                            R.drawable.default_user
                        )
                }
            } else {
                ImageDownloader
                    .getImageDownloader()
                    .setImageByPath(
                        imageView.context,
                        url!!,
                        imageView,
                        R.drawable.default_user
                    )
            }

        } else {
            imageView.setImageResource(blankState)
        }
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

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    companion object {
        fun newInstance(bundle: Bundle, patientMedicalReport: PatientMedicalReport): ReportDetailFragment {
            val reportDetailFragment = ReportDetailFragment()
            reportDetailFragment.arguments = bundle
            reportDetailFragment.patientMedicalReport = patientMedicalReport
            return reportDetailFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.detail_medical_report, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }

        presenter = DetailReportPresenter(Injection.provideDataRepository(context!!), this)
        toast = Toast(context!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_report_name.setText(patientMedicalReport.report_name)
        et_lab_name.setText(patientMedicalReport.lab_name)
        et_date_of_report.setText(patientMedicalReport.report_date)
        setupClickListener()
        setupAttachmentAdapter()
    }

    private fun setupAttachmentAdapter(){
        rv_attachment.layoutManager = GridLayoutManager(App.applicationContext(), 3)
        rv_attachment.adapter = AttachmentAdapter(App.applicationContext(),patientMedicalReport.attachmentList!!,presenter, this)
    }

    private fun setupClickListener() {
        et_date_of_report.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_date_of_report -> {
                val datePickerDialog = DatePickerDialog(
                    context, this, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                    mCalender.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mCalender.set(Calendar.YEAR, year)
        mCalender.set(Calendar.MONTH, month)
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    private fun updateLabel() {
        val myFormat = "MMM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        et_date_of_report.setText(sdf.format(mCalender.time))
    }

    fun onBackPressed(): Boolean {
        if(lyt_expanded_image.visibility == View.VISIBLE){
            hideExpandedImage()
            return true
        }
        return false
    }
}