package `in`.co.akgroups.apnaClinic.patient.medical_report

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.patient.medical_report.adapter.MedicalReportAdapter
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.support.v4.app.FragmentManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MedicalReportPresenter(

    val dataSourceInterface: DataSourceInterface,
    view: MedicalReportContract.View,
    userData: UserData
) : MedicalReportContract.Presenter {

    var mDataSourceInterface: DataSourceInterface
    var mView: MedicalReportContract.View
    var mUserData: UserData
    lateinit var fragmentManager: FragmentManager
    init {
        mDataSourceInterface = dataSourceInterface
        mView = view
        mView.presenter = this
        this.mUserData = userData
    }

    var compositeDisposable = CompositeDisposable()
    override fun start() {
        fetchMedicalReport()
    }

    override fun removeFromDB(
        patientMedicalReport: PatientMedicalReport,
        listSize: Int,
        medicalReportAdapter: MedicalReportAdapter,
        position: Int
    ) {
        mView.showProgressBar()
        compositeDisposable.add(
            mDataSourceInterface
                .removeFromMedicalReport(mUserData, patientMedicalReport)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.hideProgressBar()
                    mView.showToast("Removed from report", View.GONE, ToastPosition.BOTTOM)
                    if (listSize == 1) {
                        mView.showNoReportLayout()
                        mView.hideProgressBar()
                    }

                }, {
                    mView.hideProgressBar()
                    mView.showSnackBarMessage("Please check internet connection")
                })
        )
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun fetchMedicalReport() {
        compositeDisposable.add(
            dataSourceInterface.fetchPatientMedicalReport(mUserData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it != null && it.isNotEmpty()){
                        mView.hideNoReportLayout()
                        mView.showMedicalReport(it)
                    } else {
                        mView.showNoReportLayout()
                    }
                },{
                })
        )
    }

    override fun popFragment() {
        mView.popFragment()
    }

}