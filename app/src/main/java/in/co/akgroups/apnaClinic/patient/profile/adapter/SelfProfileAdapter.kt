package `in`.co.akgroups.apnaClinic.patient.profile.adapter

import `in`.co.akgroups.apnaClinic.App
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.SelfProfileContract
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

class SelfProfileAdapter(
    val context: Context,
    val presenter: SelfProfileContract.Presenter,
    val view: SelfProfileContract.View,
    val userData: UserData,
    val dependentList: List<Dependent>,
    private val updateProfileListener: UpdateProfileListener
    ) : RecyclerView.Adapter<ProfileViewHolder>(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private var mCalender = Calendar.getInstance()
    private lateinit var profileViewHolder: ProfileViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_self_fragment, parent, false)
        profileViewHolder = ProfileViewHolder(App.applicationContext(), itemView, updateProfileListener)
        return profileViewHolder
    }

    override fun getItemCount(): Int {
        return dependentList.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(dependentList[position],userData.email!!)
        if(position == 0){
            updateProfileListener.updateProfile(dependentList[0])
        }
        holder.et_date_of_birth.setOnClickListener(this)
        holder.btn_Update.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.et_date_of_birth -> {
                val datePickerDialog = DatePickerDialog(
                    context, this, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                    mCalender.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
            R.id.btn_Update -> {

            }
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mCalender.set(Calendar.YEAR, year)
        mCalender.set(Calendar.MONTH, month)
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    private fun updateLabel() {
        val myFormat = "MMM-dd-yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        profileViewHolder.updateDate(sdf.format(mCalender.time))
    }
}