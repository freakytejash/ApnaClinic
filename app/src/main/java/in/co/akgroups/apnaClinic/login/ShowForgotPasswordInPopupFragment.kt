package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.forgot_pwd.*
import android.util.Patterns
import android.text.TextUtils
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class ShowForgotPasswordInPopupFragment : BottomSheetDialogFragment(), ForgotPwdContract.View, View.OnClickListener {

    override lateinit var presenter: ForgotPwdContract.Presenter
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    companion object {
        fun newInstance(bundle: Bundle): ShowForgotPasswordInPopupFragment {
            val showFriendsInPopupFragment = ShowForgotPasswordInPopupFragment()
            showFriendsInPopupFragment.arguments = bundle
            return showFriendsInPopupFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.forgot_pwd, container, false)
        presenter = ForgotPwdPresenter(Injection.provideDataRepository(context!!), this)
        toast = Toast(context!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submit.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
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
                val email  = edit_text_email_id.text.toString()
                if(isValidEmail(email)){
                    presenter.validateEmail(email)
                } else {
                    showToast("Enter valid Email", View.GONE, ToastPosition.BOTTOM)
                }
            }
        }
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}