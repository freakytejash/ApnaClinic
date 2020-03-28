package `in`.co.akgroups.apnaClinic.custom.dialog

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.CustomButton
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.TextView


class CustomDialog(context: Context, val message : String, val onDialogItemClicked: OnDialogItemClicked) : Dialog(context),
    android.view.View.OnClickListener {

    var yes: CustomButton
    var no: CustomButton
    var txt_title : TextView
    var dialog : Dialog? = null

    init {
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.custom_dialog)
        dialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        yes = dialog!!.findViewById(R.id.btnConfirm)
        no = dialog!!.findViewById(R.id.btnCancel)
        txt_title = dialog!!.findViewById(R.id.txt_title)

        if (!TextUtils.isEmpty(message)) {
            txt_title.text = message
        }

        yes.setOnClickListener(this)
        no.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        onDialogItemClicked.onClick(v)
    }

    override fun show(){
        if (dialog != null) {
            dialog!!.show()
        }
    }

    override fun dismiss() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }
}