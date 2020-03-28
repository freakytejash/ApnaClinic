package `in`.co.akgroups.apnaClinic.patient.profile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.FragmentPagerAdapter
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.image_downloader.ImageDownloader
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.DependentFragment
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.DependentPresenter
import `in`.co.akgroups.apnaClinic.patient.profile.dependent.SelfProfilePresenter
import `in`.co.akgroups.apnaClinic.patient.profile.listener.UpdateProfileListener
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.layout_user_profile.*

class UserProfileFragment : Fragment(), UserProfileContract.View, UpdateProfileListener {

    private var adapter: FragmentPagerAdapter? = null
    override lateinit var presenter: UserProfileContract.Presenter
    private lateinit var toast: Toast
    private lateinit var tv_msg: TextView
    private lateinit var iv_toast: ImageView
    private lateinit var mUserData: UserData
    lateinit var selfProfileFragment: SelfProfileFragment
    lateinit var dependentFragment: DependentFragment

    private lateinit var selfProfilePresenter: SelfProfilePresenter
    private lateinit var dependentPresenter: DependentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.layout_user_profile, container, false)

        if (arguments != null) {
            mUserData = arguments!!.getParcelable(Const.KEY_USER_DATA)
        }

        if (!::presenter.isInitialized) {
            presenter = UserProfilePresenter(Injection.provideDataRepository(context!!), this, mUserData)
        }
        toast = Toast(context!!)
        return rootView
    }

    override fun updateProfile(dependent: Dependent) {
        tv_bmi.text = dependent.bmi
        tv_height.text = dependent.heightInFeet
        tv_bloodGrp.text = dependent.bloodGroup
        tv_weight.text = dependent.weightInKg
        toolbar_layout.title = dependent.first_name
        setImage(dependent.profilePic!!,expandedImage)
    }

    private fun setImage(thumbnail: String, imageView: ImageView) {

        if (TextUtils.isEmpty(thumbnail)) {
            return
        }

        if (imageView.width == 0 || imageView.height == 0) {
            imageView.post {
                ImageDownloader
                    .getImageDownloader()
                    .setImageByPath(
                        imageView.context,
                        thumbnail,
                        imageView,
                        R.drawable.default_user
                    )
            }
        } else {
            ImageDownloader
                .getImageDownloader()
                .setImageByPath(
                    imageView.context,
                    thumbnail,
                    imageView,
                    R.drawable.default_user
                )
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): UserProfileFragment {
            var userProfileFragment = UserProfileFragment()
            userProfileFragment.arguments = bundle
            return userProfileFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        toolbar_layout.title = "Apna clinic"
//        activity!!.title = "Title"
//        expandedImage.setImageResource(R.drawable.photo)

        presenter.fetchUserProfile(mUserData)
        setupViewPager()
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition) {
        try {
            val toastView = layoutInflater.inflate(R.layout.toast_layout, null)
            // Set custom view in toast.
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
        } catch (throwable: Throwable) {

        }
    }

    override fun showSnackBarMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupViewPager() {
        adapter = FragmentPagerAdapter(activity!!.supportFragmentManager)
        val bundle = Bundle()
        bundle.putParcelable(Const.KEY_USER_DATA, mUserData)

        selfProfileFragment = SelfProfileFragment.newInstance(bundle)
        selfProfileFragment.setListener(this)
        dependentFragment = DependentFragment.newInstance(bundle)

        selfProfilePresenter = SelfProfilePresenter(
            Injection.provideDataRepository(context!!),
            selfProfileFragment,
            mUserData
        )

        dependentPresenter =
            DependentPresenter(
                Injection.provideDataRepository(context!!),
                dependentFragment,
                mUserData
            )

        adapter!!.addFragment(selfProfileFragment, "Self")
        adapter!!.addFragment(dependentFragment, "family")


        vp_profile.adapter = adapter
        vp_profile.offscreenPageLimit = 2
        vp_profile.setPagingEnabled(true)

        tabs.setupWithViewPager(vp_profile)
    }

    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(context!!)
    }
}