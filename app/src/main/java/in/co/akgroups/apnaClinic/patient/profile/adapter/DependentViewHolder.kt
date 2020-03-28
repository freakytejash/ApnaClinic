package `in`.co.akgroups.apnaClinic.patient.profile.adapter

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import `in`.co.akgroups.apnaClinic.utils.Const
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*

class DependentViewHolder(val context: Context, rootView: View, val updateProfileListener: UpdateProfileListener) :
    RecyclerView.ViewHolder(rootView) {

    var dependent_title : TextView
    var et_email: EditText
    var et_phone: EditText
    var tv_editEmail: TextView
    var tv_editPhone: TextView
    var et_date_of_birth: EditText
    var et_address: EditText
    var radioMale: RadioButton
    var radioFemale: RadioButton
    var radioGrpGender: RadioGroup
    var btn_Update: Button

    init {
        et_email = rootView.findViewById(R.id.et_email)
        et_phone = rootView.findViewById(R.id.et_phone)
        tv_editEmail = rootView.findViewById(R.id.tv_editEmail)
        tv_editPhone = rootView.findViewById(R.id.tv_editPhone)
        et_date_of_birth = rootView.findViewById(R.id.et_date_of_birth)
        et_address = rootView.findViewById(R.id.et_address)
        radioMale = rootView.findViewById(R.id.radioMale)
        radioFemale = rootView.findViewById(R.id.radioFemale)
        radioGrpGender = rootView.findViewById(R.id.radioGrpGender)
        btn_Update = rootView.findViewById(R.id.btn_Update)
        dependent_title = rootView.findViewById(R.id.dependent_title)
    }

    fun bind(dependent: Dependent, email: String) {
        et_email.setText(dependent.email)
        et_phone.setText(dependent.telephone.toString())
        et_date_of_birth.setText(dependent.dob)
        et_address.setText(dependent.primaryAddress)
        if (dependent.gender == Const.FEMALE) {
            radioFemale.isChecked = true
        } else {
            radioMale.isChecked = true
        }
    }
}