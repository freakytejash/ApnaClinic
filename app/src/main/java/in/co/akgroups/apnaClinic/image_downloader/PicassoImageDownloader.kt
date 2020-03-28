//package `in`.co.akgroups.apnaClinic.image_downloader
//
//import android.content.Context
//import android.support.annotation.VisibleForTesting
//import android.support.v4.content.ContextCompat
//import android.widget.ImageView
//import com.squareup.picasso.Picasso
//import com.squareup.picasso.Transformation
//
//class PicassoImageDownloader : ImageDownloadInterface {
//
//    companion object {
//        private var INSTANCE: ImageDownloadInterface? = null
//
//        @JvmStatic
//        fun getInstance(): ImageDownloadInterface {
//            if (INSTANCE == null) {
//                synchronized(PicassoImageDownloader::javaClass) {
//                    INSTANCE = PicassoImageDownloader()
//                }
//            }
//            return INSTANCE!!
//        }
//
//        @VisibleForTesting
//        fun clearInstance() {
//            INSTANCE = null
//        }
//    }
//
//
//    override fun setImageByPath(appContext: Context, imgUrl: String, imageView: ImageView) {
//        val picasso = Picasso.get()
//        picasso.setIndicatorsEnabled(false)
//
//        picasso.load(imgUrl)
//            .resize(imageView.width, imageView.height)
//            .into(imageView)
//    }
//
//
//    override fun setImageWithTransformation(appContext: Context, imgUrl: String, imageView: ImageView, placeholder : Int, transformation: Transformation) {
//        val picasso = Picasso.get()
//        picasso.setIndicatorsEnabled(false)
//
//        picasso.load(imgUrl)
//            .resize(imageView.width, imageView.height)
//            .placeholder(ContextCompat.getDrawable(appContext, placeholder)!!)
//            .transform(transformation)
//            .into(imageView)
//    }
//
//    override fun setImageByPath(appContext: Context, imgurl: String, imageView: ImageView, placeholder: Int) {
//        val picasso = Picasso.get()
//        picasso.setIndicatorsEnabled(false)
//        picasso.load(imgurl)
//            .resize(imageView.width,imageView.height)
//            .placeholder(ContextCompat.getDrawable(appContext,placeholder)!!)
//            .into(imageView)
//    }
//}