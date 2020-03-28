package `in`.co.akgroups.apnaClinic.patient.medical_report.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.patient.medical_report.MedicalReportContract
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MedicalReportAdapter(
    val context: Context,
    private val patientReportList: MutableList<PatientMedicalReport>,
    val presenter: MedicalReportContract.Presenter,
    val mView: MedicalReportContract.View
) : RecyclerView.Adapter<MedicalReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalReportViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_medical_report, parent, false)
        return MedicalReportViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return patientReportList.size
    }

    override fun onBindViewHolder(holder: MedicalReportViewHolder, position: Int) {
        holder.bind(patientReportList[position])
        holder.addClickListener(object : OnClickListener{
            override fun onClick(view: View) {
                when (view!!.id) {
                    R.id.layout_foreground_view -> {
//                        presenter.fetchContentDetail(holder.contentId, holder.adapterPosition)
                        mView.openReportDetailPage(patientReportList[position])
                    }
                    R.id.img_delete -> {
                        delete(holder.adapterPosition)
                    }
                }
            }
        })
    }
    fun delete(position: Int) {
        var content = patientReportList.get(position)
        presenter.removeFromDB(content, patientReportList.size, this@MedicalReportAdapter, position)
        patientReportList.remove(content)
        this.notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(view: View)
    }
}