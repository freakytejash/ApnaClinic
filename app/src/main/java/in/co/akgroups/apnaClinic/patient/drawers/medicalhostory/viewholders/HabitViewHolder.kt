package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.viewholders

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.CustomButton
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.TypeHabit
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters.HabitAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class HabitViewHolder(val view: View, val clickListener: HabitAdapter.ClickListener) : RecyclerView.ViewHolder(view),
    View.OnClickListener {
    var btnType: CustomButton

    init {
        btnType = view.findViewById(R.id.btn_type)
        btnType.setOnClickListener(this)
    }

    lateinit var typeEntity: TypeHabit

    fun bind(typeEntity: TypeHabit, position: Int) {
        this.typeEntity = typeEntity

        btnType.text = typeEntity.name

        if (typeEntity.isSelected) {
            btnType.isSelected = true
            btnType.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        } else {
            btnType.isSelected = false
            btnType.setTextColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
        }

    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btn_type -> {
                clickListener.onClick(typeEntity)
                if (typeEntity.isSelected) {
                    btnType.isSelected = true
                    btnType.setTextColor(ContextCompat.getColor(view.context, R.color.white))
                } else {
                    btnType.isSelected = false
                    btnType.setTextColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
                }
            }
        }
    }
}