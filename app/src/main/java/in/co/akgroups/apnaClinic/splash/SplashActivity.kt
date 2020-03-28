package `in`.co.akgroups.apnaClinic.splash


import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.doctor.DoctorActivity
import `in`.co.akgroups.apnaClinic.login.LoginActivity
import `in`.co.akgroups.apnaClinic.patient.PatientActivity
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashContract.View{

    override lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(
            Injection.provideDataRepository(this),
            this
        )

    }

    override fun onStart() {
        super.onStart()
        presenter.start()

    }

    override fun showProgressBar() {
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pb_loading.visibility = View.GONE
    }

    override fun openLoginActivity() {
        var openIntent = Intent(this, LoginActivity::class.java)
        openWithDelay(openIntent)
    }

    override fun openDoctorPanel(userData: UserData) {
        var openIntent = Intent(this, DoctorActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        openWithDelay(openIntent)
    }

    fun openWithDelay(intent: Intent){
        Handler().postDelayed(Runnable {
            startActivity(intent)
            finish()
        }, 2000)
    }

    override fun openPatientPanel(userData: UserData) {
        var openIntent = Intent(this, PatientActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        openWithDelay(openIntent)
    }

    override fun showError(errorMessage: String) {

    }
    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}
