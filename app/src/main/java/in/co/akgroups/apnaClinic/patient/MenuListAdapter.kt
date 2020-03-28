package `in`.co.akgroups.apnaClinic.patient

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.base.BaseViewHolder
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.MenuItem
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MenuListAdapter(val context: Context, val list: List<MenuItem>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_items, parent, false)
        return MenuViewHolder(itemView.context, itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is MenuViewHolder) {

            val menuItem = list.get(holder.adapterPosition)
            holder.bind(menuItem, position)
        }
    }
}