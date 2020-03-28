package `in`.co.akgroups.apnaClinic.patient.medical_report.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class AttachmentViewHolder(val rootView: View): RecyclerView.ViewHolder(rootView){

    var imgAttachment: ImageView
    var tv_attachment: TextView
    var lyt_constraint: ConstraintLayout

    init {
        imgAttachment = rootView.findViewById(R.id.img_attachment)
        tv_attachment = rootView.findViewById(R.id.tv_attachment)
        lyt_constraint = rootView.findViewById(R.id.lyt_constraint)
    }

    fun bind(attachments: PatientMedicalReport.Attachments) {
        tv_attachment.text = attachments.name
        setImage(attachments.imageUrl!!,imgAttachment)
    }

    fun addClickListener(onClickListener: AttachmentAdapter.OnClickListener) {
        imgAttachment.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onClickListener.onClick(v!!)
            }
        })
    }

    private fun setImage(thumbnail: String, imageView: ImageView) {

        if (TextUtils.isEmpty(thumbnail)) {
            return
        }

        if (imageView.width == 0 || imageView.height == 0) {
            imageView.post {
                ImageDownloader
                    .getImageDownloader()
                    .setImageByPath(
                        imageView.context,
                        thumbnail,
                        imageView,
                        R.drawable.default_user
                    )
            }
        } else {
            ImageDownloader
                .getImageDownloader()
                .setImageByPath(
                    imageView.context,
                    thumbnail,
                    imageView,
                    R.drawable.default_user
                )
        }
    }

    fun showZoomView(attachment: PatientMedicalReport.Attachments){

    }
}