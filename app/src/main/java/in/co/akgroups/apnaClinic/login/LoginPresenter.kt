package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException

class LoginPresenter(val dataSourceInterface: DataSourceInterface, loginView: LoginContract.View) :
    LoginContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var mLoginView: LoginContract.View

    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        mLoginView = loginView
        mLoginView.presenter = this
    }

    override fun checkIfUserAlreadyLoggedIn() {

    }

    override fun loginUser(loginId: String, password: String, type: Int) {
        mLoginView.showProgressBar()
        compositeDisposable.add(
            dataSourceInterface.loginUser(loginId, password, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
//                    if(it.success){
//                        if(it.isVerified && it.isActive){
//                            mLoginView.showToast("Your credential is matched", View.GONE, ToastPosition.BOTTOM)
//                            if(it.loginType == Const.TYPE_DOCTOR){
//                                Utils.isDoctor = true
//                                mLoginView.openDoctorPanel(it)
//                            } else {
//                                mLoginView.openOtpPage(it)
//                            }
//                        } else {
//                            mLoginView.openOtpPage(it)
//                        }
//                    }
                    if(it.success && it.isVerified && it.isActive){
                        if(it.isVerified && it.isActive){
                            mLoginView.showToast("Your credential is matched", View.GONE, ToastPosition.BOTTOM)
                            mLoginView.openOtpPage(it)
                        }else {
                            mLoginView.showToast("Account not verified,contact admin", View.GONE, ToastPosition.BOTTOM)
                        }
                    }
                    else {
                        mLoginView.showToast("Your credential is not matched", View.GONE, ToastPosition.BOTTOM)
                    }
                    mLoginView.hideProgressBar()
                }, {
                    mLoginView.hideProgressBar()
                    if (it is UnknownHostException) {
                        mLoginView.showError("Please check your internet connection")
                    } else if (it is HttpException) {
                        if (it.code() == 409) {
                            mLoginView.showError("Error in login, try again!")
                        }
                    }
                })
        )
    }

    override fun start() {
        checkIfUserAlreadyLoggedIn()
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}