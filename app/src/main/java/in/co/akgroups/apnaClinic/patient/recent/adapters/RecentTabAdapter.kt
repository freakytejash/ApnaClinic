package `in`.co.akgroups.apnaClinic.patient.recent.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.patient.recent.model.PatientRequest

class RecentTabAdapter(private val dataSet: Array<PatientRequest>) :
    RecyclerView.Adapter<RecentTabAdapter.ViewHolder>() {

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewTitle: TextView
        internal var textViewsubTitle: TextView
        internal var textViewDate: TextView
        internal var textViewDescription: TextView
        internal var buttonPayNow: Button

        init {
            this.textViewTitle = itemView.findViewById<View>(R.id.title) as TextView
            this.textViewsubTitle = itemView.findViewById<View>(R.id.subtitle) as TextView
            this.textViewDate = itemView.findViewById<View>(R.id.date) as TextView
            this.textViewDescription = itemView.findViewById<View>(R.id.description) as TextView
            this.buttonPayNow = itemView.findViewById<View>(R.id.pay_now) as Button
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recent, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = dataSet[position].title
        holder.textViewsubTitle.text = dataSet[position].subTitle
        holder.textViewDate.text = dataSet[position].date
        holder.textViewDescription.text = dataSet[position].description
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}