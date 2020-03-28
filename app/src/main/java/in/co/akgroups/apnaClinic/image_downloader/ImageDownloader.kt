package `in`.co.akgroups.apnaClinic.image_downloader

object ImageDownloader {

    @JvmStatic
    fun getImageDownloader(): ImageDownloadInterface {
        return GlideImageDownloader.getInstance()
    }
}