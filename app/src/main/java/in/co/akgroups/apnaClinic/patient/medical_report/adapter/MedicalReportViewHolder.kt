package `in`.co.akgroups.apnaClinic.patient.medical_report.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MedicalReportViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {

    var tvDate: TextView
    var tvReportName: TextView
    var tvLabName: TextView
    var imgDelete: ImageView
    var forgroundView: ConstraintLayout

    init {
        tvDate = rootView.findViewById(R.id.tv_date)
        tvReportName = rootView.findViewById(R.id.tv_report_name)
        tvLabName = rootView.findViewById(R.id.tv_lab_name)
        imgDelete = rootView.findViewById(R.id.img_delete)
        forgroundView = rootView.findViewById(R.id.layout_foreground_view)
    }

    fun bind(patientMedicalReport: PatientMedicalReport){
        tvDate.text = patientMedicalReport.report_date
        tvLabName.text = patientMedicalReport.lab_name
        tvReportName.text = patientMedicalReport.report_name
    }

    fun addClickListener(onClickListener: MedicalReportAdapter.OnClickListener) {
        imgDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onClickListener.onClick(v!!)
            }
        })
        forgroundView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onClickListener.onClick(v!!)
            }
        })
    }
}