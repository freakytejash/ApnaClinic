package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.TypeHabit
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.viewholders.HabitViewHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class HabitAdapter(context : Context, typeHabitList: List<TypeHabit>, clickListener : ClickListener) : RecyclerView.Adapter<HabitViewHolder>() {

    var typeHabitList : List<TypeHabit>
    var context : Context
    var mClickListener : HabitAdapter.ClickListener

    init {
        this.typeHabitList = typeHabitList
        this.context = context
        this.mClickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_type, parent, false)
        return HabitViewHolder(itemView, mClickListener)
    }

    override fun getItemCount(): Int {
        return typeHabitList.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(typeHabitList.get(position), position)
    }

    interface ClickListener {
        fun onClick(type: TypeHabit)

    }
}