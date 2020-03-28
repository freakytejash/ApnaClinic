package `in`.co.akgroups.apnaClinic.patient.profile.dependent

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import io.reactivex.disposables.CompositeDisposable

class SelfProfilePresenter(
    val dataSourceInterface: DataSourceInterface, val view: SelfProfileContract.View,
    userData: UserData
) : SelfProfileContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun convertUserDataToDependent(userData: UserData) {

        view.showProgressBar()

        val dependentList = ArrayList<Dependent>()
        val dependent = Dependent()
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

        dependentList.add(dependent)
        view.hideProgressBar()
        view.showDependentList(dependentList)
    }

    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}