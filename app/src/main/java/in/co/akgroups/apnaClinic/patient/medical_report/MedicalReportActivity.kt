package `in`.co.akgroups.apnaClinic.patient.medical_report

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

class MedicalReportActivity: AppCompatActivity() {

    private lateinit var medicalReportPresenter: MedicalReportPresenter
    private lateinit var mUserData: UserData
    private lateinit var reportFragment: MedicalReportFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_report)
        val intent = intent
        val bundle = intent.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle?.getParcelable(Const.KEY_USER_DATA)?: UserData()


        reportFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
                as MedicalReportFragment? ?: MedicalReportFragment.newInstance(bundle).also {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val slideTransition = Slide(Gravity.LEFT)
                slideTransition.duration = 500
                it.reenterTransition = slideTransition
                it.exitTransition = slideTransition
                it.sharedElementEnterTransition = ChangeBounds()
            }
            replaceFragmentInActivity(it, R.id.content_frame)
        }

        medicalReportPresenter = MedicalReportPresenter(
            Injection.provideDataRepository(this),
            reportFragment,
            mUserData
        )
    }

    override fun onBackPressed() {
        if(::reportFragment.isInitialized){
            val isImageOpened = reportFragment.onBackPressed()
            if(isImageOpened){
                return
            }
        }
        super.onBackPressed()
    }
}