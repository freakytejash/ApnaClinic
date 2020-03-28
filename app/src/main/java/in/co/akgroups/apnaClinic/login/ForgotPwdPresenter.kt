package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForgotPwdPresenter(dataSourceInterface: DataSourceInterface, view: ForgotPwdContract.View) :
    ForgotPwdContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var view: ForgotPwdContract.View
    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        this.view = view
        this.view.presenter = this
    }

    override fun validateEmail(email: String) {
        view.showProgressBar()
        compositeDisposable.add(
            mDataSourceInterface.validateEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgressBar()
                    if (it?.data != null) {
                        view.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                        view.dismissFragment()
                    }
                }, {
                    view.hideProgressBar()
                    view.dismissFragment()
                })
        )

    }

    override fun start() {

    }

    override fun stop() {

    }
}