package `in`.co.akgroups.apnaClinic.patient

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.base.BaseViewHolder
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.MenuItem
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MenuViewHolder(val context: Context, rootView: View) : BaseViewHolder(rootView) {

    var textViewName: TextView
    var imgPicture: ImageView

    init {
        textViewName = rootView.findViewById(R.id.tv_Name)
        imgPicture = rootView.findViewById(R.id.iv_picture)
    }

    fun bind(menuItem: MenuItem, position: Int) {
        textViewName.text = menuItem.name
        setImageToImaveView(imgPicture, menuItem.picture, R.drawable.default_user)
    }

    private fun setImageToImaveView(imageView: ImageView, url: String?, blankState: Int) {
        if (!TextUtils.isEmpty(url)) {
            if (imageView.width == 0 || imageView.height == 0) {
                imageView.post {
                    ImageDownloader
                        .getImageDownloader()
                        .setImageRoundedTransformation(
                            context,
                            url!!,
                            imageView,
                            blankState
                        )
                }
            } else {
                ImageDownloader
                    .getImageDownloader()
                    .setImageRoundedTransformation(
                        context,
                        url!!,
                        imageView,
                        blankState
                    )
            }

        } else {
            imageView.setImageResource(blankState)
        }
    }
}