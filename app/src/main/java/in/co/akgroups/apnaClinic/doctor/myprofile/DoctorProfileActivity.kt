package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.FragmentPagerAdapter
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.utils.Const
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_patient.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorProfileActivity: AppCompatActivity(),DoctorProfileActivityContract.View,View.OnClickListener  {

    override lateinit var presenter: DoctorProfileActivityContract.Presenter

    private var adapter: FragmentPagerAdapter? = null
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var toast: Toast
    lateinit var mUserData: UserData
    lateinit var bundle: Bundle
    private lateinit var mSnackbar: Snackbar
//    var data: Intent? = null

    private lateinit var doctorClinicDetailsPresenter:DoctorClinicDetailsPresenter
    private lateinit var doctorExperienceDetailsPresenter:DoctorExperienceDetailsPresenter
    private lateinit var doctorProfilePresenter:DoctorProfilePresenter
    private lateinit var doctorQualificationPresenter:DoctorQualificationPresenter

    private lateinit var mDoctorClinicDetailsFragment:DoctorClinicDetailsFragment
    private lateinit var mDoctorExperienceDetailsFragment:DoctorExperienceDetailsFragment
    private lateinit var mDoctorProfileFragment:DoctorProfileFragment
    private lateinit var mDoctorQualificationFragment:DoctorQualificationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = intent
        val bundle = intent.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle?.getParcelable(Const.KEY_USER_DATA)?: UserData()

//        val fragment = supportFragmentManager.findFragmentById(R.id.content_frame)
//                as DoctorProfileFragment? ?: DoctorProfileFragment.newInstance(bundle).also {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                val slideTransition = Slide(Gravity.START)
//                slideTransition.duration = 500
//                it.reenterTransition = slideTransition
//                it.exitTransition = slideTransition
//                it.sharedElementEnterTransition = ChangeBounds()
//            }
//            replaceFragmentInActivity(it, R.id.content_frame)
//        }

        setToolbar()
        setupClickListener()
        setupViewPager()
        setupTab()
        setHeaderViews()
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

        mDoctorClinicDetailsFragment=DoctorClinicDetailsFragment.newInstance(bundle)
        mDoctorExperienceDetailsFragment=DoctorExperienceDetailsFragment.newInstance(bundle)
        mDoctorProfileFragment=DoctorProfileFragment.newInstance(bundle)
        mDoctorQualificationFragment=DoctorQualificationFragment.newInstance(bundle)

        doctorClinicDetailsPresenter= DoctorClinicDetailsPresenter(
                Injection.provideDataRepository(applicationContext),
                mDoctorClinicDetailsFragment,
                mUserData,
                AppPreferenceHelper.getInstance(applicationContext)
        )
        doctorExperienceDetailsPresenter= DoctorExperienceDetailsPresenter(
                Injection.provideDataRepository(applicationContext),
                mDoctorExperienceDetailsFragment,
                mUserData,
                AppPreferenceHelper.getInstance(applicationContext)
        )
        doctorProfilePresenter = DoctorProfilePresenter(
                Injection.provideDataRepository(this),
                mDoctorProfileFragment,
                mUserData
        )
        doctorQualificationPresenter= DoctorQualificationPresenter(
                Injection.provideDataRepository(applicationContext),
                mDoctorQualificationFragment,
                mUserData,
                AppPreferenceHelper.getInstance(applicationContext)
        )

        adapter!!.addFragment(mDoctorProfileFragment,"Profile")
        adapter!!.addFragment(mDoctorQualificationFragment,"Qualification")
        adapter!!.addFragment(mDoctorExperienceDetailsFragment,"Experience Details")
        adapter!!.addFragment(mDoctorClinicDetailsFragment,"Clinic Details")

        content_view_pager.adapter = adapter
        content_view_pager.offscreenPageLimit = 5
        content_view_pager.setPagingEnabled(true)
    }

    override fun setupClickListener() {
        img_left.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {

    }

    override fun isConnectedToInternet(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}