package `in`.co.akgroups.apnaClinic

interface BaseView<T> {

    var presenter: T

    fun isConnectedToInternet() : Boolean

}