package `in`.co.akgroups.apnaClinic.splash

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.Const
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class SplashPresenter(val dataSourceInterface: DataSourceInterface, splashView: SplashContract.View) : SplashContract.Presenter {


    var mDataSourceInterface: DataSourceInterface
    var mSplashView: SplashContract.View
    lateinit var mUserData: UserData

    var compositeDisposable = CompositeDisposable()

    init {
        mDataSourceInterface = dataSourceInterface
        mSplashView = splashView
        mSplashView.presenter = this
    }


    override fun checkIfUserAlreadyLoggedIn() {
        compositeDisposable.add(
            mDataSourceInterface.getUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    if (res != null && res.isLoginOtpVerified) {
                        if(res.loginType == Const.TYPE_DOCTOR){
                            mSplashView.openDoctorPanel(res)
                        } else {
                           mSplashView.openPatientPanel(res)
                        }
                    } else {
                        mSplashView.openLoginActivity()
                    }
                }, {
                    mSplashView.openLoginActivity()
                })

        )
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun start() {
        checkIfUserAlreadyLoggedIn()
    }


//    fun clearData() {
//        mDataSourceInterface.deleteAllAppData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                LoginManager.getInstance().logOut()
//                start()
//            }, {})
//    }

}