package `in`.co.akgroups.apnaClinic.register

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.login.RegisterContract
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterPresenter(val dataSourceInterface: DataSourceInterface, registerView: RegisterContract.View) :
    RegisterContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var mRegisterView: RegisterContract.View

    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        mRegisterView = registerView
        mRegisterView.presenter = this
    }

    override fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        phone: Long,
        password: String,
        type: Int
    ) {
        mRegisterView.showProgressBar()
        compositeDisposable.add(
            dataSourceInterface.registerUser(firstName,lastName, email, phone, password, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        mRegisterView.hideProgressBar()
                        mRegisterView.showToast(it.data!!.message, View.GONE, ToastPosition.BOTTOM)
                        if(it.status != 409){
                            val userData = UserData()
                            userData.id = it.data!!.id
                            userData.first_name = firstName
                            userData.last_name = lastName
                            userData.loginType = type
                            userData.telephone = phone
                            userData.email = email
                            mRegisterView.openOtpPage(userData)
                        }
                    }
                }, {
                    mRegisterView.hideProgressBar()
                    mRegisterView.showSnackBarMessage("Error in register, try again!")
                })
        )
    }

    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}