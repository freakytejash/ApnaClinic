package `in`.co.akgroups.apnaClinic.register.otp

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by narendrapal on 15/01/2020
 */

class OtpPresenter(val dataSourceInterface: DataSourceInterface, view: OtpContract.View) : OtpContract.Presenter {

    var compositeDisposable = CompositeDisposable()
    var mDataSourceInterface: DataSourceInterface
    var mView: OtpContract.View


    init {
        mDataSourceInterface = dataSourceInterface
        mView = view
        mView.presenter = this
    }

    override fun submitOtp(userData: UserData, mobileOtp: Int, emailOtp: Int) {
        mView.showProgressBar()
        compositeDisposable.add(
            mDataSourceInterface.verifyOtp(userData,emailOtp,mobileOtp,false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mView.hideProgressBar()
                        userData.success = it.success
                        userData.isVerified = it.isVerified
                        userData.isActive = it.isActive
                        userData.isLoginOtpVerified = false
                        if(it.success && it.isVerified && it.isActive){
                            mView.showToast(it.msg,View.GONE,ToastPosition.BOTTOM)
                            mView.openLoginPage()
                        } else {
                            mView.showToast("Error in verifying OTP",View.GONE, ToastPosition.BOTTOM)
                        }
                    },{
                        mView.hideProgressBar()
                        mView.showToast("Error in verifying OTP",View.GONE, ToastPosition.BOTTOM)
                    }
                )
        )
    }

    override fun submitLoginOtp(userData: UserData, otp: Int) {
        mView.showProgressBar()
        compositeDisposable.add(
            mDataSourceInterface.verifyLoginOtp(userData,otp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mView.hideProgressBar()
//                        userData.success = it.success
//                        userData.isVerified = true
//                        userData.isActive = true
//                        userData.isLoginOtpVerified = true
                        if(it.success){
                            mView.showToast(it.msg,View.GONE,ToastPosition.BOTTOM)
                            if(userData.loginType == Const.TYPE_DOCTOR){
                                mView.openDoctorPanel(userData)
                            } else {
                                mView.openPatientPanel(userData)
                            }
                        } else {
                            mView.showToast("Error in verifying OTP",View.GONE, ToastPosition.BOTTOM)
                        }
                    },{
                        mView.hideProgressBar()
                        mView.showToast("Error in verifying OTP",View.GONE, ToastPosition.BOTTOM)
                    }
                )
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