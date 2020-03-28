package `in`.co.akgroups.apnaClinic.patient.profile.dependent

import `in`.co.akgroups.apnaClinic.App
import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.profile.adapter.DependentAdapter
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_dependent.*

class DependentFragment : Fragment(), DependentContract.View {

    override lateinit var presenter: DependentContract.Presenter
    private lateinit var mUserData: UserData
    private lateinit var updateProfileListener: UpdateProfileListener
    companion object {
        fun newInstance(bundle: Bundle): DependentFragment {
            val dependentFragment = DependentFragment()
            dependentFragment.arguments = bundle
            return dependentFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.layout_dependent, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }

        if (!::presenter.isInitialized) {
            presenter = DependentPresenter(Injection.provideDataRepository(context!!), this, mUserData)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchDependentData(mUserData)
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

    override fun showDependentList(list: List<Dependent>) {
//        recyclerView.layoutManager = LinearLayoutManager(App.applicationContext(), LinearLayoutManager.VERTICAL, false)
//        val dependentAdapter = DependentAdapter(context!!, presenter, this, mUserData, list, updateProfileListener)
//        dependentAdapter.setHasStableIds(true)
//        recyclerView.adapter = dependentAdapter
    }

    override fun showError(errorMessage: String) {
        val snackbar = Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT)
        snackbar.show()
        val view = snackbar.getView()
        val txtv = view.findViewById(android.support.design.R.id.snackbar_text) as TextView
        txtv.gravity = Gravity.CENTER_HORIZONTAL
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }

    fun setListener(updateProfileListener: UpdateProfileListener){
        this.updateProfileListener = updateProfileListener
    }

}