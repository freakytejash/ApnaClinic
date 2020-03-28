package `in`.co.akgroups.apnaClinic.patient.change_password

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.change_pwd.*


class ShowChangePasswordInPopupFragment : BottomSheetDialogFragment(), ChangePasswordContract.View, View.OnClickListener, TextWatcher {

    override lateinit var presenter: ChangePasswordContract.Presenter
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    private lateinit var mUserData: UserData
    companion object {
        fun newInstance(bundle: Bundle): ShowChangePasswordInPopupFragment {
            val showChangePasswordInPopup = ShowChangePasswordInPopupFragment()
            showChangePasswordInPopup.arguments = bundle
            return showChangePasswordInPopup
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.change_pwd, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }

        presenter = ChangePasswordPresenter(Injection.provideDataRepository(context!!), this)
        toast = Toast(context!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submit.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
        tv_showCurrentPwd.setOnClickListener(this)
        tv_showNewPwd.setOnClickListener(this)
        et_confirm_password.addTextChangedListener(this)
        et_current_password.addTextChangedListener(this)
        et_new_password.addTextChangedListener(this)
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

    override fun dismissFragment() {
        dismiss()
    }
    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    override fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_cancel -> {
                dismiss()
            }

            R.id.btn_submit -> {
                val currentPassword  = et_current_password.text.toString()
                if (currentPassword.length < 8) {
                    showToast("Password should be of minimum 8 characters!",View.GONE,ToastPosition.BOTTOM)
                    return
                }
                val newPassword = et_new_password.text.toString()
                if (newPassword.length < 8) {
                    showToast("Password should be of minimum 8 characters!",View.GONE,ToastPosition.BOTTOM)
                    return
                }
                val confirmPassword = et_confirm_password.text.toString()
                if (confirmPassword != newPassword) {
                    showToast("New password and Confirm password not matching!",View.GONE,ToastPosition.BOTTOM)
                    return
                }

                presenter.changePassword(mUserData, currentPassword, newPassword)
            }

            R.id.tv_showCurrentPwd -> {
                if (tv_showCurrentPwd.text.toString() == getString(R.string.show)) {
                    showPassword(et_current_password)
                    tv_showCurrentPwd.text = getString(R.string.hide)
                } else {
                    hidePassword(et_current_password)
                    tv_showCurrentPwd.text = getString(R.string.show)
                }
            }

            R.id.tv_showNewPwd -> {
                if (tv_showNewPwd.text.toString() == getString(R.string.show)) {
                    showPassword(et_new_password)
                    tv_showNewPwd.text = getString(R.string.hide)
                } else {
                    hidePassword(et_new_password)
                    tv_showNewPwd.text = getString(R.string.show)
                }
            }

        }
    }

    private fun showPassword(editText: EditText?) {
        editText?.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }


    private fun hidePassword(editText: EditText?) {
        editText?.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun afterTextChanged(s: Editable?) {
        if(et_current_password.text.hashCode() == s.hashCode()){
            if (s.toString().isNotEmpty()) {
                tv_showCurrentPwd.visibility = View.VISIBLE
            } else {
                tv_showCurrentPwd.visibility = View.GONE
            }
        } else if(et_new_password.text.hashCode() == s.hashCode()){
            if (s.toString().isNotEmpty()) {
                tv_showNewPwd.visibility = View.VISIBLE
            } else {
                tv_showNewPwd.visibility = View.GONE
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}