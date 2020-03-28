package `in`.co.akgroups.apnaClinic.patient.profile.adapter

import `in`.co.akgroups.apnaClinic.App
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.DependentContract
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

class DependentAdapter(
    val context: Context,
    val presenter: DependentContract.Presenter,
    val view: DependentContract.View,
    val userData: UserData,
    val dependentList: List<Dependent>,
    val updateProfileListener: UpdateProfileListener
) : RecyclerView.Adapter<DependentViewHolder>() {

    private var mCalender = Calendar.getInstance()
    private lateinit var dependentViewHolder: DependentViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DependentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.dependent_row, parent, false)
        dependentViewHolder = DependentViewHolder(App.applicationContext(), itemView, updateProfileListener)
        return dependentViewHolder
    }

    override fun onBindViewHolder(holder: DependentViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}