package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorProfileActivityContract {

    interface View : BaseView<Presenter> {

        fun setToolbar()

        fun setupViewPager()

        fun setupClickListener()

    }

    interface Presenter : BasePresenter {
        fun fetchDoctorProfile(userData: UserData)
    }
}