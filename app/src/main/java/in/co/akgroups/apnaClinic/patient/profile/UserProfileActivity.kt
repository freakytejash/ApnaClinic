package `in`.co.akgroups.apnaClinic.patient.profile

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.replaceFragmentInActivity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity

class UserProfileActivity : AppCompatActivity(){

    private lateinit var userProfilePresenter: UserProfilePresenter
    lateinit var mUserData : UserData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lyt_profile)

        val intent = intent
        val bundle = intent.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle?.getParcelable(Const.KEY_USER_DATA)?: UserData()

        val ratingFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as UserProfileFragment? ?: UserProfileFragment.newInstance(bundle).also {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val slideTransition = Slide(Gravity.LEFT)
                slideTransition.duration = 500
                it.reenterTransition = slideTransition
                it.exitTransition = slideTransition
                it.sharedElementEnterTransition = ChangeBounds()
            }
            replaceFragmentInActivity(it, R.id.content_frame)
        }

        userProfilePresenter = UserProfilePresenter(
            Injection.provideDataRepository(this),
            ratingFragment,
            mUserData
        )
    }
}