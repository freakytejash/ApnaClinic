package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.TypeSurgery
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.viewholders.SurgeryViewHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SurgeryAdapter(context : Context, typeDiseasesList: List<TypeSurgery>, clickListener : ClickListener) : RecyclerView.Adapter<SurgeryViewHolder>() {

    var typeDiseasesList : List<TypeSurgery>
    var context : Context
    var mClickListener : SurgeryAdapter.ClickListener

    init {
        this.typeDiseasesList = typeDiseasesList
        this.context = context
        this.mClickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurgeryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_type, parent, false)
        return SurgeryViewHolder(itemView, mClickListener)
    }

    override fun getItemCount(): Int {
        return typeDiseasesList.size
    }

    override fun onBindViewHolder(holder: SurgeryViewHolder, position: Int) {
        holder.bind(typeDiseasesList.get(position), position)
    }

    interface ClickListener {
        fun onClick(type: TypeSurgery)

    }
}