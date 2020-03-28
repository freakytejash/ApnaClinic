package `in`.co.akgroups.apnaClinic.register

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.dialog.CustomDialog
import `in`.co.akgroups.apnaClinic.custom.dialog.OnDialogItemClicked
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.login.RegisterContract
import `in`.co.akgroups.apnaClinic.patient.medical_report.MedicalReportActivity
import `in`.co.akgroups.apnaClinic.register.otp.OtpActivity
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.android.synthetic.main.layout_register.view.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), RegisterContract.View, View.OnClickListener, TextWatcher {

    override lateinit var presenter: RegisterContract.Presenter
    private var liveDialog: CustomDialog? = null
    var fist_name = ""
    var last_name = ""
    var email = ""
    var mobile = ""
    var password = ""
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register)

        presenter = RegisterPresenter(
            Injection.provideDataRepository(this),
            this
        )

        toast = Toast(applicationContext)
        btn_register.setOnClickListener(this)
        sign_in.setOnClickListener(this)
        tv_showPwd.setOnClickListener(this)
        et_password.addTextChangedListener(this)
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

    override fun openLoginPage() {
        finish()
    }

    override fun openOtpPage(userData: UserData) {
        var openIntent = Intent(this, OtpActivity::class.java)
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

    override fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }


    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_register -> {
                fist_name = et_first_name.text.toString()
                if (fist_name.isEmpty()) {
                    showSnackBarMessage("First name must be provided!")
                    return
                }
                last_name = et_last_name.text.toString()
                if(last_name.isEmpty()){
                    showSnackBarMessage("Last name must be provided!")
                }
                email = et_email.text.toString()
                if (!isValidEmail(email)) {
                    showSnackBarMessage("Invalid Email!")
                    return
                }
                mobile = et_mobile.text.toString()
                if (!isValidPhone(mobile)) {
                    showSnackBarMessage("Invalid Phone number!")
                    return
                }
                password = et_password.text.toString()
                if (password.length < 8) {
                    showSnackBarMessage("Password should be of minimum 8 characters!")
                    return
                }
                val confirmPassword = et_confirm_password.text.toString()
                if (confirmPassword != password) {
                    showSnackBarMessage("Password and Confirm password not matching!")
                    return
                }
                if (radio_is_doctor!!.yes.isChecked) {
                    showCustomDialog()
                    return
                }
                presenter.registerUser(
                    fist_name,
                    last_name,
                    email,
                    mobile.toLong(),
                    password,
                    Const.TYPE_PATIENT
                )
            }
            R.id.sign_in -> {
                openLoginPage()
            }

            R.id.tv_showPwd -> {
                if (tv_showPwd.text.toString() == getString(R.string.show)) {
                    showPassword()
                    tv_showPwd.text = getString(R.string.hide)
                } else {
                    hidePassword()
                    tv_showPwd.text = getString(R.string.show)
                }
            }
        }
    }

    private fun showCustomDialog() {

        liveDialog = CustomDialog(this, getString(R.string.message), object :
            OnDialogItemClicked {
            override fun onClick(view: View) {
                when (view!!.id) {
                    R.id.btnCancel -> {

                        liveDialog!!.dismiss()
                    }
                    R.id.btnConfirm -> {
                        try {
                            presenter.registerUser(
                                fist_name,
                                last_name,
                                email,
                                mobile.toLong(),
                                password,
                                Const.TYPE_DOCTOR
                            )

                            liveDialog!!.dismiss()
                        } catch (throwable: Throwable) {
                            liveDialog!!.dismiss()
                            throwable.printStackTrace()
                        }
                    }
                }
            }
        })
        liveDialog!!.show()
    }

    private fun showPassword() {
        et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    private fun hidePassword() {
        et_password.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().isNotEmpty()) {
            tv_showPwd.visibility = View.VISIBLE
        } else {
            tv_showPwd.visibility = View.GONE
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    private fun isValidPhone(value: String): Boolean {
        val p = Pattern.compile("(0/91)?[6-9][0-9]{9}")
        val m = p.matcher(value)
        return m.find() && m.group() == value
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}