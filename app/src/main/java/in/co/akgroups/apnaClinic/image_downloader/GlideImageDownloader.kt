package `in`.co.akgroups.apnaClinic.image_downloader

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideImageDownloader : ImageDownloadInterface {

    companion object {
        private var INSTANCE: ImageDownloadInterface? = null

        @JvmStatic
        fun getInstance(): ImageDownloadInterface {
            if (INSTANCE == null) {
                synchronized(GlideImageDownloader::javaClass) {
                    INSTANCE = GlideImageDownloader()
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun setImageByPath(appContext: Context, imgUrl: String, imageView: ImageView) {
        Glide.with(appContext)
            .load(imgUrl)
            .thumbnail(0.1f)
            .centerCrop()
            .into(imageView)
    }

    override fun setImageCircleTransformation(appContext: Context, imgUrl: String, imageView: ImageView, placeholder: Int) {
        Glide.with(appContext)
            .load(imgUrl)
            .thumbnail(0.1f)
            .error(placeholder)
            .placeholder(placeholder)
            .fallback(placeholder)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }

    override fun setImageByPath(appContext: Context, imgurl: String, imageView: ImageView, placeholder: Int) {
        Glide.with(appContext)
            .load(imgurl)
            .thumbnail(0.1f)
            .error(placeholder)
            .placeholder(placeholder)
            .fallback(placeholder)
            .centerCrop()
            .into(imageView)
    }

    override fun setImageRoundedTransformation(appContext: Context, imgUrl: String, imageView: ImageView, placeholder: Int) {
        Glide.with(appContext)
            .load(imgUrl)
            .thumbnail(0.1f)
            .error(placeholder)
            .placeholder(placeholder)
            .fallback(placeholder)
            .centerCrop()
            .into(imageView)
    }

    override fun setImageRoundedTransformation(appContext: Context, thumbnail: String, high_res_url: String, imageView: ImageView, placeholder: Int) {
        val thumbnailRequest = Glide
            .with(appContext)
            .load(thumbnail)

        Glide.with(appContext)
            .load(high_res_url)
            .thumbnail(thumbnailRequest)
            .error(placeholder)
            .placeholder(placeholder)
            .fallback(placeholder)
            .centerCrop()
            .into(imageView)
    }

    override fun setImageByWithoutCentreCrop(appContext: Context, imgurl: String, imageView: ImageView, placeholder: Int) {
        Glide.with(appContext)
            .load(imgurl)
            .thumbnail(0.1f)
            .error(placeholder)
            .placeholder(placeholder)
            .fallback(placeholder)
            .into(imageView)
    }

}