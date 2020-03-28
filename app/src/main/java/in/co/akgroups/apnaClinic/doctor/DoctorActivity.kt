package `in`.co.akgroups.apnaClinic.doctor

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.FragmentPagerAdapter
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.doctor.appointment.AppointmentsFragment
import `in`.co.akgroups.apnaClinic.doctor.appointment.AppointmentsPresenter
import `in`.co.akgroups.apnaClinic.doctor.cases.CasesFragment
import `in`.co.akgroups.apnaClinic.doctor.cases.CasesPresenter
import `in`.co.akgroups.apnaClinic.doctor.change_password.ShowChangePasswordInPopupFragment
import `in`.co.akgroups.apnaClinic.doctor.dashboard.DashboardFragment
import `in`.co.akgroups.apnaClinic.doctor.dashboard.DashboardPresenter
import `in`.co.akgroups.apnaClinic.doctor.myprofile.DoctorProfileActivity
import `in`.co.akgroups.apnaClinic.doctor.schedule.ScheduleFragment
import `in`.co.akgroups.apnaClinic.doctor.schedule.SchedulePresenter
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_patient.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.*

class DoctorActivity : AppCompatActivity(), DoctorActivityContract.View, View.OnClickListener {

    var adapter: FragmentPagerAdapter? = null
    override lateinit var presenter: DoctorActivityContract.Presenter

    private lateinit var dashboardPresenter: DashboardPresenter
    private lateinit var appointmentsPresenter: AppointmentsPresenter
    private lateinit var casesPresenter: CasesPresenter
    private lateinit var schedulePresenter: SchedulePresenter

    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var appointmentsFragment: AppointmentsFragment
    private lateinit var casesFragment: CasesFragment
    private lateinit var scheduleFragment: ScheduleFragment

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast

    lateinit var mUserData: UserData
    lateinit var bundle: Bundle
    private lateinit var mSnackbar: Snackbar
    var data: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_doctor)
        val intent = intent
        bundle = intent.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle.getParcelable(Const.KEY_USER_DATA)

        mSnackbar = Snackbar.make(drawer_layout, R.string.exit_message, Snackbar.LENGTH_SHORT)

        setToolbar()
        setupClickListener()
        setupViewPager()
        setupTab()
        setHeaderViews()
        setupNavigationView()
        toast = Toast(applicationContext)

    }

    private fun setupTab(){
        tab_filter.setupWithViewPager(content_view_pager)
    }

    private fun setHeaderViews() {
        if (!TextUtils.isEmpty(mUserData.first_name)) {
            nav_view.getHeaderView(0).txt_user_name.text = mUserData.first_name
        } else nav_view.getHeaderView(0).txt_user_name.text = getString(R.string.welcome)

        if (!TextUtils.isEmpty(mUserData.picture)) {
            setImageToImaveView(nav_view.getHeaderView(0).img_user as ImageView, mUserData.picture, R.drawable.ic_user)
        }
    }

    private fun setImageToImaveView(imageView: ImageView, url: String?, blankState: Int) {
        if (!TextUtils.isEmpty(url)) {
            if (imageView.width == 0 || imageView.height == 0) {
                imageView.post {
                    ImageDownloader
                        .getImageDownloader()
                        .setImageRoundedTransformation(
                            applicationContext,
                            url!!,
                            imageView,
                            blankState
                        )
                }
            } else {
                ImageDownloader
                    .getImageDownloader()
                    .setImageRoundedTransformation(
                        applicationContext,
                        url!!,
                        imageView,
                        blankState
                    )
            }

        } else {
            imageView.setImageResource(blankState)
//            imgContent.setImageResource(R.drawable.ic_card_empty_state)
        }
    }
    override fun setToolbar() {
        img_left.setImageResource(R.drawable.ic_navigation_menu)
        img_logo.setImageResource(R.drawable.logo)
        img_logo.visibility = View.VISIBLE
        img_left.visibility = View.VISIBLE
        img_right.visibility = View.GONE

        DrawableCompat.setTint(img_left.drawable, ContextCompat.getColor(this, R.color.colorPrimary))

        hideToolbarTitle()
    }
    private fun hideToolbarTitle() {
        txt_title.visibility = View.GONE
    }

    override fun setupViewPager() {
        adapter = FragmentPagerAdapter(supportFragmentManager)
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)

        dashboardFragment = DashboardFragment.newInstance(bundle)
        appointmentsFragment = AppointmentsFragment.newInstance(bundle)
        casesFragment = CasesFragment.newInstance(bundle)
        scheduleFragment = ScheduleFragment.newInstance(bundle)

        dashboardPresenter = DashboardPresenter(
            Injection.provideDataRepository(applicationContext),
            dashboardFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext)
        )

        appointmentsPresenter = AppointmentsPresenter(
            Injection.provideDataRepository(applicationContext),
            appointmentsFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext)
        )

        casesPresenter = CasesPresenter(
            Injection.provideDataRepository(applicationContext),
            casesFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext)
        )

        schedulePresenter = SchedulePresenter(
            Injection.provideDataRepository(applicationContext),
            scheduleFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext)
        )

        adapter!!.addFragment(dashboardFragment, "dashboard")
        adapter!!.addFragment(appointmentsFragment, "appointments")
        adapter!!.addFragment(scheduleFragment, "schedule")
        adapter!!.addFragment(casesFragment, "cases")

        content_view_pager.adapter = adapter
        content_view_pager.offscreenPageLimit = 5
        content_view_pager.setPagingEnabled(true)
    }

    override fun setupClickListener() {
        img_left.setOnClickListener(this)
    }

    override fun setupNavigationView() {
        nav_view.setItemIconTintList(null)
        nav_view.setNavigationItemSelectedListener{
                res ->
            when (res.itemId) {
                R.id.action_my_profile -> {
                    openMyProfileScreen(mUserData)
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_my_accounts -> {
                    openMyAccountsScreen(mUserData)
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_terms -> {
                    openTerms_N_ConditionScreen()
                    drawer_layout.closeDrawers()
                    true
                }

                R.id.action_feedback -> {
                    openFeedbackScreen()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_change_password -> {
                    openChangePasswordScreen()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_logout -> {
                    performLogout()
                    drawer_layout.closeDrawers()
                    true
                }
                else -> true
            }
        }
    }

    override fun openMyProfileScreen(userData: UserData) {
        var openIntent = Intent(this, DoctorProfileActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
    }

    override fun openMyAccountsScreen(userData: UserData) {
        showToast("openMyAccountsScreen", View.GONE, ToastPosition.BOTTOM)
    }

    override fun openTerms_N_ConditionScreen() {
        showToast("openTerms_N_ConditionScreen", View.GONE, ToastPosition.BOTTOM)
    }

    override fun openFeedbackScreen() {
        showToast("openFeedbackScreen", View.GONE, ToastPosition.BOTTOM)
    }

    override fun openChangePasswordScreen() {
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        ShowChangePasswordInPopupFragment.newInstance(bundle).show(supportFragmentManager, "dialog")
    }

    override fun performLogout() {
        showToast("performLogout", View.GONE, ToastPosition.BOTTOM)
    }

    override fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition) {
        val toastView = layoutInflater.inflate(R.layout.toast_layout, null)
        tv_msg = toastView.findViewById(R.id.tv_mesg)
        iv_toast = toastView.findViewById(R.id.iv_toast)
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT
        tv_msg.text = message
        iv_toast.visibility = imageVisibility
        if (toastPosition == ToastPosition.CENTRE) {
            toast.setGravity(Gravity.CENTER, 0, 0)
        } else
            toast.setGravity(Gravity.CENTER, 0, 450)
        toast.show()
    }

    override fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isConnectedToInternet(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.img_left -> {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }
    }
}