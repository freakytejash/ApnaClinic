package `in`.co.akgroups.apnaClinic.patient.profile

import `in`.co.akgroups.apnaClinic.App
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.profile.adapter.SelfProfileAdapter
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.SelfProfileContract
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.SelfProfilePresenter
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.app.DatePickerDialog
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.DatePicker
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_profile.*
import java.util.*


class SelfProfileFragment : Fragment(), SelfProfileContract.View, View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var mUserData: UserData
    private var mCalender = Calendar.getInstance()
    override lateinit var presenter: SelfProfileContract.Presenter
    private lateinit var updateProfileListener: UpdateProfileListener

    companion object {
        fun newInstance(bundle: Bundle): SelfProfileFragment {
            val selfProfileFragment = SelfProfileFragment()
            selfProfileFragment.arguments = bundle
            return selfProfileFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.layout_profile, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }

        if (!::presenter.isInitialized) {
            presenter = SelfProfilePresenter(Injection.provideDataRepository(context!!), this, mUserData)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.convertUserDataToDependent(mUserData)
    }

    override fun showProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.VISIBLE
        }
    }

    override fun hideProgressBar() {
        if (pb_loading != null) {
            pb_loading.visibility = View.GONE
        }
    }

    override fun showError(errorMessage: String) {
        val snackbar = Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }

    override fun showDependentList(dependentList: List<Dependent>) {

        rv_profile.layoutManager =
            LinearLayoutManager(App.applicationContext(), LinearLayoutManager.VERTICAL, false)
        val selfProfileAdapter = SelfProfileAdapter(context!!, presenter, this, mUserData, dependentList, updateProfileListener)
        selfProfileAdapter.setHasStableIds(true)
        rv_profile.adapter = selfProfileAdapter
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mCalender.set(Calendar.YEAR, year)
        mCalender.set(Calendar.MONTH, monthOfYear)
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.et_date_of_birth -> {
                val datePickerDialog = DatePickerDialog(
                    context!!, this, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                    mCalender.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
        }
    }

    fun setListener(updateProfileListener: UpdateProfileListener){
        this.updateProfileListener = updateProfileListener
    }
}
