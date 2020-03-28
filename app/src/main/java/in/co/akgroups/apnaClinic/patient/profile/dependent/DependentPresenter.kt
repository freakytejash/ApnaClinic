package `in`.co.akgroups.apnaClinic.patient.profile.dependent

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DependentPresenter(
    val dataSourceInterface: DataSourceInterface, val view: DependentContract.View,
    val userData: UserData
) : DependentContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchDependentData(mUserData: UserData) {
        compositeDisposable.add(
            dataSourceInterface.fetchDependentsData(mUserData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgressBar()

                    val dependentList = ArrayList<Dependent>()
                    val dependent = Dependent()
                    val dependent2 = Dependent()
                    userData.primaryAddress = "boisar"
                    userData.dob = "absc"
                    userData.gender = "male"
                    userData.email = "dfj@djg.com"
                    userData.telephone = 9859859686
                    userData.bmi = "82"
                    userData.heightInFeet = "5.8 feet"
                    userData.bloodGroup = "O+ve"
                    userData.weightInKg = "75kg"


                    dependent.zip = userData.zip
                    dependent.weightInKg = userData.weightInKg
                    dependent.telephone = userData.telephone
                    dependent.state = userData.state
                    dependent.profilePic = userData.picture
                    dependent.primaryAddress = userData.primaryAddress
                    dependent.first_name = userData.first_name
                    dependent.last_name = userData.last_name
                    dependent.heightInFeet = userData.heightInFeet
                    dependent.gender = userData.gender
                    dependent.dob = userData.dob
                    dependent.country = userData.country
                    dependent.city = userData.city
                    dependent.bloodGroup = userData.bloodGroup
                    dependent.bmi = userData.bmi
                    dependent.email = userData.email
                    dependent.id = userData.id

                    dependent2.zip = userData.zip
                    dependent2.weightInKg = userData.weightInKg
                    dependent2.telephone = userData.telephone
                    dependent2.state = userData.state
                    dependent2.profilePic = userData.picture
                    dependent2.primaryAddress = userData.primaryAddress
                    dependent2.first_name  = userData.first_name
                    dependent2.last_name  = userData.last_name
                    dependent2.heightInFeet = userData.heightInFeet
                    dependent2.gender = userData.gender
                    dependent2.dob = userData.dob
                    dependent2.country = userData.country
                    dependent2.city = userData.city
                    dependent2.bloodGroup = userData.bloodGroup
                    dependent2.bmi = userData.bmi
                    dependent2.email = userData.email
                    dependent2.id = userData.id

                    dependentList.add(dependent)
                    dependentList.add(dependent2)

                    view.showDependentList(it)
                }, {
                    view.hideProgressBar()
                    view.showError("Error in login, try again!")
                })
        )
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}