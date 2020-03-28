package `in`.co.akgroups.apnaClinic.patient

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.FragmentPagerAdapter
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.patient.cases.CasesFragment
import `in`.co.akgroups.apnaClinic.patient.cases.CasesPresenter
import `in`.co.akgroups.apnaClinic.patient.change_password.ShowChangePasswordInPopupFragment
import `in`.co.akgroups.apnaClinic.patient.consult.ConsultFragment
import `in`.co.akgroups.apnaClinic.patient.consult.ConsultPresenter
import `in`.co.akgroups.apnaClinic.patient.dashboard.DashboardFragment
import `in`.co.akgroups.apnaClinic.patient.dashboard.DashboardPresenter
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.MedicalHistoryActivity
import `in`.co.akgroups.apnaClinic.patient.medical_report.MedicalReportActivity
import `in`.co.akgroups.apnaClinic.patient.profile.UserProfileActivity
import `in`.co.akgroups.apnaClinic.patient.profile.UserProfilePresenter
import `in`.co.akgroups.apnaClinic.patient.recent.RecentFragment
import `in`.co.akgroups.apnaClinic.patient.recent.RecentPresenter
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
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

class PatientActivity : AppCompatActivity(), PatientActivityContract.View, View.OnClickListener {

    var adapter: FragmentPagerAdapter? = null

    override lateinit var presenter: PatientActivityContract.Presenter
    private lateinit var dashboardPresenter: DashboardPresenter
    private lateinit var recentPresenter: RecentPresenter
    private lateinit var consultPresenter: ConsultPresenter
    private lateinit var casesPresenter: CasesPresenter
    private lateinit var userProfilePresenter: UserProfilePresenter

    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast

    lateinit var mUserData: UserData
    lateinit var bundle: Bundle
    private lateinit var mSnackbar: Snackbar
    var data: Intent? = null

    lateinit var dashboardFragment: DashboardFragment
    lateinit var recentFragment: RecentFragment
    lateinit var consultFragment: ConsultFragment
    lateinit var casesFragment: CasesFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_patient)
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
        /*
        * Commenting out below code as we are not setting drawer layout values dynamically.
        * */
//        var menuItem = MenuItem()
//        menuItem.id = 1
//        menuItem.priority = 1
//        menuItem.name = "test"
//        menuItem.picture = ""
//
//        var menuItem2 = MenuItem()
//        menuItem2.id = 2
//        menuItem2.priority = 2
//        menuItem2.name = "test2"
//        menuItem2.picture = ""
//
//        menu_items.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val adapter = MenuListAdapter(
//            this,menuList
//        )
//        menu_items.adapter = adapter
    }

    fun setHeaderViews() {
        if (!TextUtils.isEmpty(mUserData.first_name)) {
            nav_view.getHeaderView(0).txt_user_name.text = mUserData.first_name
        } else nav_view.getHeaderView(0).txt_user_name.text = getString(R.string.welcome)

        if (!TextUtils.isEmpty(mUserData.picture)) {
            setImageToImaveView(nav_view.getHeaderView(0).img_user, mUserData.picture, R.drawable.ic_user)
        }
    }

    override fun setupNavigationView() {
        nav_view.setItemIconTintList(null)
        nav_view.setNavigationItemSelectedListener{
                res ->
            when (res.itemId) {
                R.id.action_medical_hostory -> {
                    openMedicalHistoryScreen()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_medical_report -> {
                    openMedicalReportsScreen()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.action_user_profile -> {
                    openUserProfileScreen()
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

    override fun setupViewPager() {
        adapter = FragmentPagerAdapter(supportFragmentManager)
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)

        dashboardFragment = DashboardFragment.newInstance(bundle)
        recentFragment = RecentFragment.newInstance(bundle)
        consultFragment = ConsultFragment.newInstance(bundle)
        casesFragment = CasesFragment.newInstance(bundle)

        dashboardPresenter = DashboardPresenter(
            Injection.provideDataRepository(this),
            dashboardFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext!!)
        )

        recentPresenter = RecentPresenter(
            Injection.provideDataRepository(this),
            recentFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext!!)
        )

        consultPresenter = ConsultPresenter(
            Injection.provideDataRepository(this),
            consultFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext!!)
        )

        casesPresenter = CasesPresenter(
            Injection.provideDataRepository(this),
            casesFragment,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext!!)
        )


        adapter!!.addFragment(dashboardFragment, "dashboard")
        adapter!!.addFragment(recentFragment, "recent")
        adapter!!.addFragment(consultFragment, "consult")
        adapter!!.addFragment(casesFragment, "cases")

        content_view_pager.adapter = adapter
        content_view_pager.offscreenPageLimit = 5
        content_view_pager.setPagingEnabled(true)
    }


    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(this)
    }

    private fun setupTab(){
        tab_filter.setupWithViewPager(content_view_pager)
//        tab_filter.addTab(tab_filter.newTab().setTag(getString(R.string.dashboard)), 0)
//        tab_filter.addTab(tab_filter.newTab().setTag(getString(R.string.recent)), 1)
//        tab_filter.addTab(tab_filter.newTab().setTag(getString(R.string.consult)), 2)
//        tab_filter.addTab(tab_filter.newTab().setTag(getString(R.string.cases)), 3)
//
//        val tab_item_layout1 = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null)
//        val tab_item_layout2 = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null)
//        val tab_item_layout3 = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null)
//
//        tab_item_layout1.findViewById<TextView>(R.id.tab_text).text = getString(R.string.filter_all)
//        tab_item_layout2.findViewById<TextView>(R.id.tab_text).text = getString(R.string.filter_movies)
//        tab_item_layout3.findViewById<TextView>(R.id.tab_text).text = getString(R.string.filter_series)
//
//        tab_filter.getTabAt(0)!!.customView = tab_item_layout1
//        tab_filter.getTabAt(1)!!.customView = tab_item_layout2
//        tab_filter.getTabAt(2)!!.customView = tab_item_layout3
//
//        tab_filter.setSelectedTabIndicatorColor(ContextCompat.getColor(context!!, R.color.colorAccent))
//        tab_filter.setSelectedTabIndicatorHeight((3 * getResources().getDisplayMetrics().density).toInt())
    }

    override fun setToolbar() {
        img_left.setImageResource(R.drawable.ic_navigation_menu)
        img_logo.setImageResource(R.drawable.logo)
        img_logo.visibility = View.VISIBLE
        img_left.visibility = View.VISIBLE
        img_right.visibility = View.GONE

        DrawableCompat.setTint(img_left.getDrawable(), ContextCompat.getColor(this, R.color.colorPrimary))

        hideToolbarTitle()
    }

    fun hideToolbarTitle() {
        txt_title.visibility = View.GONE
    }

    override fun setupClickListener() {
        img_left.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.img_left -> {
                drawer_layout.openDrawer(Gravity.LEFT)
//                drawer_layout.closeDrawers()
            }
        }
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

    override fun openMedicalHistoryScreen() {
        var openIntent = Intent(this, MedicalHistoryActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
    }

    override fun openMedicalReportsScreen() {
        var openIntent = Intent(this, MedicalReportActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)
    }

    override fun openUserProfileScreen() {
        val openIntent = Intent(this, UserProfileActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        openIntent.putExtra(Const.ACTION_DATA, bundle)
        startActivity(openIntent)

    }

    override fun openChangePasswordScreen() {
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)
        ShowChangePasswordInPopupFragment.newInstance(bundle).show(supportFragmentManager, "dialog")
    }

    override fun performLogout() {
        showToast("Perform Logout", View.GONE, ToastPosition.BOTTOM)
    }

}