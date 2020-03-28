package `in`.co.akgroups.apnaClinic.image_downloader

import android.content.Context
import android.widget.ImageView

interface ImageDownloadInterface {

    fun setImageByPath(appContext: Context, imgUrl: String, imageView: ImageView)

    fun setImageCircleTransformation(appContext: Context, imgUrl: String, imageView: ImageView, placeholder : Int)

    fun setImageRoundedTransformation(appContext: Context, imgUrl: String, imageView: ImageView, placeholder : Int)

    fun setImageRoundedTransformation(appContext: Context, thumbnail: String, high_res_url: String, imageView: ImageView, placeholder : Int)

    fun setImageByPath(appContext: Context, imgurl: String, imageView: ImageView, placeholder: Int)

    fun setImageByWithoutCentreCrop(appContext: Context, imgurl: String, imageView: ImageView, placeholder: Int)

}