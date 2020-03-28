package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.TypeDiseases
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.viewholders.DiseaseViewHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class DiseaseAdapter(context : Context, typeDiseasesList: List<TypeDiseases>, clickListener : ClickListener) : RecyclerView.Adapter<DiseaseViewHolder>() {

    var typeDiseasesList : List<TypeDiseases>
    var context : Context
    var mClickListener : DiseaseAdapter.ClickListener

    init {
        this.typeDiseasesList = typeDiseasesList
        this.context = context
        this.mClickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_type, parent, false)
        return DiseaseViewHolder(itemView, mClickListener)
    }

    override fun getItemCount(): Int {
        return typeDiseasesList.size
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        holder.bind(typeDiseasesList.get(position), position)
    }

    interface ClickListener {
        fun onClick(type: TypeDiseases)

    }
}