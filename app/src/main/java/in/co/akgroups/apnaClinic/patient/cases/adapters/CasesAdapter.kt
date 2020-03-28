package `in`.co.akgroups.apnaClinic.patient.cases.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.cases.model.CaseData
import android.content.Context
import android.support.v4.content.ContextCompat

class CasesAdapter(private val dataSet: Array<CaseData>, internal var context: Context) :
    RecyclerView.Adapter<CasesAdapter.ViewHolder>() {

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewTitle: TextView
        internal var textViewStatus: TextView
        internal var textViewDate: TextView
        internal var textViewDescription: TextView

        init {
            this.textViewTitle = itemView.findViewById<View>(R.id.title) as TextView
            this.textViewStatus = itemView.findViewById<View>(R.id.status) as TextView
            this.textViewDate = itemView.findViewById<View>(R.id.date) as TextView
            this.textViewDescription = itemView.findViewById<View>(R.id.description) as TextView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_iten_case, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = dataSet[position].title

        holder.textViewDate.text = dataSet[position].date
        holder.textViewDescription.text = dataSet[position].description

        if (dataSet[position].status!!.equals("open", ignoreCase = true)) {
            holder.textViewStatus.visibility = View.GONE
        } else {
            holder.textViewStatus.visibility = View.VISIBLE
            holder.textViewStatus.text = context.getString(R.string.status) + " : " + dataSet[position].status
//            holder.textViewStatus.text = activity.resources.getString(R.string.status) + " : " + dataSet[position].status
            if (dataSet[position].status!!.equals("closed", ignoreCase = true)) {

                holder.textViewStatus.setTextColor(ContextCompat.getColor(context,R.color.green))
            } else if (dataSet[position].status!!.equals("expired", ignoreCase = true)) {
                holder.textViewStatus.setTextColor(ContextCompat.getColor(context,R.color.red))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}