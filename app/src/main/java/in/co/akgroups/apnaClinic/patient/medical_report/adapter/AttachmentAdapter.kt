package `in`.co.akgroups.apnaClinic.patient.medical_report.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.patient.medical_report.DetailReportContract
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AttachmentAdapter(
    val context: Context,
    private val attachmentList: List<PatientMedicalReport.Attachments>,
    val presenter: DetailReportContract.Presenter,
    val mView: DetailReportContract.View
) : RecyclerView.Adapter<AttachmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_attachment, parent, false)
        return AttachmentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return attachmentList.size
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        holder.bind(attachmentList[position])
        holder.addClickListener(object : AttachmentAdapter.OnClickListener {
            override fun onClick(view: View) {
                when (view.id) {
                    R.id.img_attachment -> {
                        mView.showExpandedImage(attachmentList[position])
//                        holder.showZoomView(attachmentList[position])
                    }
                }
            }
        })
    }

    interface OnClickListener {
        fun onClick(view: View)
    }
}