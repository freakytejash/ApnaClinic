package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.doctor.DoctorActivity
import `in`.co.akgroups.apnaClinic.patient.PatientActivity
import `in`.co.akgroups.apnaClinic.register.RegisterActivity
import `in`.co.akgroups.apnaClinic.register.otp.OtpActivity
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener, TextView.OnEditorActionListener {

    override lateinit var presenter: LoginContract.Presenter

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        presenter = LoginPresenter(
            Injection.provideDataRepository(this),
            this
        )
        toast = Toast(applicationContext)
        btn_login.setOnClickListener(this)
        btn_forgot_pwd.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
        edit_text_password.setOnEditorActionListener(this)
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

    override fun showError(errorMessage: String) {
        val snackbar = Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
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

    override fun openDoctorPanel(userData: UserData) {
        var openIntent = Intent(this, DoctorActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
        finish()
    }

    override fun openOtpPage(userData: UserData) {
        var openIntent = Intent(this, OtpActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, userData)
        bundle.putString(Const.OTP_TYPE,Const.OTP_TYPE_LOGIN)
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

    override fun openSignUpPage() {
        var openIntent = Intent(this, RegisterActivity::class.java)
        startActivity(openIntent)
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_login -> {
                if((!TextUtils.isEmpty(edit_text_login_id.text.trim().toString())) && (!TextUtils.isEmpty(edit_text_password.text.trim().toString()))){
                    if(rb_isDoctor.isChecked)
                    {
                        Utils.isDoctor = true
                    }
                    presenter.loginUser(edit_text_login_id.text.trim().toString(), edit_text_password.text.trim().toString(), if(rb_isDoctor.isChecked) 1 else 2)
                } else {
                    showError("Login Id and/or Password is empty")
                }
            }
            R.id.btn_signup -> {
                openSignUpPage()
            }
            R.id.btn_forgot_pwd -> {
                val bundle = Bundle()
                ShowForgotPasswordInPopupFragment.newInstance(bundle).show(supportFragmentManager, "dialog")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if(actionId == EditorInfo.IME_ACTION_GO){
            btn_login.performClick()
        }
        return false
    }
}