package `in`.co.akgroups.apnaClinic.register.otp


import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.doctor.DoctorActivity
import `in`.co.akgroups.apnaClinic.login.LoginActivity
import `in`.co.akgroups.apnaClinic.patient.PatientActivity
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by narendrapal on 15/01/2020
 */

class OtpActivity : AppCompatActivity(), OtpContract.View {

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    override lateinit var presenter: OtpContract.Presenter
    private lateinit var mUserData: UserData
    private var otpType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val intent = intent
        val bundle = intent.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle?.getParcelable(Const.KEY_USER_DATA) ?: UserData()
        otpType = bundle?.getString(Const.OTP_TYPE) ?: ""
        toast = Toast(applicationContext)
        if (otpType == Const.OTP_TYPE_LOGIN) {
            lyt_Login.visibility = View.VISIBLE
            lyt_SignUp.visibility = View.GONE
        } else {
            lyt_Login.visibility = View.GONE
            lyt_SignUp.visibility = View.VISIBLE
        }
        presenter = OtpPresenter(
            Injection.provideDataRepository(this),
            this
        )
        setToolbar()
        btn_Submit.setOnClickListener {
            val phoneOtp = et_phoneOtp.text.trim().toString()
            val emailOtp = et_emailOtp.text.trim().toString()
            val otp = et_Otp.text.trim().toString()
            if (otpType == Const.OTP_TYPE_LOGIN) {
                if (otp.isNotEmpty()) {
                    presenter.submitLoginOtp(mUserData, otp.toInt())
                }
            } else {
                if (phoneOtp.isNotEmpty() && emailOtp.isNotEmpty()) {
                    presenter.submitOtp(mUserData, phoneOtp.toInt(), emailOtp.toInt())
                } else {
                    showToast(
                        getString(R.string.valid_otp_error_message),
                        View.GONE,
                        ToastPosition.BOTTOM
                    )
                }
            }
        }
        img_left.setOnClickListener {
            finish()
        }
    }

    private fun setToolbar() {
        img_left.visibility = View.VISIBLE
        img_right.visibility = View.GONE
        txt_title.visibility = View.VISIBLE
        img_filter.visibility = View.GONE

        img_left.setImageResource(R.drawable.ic_back)
        txt_title.text = getString(R.string.otp_verification)

        DrawableCompat.setTint(
            img_left.drawable,
            ContextCompat.getColor(this, R.color.colorPrimary)
        )
    }

    override fun showProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.GONE
        }
    }

    override fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }

    override fun openLoginPage() {
        var openIntent = Intent(this, LoginActivity::class.java)
        startActivity(openIntent)
        finish()
    }

    override fun openDoctorPanel(userData: UserData) {
        var openIntent = Intent(this, DoctorActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
        finish()
    }

    override fun openPatientPanel(userData: UserData) {
        var openIntent = Intent(this, PatientActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
        finish()
    }

    override fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition) {
        val toastView = layoutInflater.inflate(R.layout.toast_layout, null)
        tv_msg = toastView.findViewById(R.id.tv_mesg)
        iv_toast = toastView.findViewById(R.id.iv_toast)
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT
        tv_msg.text = message
        iv_toast.visibility = imageVisibility
        if (toastPosition == ToastPosition.CENTRE) {
            toast.setGravity(Gravity.CENTER, 0, 0)
        } else
            toast.setGravity(Gravity.CENTER, 0, 450)
        toast.show()
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }


}