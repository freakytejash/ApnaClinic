package `in`.co.akgroups.apnaClinic.doctor.change_password

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by amitacharya on 3/2/20.
 */
class ChangePasswordCurPresenter(dataSourceInterface: DataSourceInterface, view: ChangePasswordCurContract.View) :
        ChangePasswordCurContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var view: ChangePasswordCurContract.View
    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        this.view = view
        this.view.presenter = this
    }

    override fun start() {

    }

    override fun stop() {

    }

    override fun changePasswordCur(userData: UserData, currentPassword: String, newPassword: String) {
        view.showProgressBar()
        compositeDisposable.add(
                mDataSourceInterface.changePasswordCur(userData, currentPassword, newPassword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
//                            println(it.data!!.message)
//                            println(it.data!!.success)
//                            println(it.status)
                            view.hideProgressBar()
                            if (it?.status == 200) {
                                view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                                view.dismissFragment()
                            } else if (it?.status == 401) {
                                view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                            }
                        }, {
                            view.hideProgressBar()
                        })
        )
    }
}