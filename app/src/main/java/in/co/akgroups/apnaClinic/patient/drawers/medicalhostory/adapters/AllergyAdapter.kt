package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.TypeAllergy
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.viewholders.AllergyViewHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class AllergyAdapter(context : Context, typeAllergyList: List<TypeAllergy>, clickListener : ClickListener) : RecyclerView.Adapter<AllergyViewHolder>() {

    var typeAllergyList : List<TypeAllergy>
    var context : Context
    var mClickListener : AllergyAdapter.ClickListener

    init {
        this.typeAllergyList = typeAllergyList
        this.context = context
        this.mClickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_type, parent, false)
        return AllergyViewHolder(itemView, mClickListener)
    }

    override fun getItemCount(): Int {
        return typeAllergyList.size
    }

    override fun onBindViewHolder(holder: AllergyViewHolder, position: Int) {
        holder.bind(typeAllergyList.get(position), position)
    }

    interface ClickListener {
        fun onClick(type: TypeAllergy)

    }
}