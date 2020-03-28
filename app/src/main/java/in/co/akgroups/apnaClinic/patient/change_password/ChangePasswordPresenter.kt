package `in`.co.akgroups.apnaClinic.patient.change_password

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChangePasswordPresenter(dataSourceInterface: DataSourceInterface, view: ChangePasswordContract.View) :
    ChangePasswordContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var view: ChangePasswordContract.View
    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        this.view = view
        this.view.presenter = this
    }

//    override fun validateEmail(email: String) {
//        view.showProgressBar()
//        compositeDisposable.add(
//            mDataSourceInterface.validateEmail(email)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.hideProgressBar()
//                    if (it?.data != null) {
//                        view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
//                        view.dismissFragment()
//                    }
//                }, {
//                    view.hideProgressBar()
//                    view.dismissFragment()
//                })
//        )
//
//    }

    override fun changePassword(userData: UserData, currentPassword: String, newPassword: String) {
        view.showProgressBar()
        compositeDisposable.add(
            mDataSourceInterface.changePassword(userData,currentPassword,newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgressBar()
                    if (it?.status == 401) {
                        view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                    } else if(it?.status == 201) {
                        view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                        view.dismissFragment()
                    }
                }, {
                    view.hideProgressBar()
                })
        )
    }

    override fun start() {

    }

    override fun stop() {

    }
}