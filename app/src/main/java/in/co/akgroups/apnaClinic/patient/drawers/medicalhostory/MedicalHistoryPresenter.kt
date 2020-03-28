package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class MedicalHistoryPresenter(
    val dataSourceInterface: DataSourceInterface,
    val view: MedicalHistoryContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : MedicalHistoryContract.Presenter{

    var compositeDisposable = CompositeDisposable()

    override fun fetchMedicalHistoryData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        fetchMedicalDiseasesList()
        fetchSurgeryPerformedList()
        fetchMedicalAllergyList()
        fetchHabitHistory()
    }

    override fun fetchHabitHistory() {
        val typeList = ArrayList<TypeHabit>()
        var habit = TypeHabit()
        habit.id = 1
        habit.name = "Smoker"
        typeList.add(habit)

        habit = TypeHabit()
        habit.id = 2
        habit.name = "Hard Drinker"
        typeList.add(habit)

        view.setHabitType(typeList)
    }

    override fun fetchMedicalAllergyList() {
        val typeList = ArrayList<TypeAllergy>()
        var allergy = TypeAllergy()
        allergy.id = 1
        allergy.name = "Drug Allergy"
        typeList.add(allergy)

        allergy = TypeAllergy()
        allergy.id = 2
        allergy.name = "Mold Allergy"
        typeList.add(allergy)

        allergy = TypeAllergy()
        allergy.id = 3
        allergy.name = "latex Allergy"
        typeList.add(allergy)

        allergy = TypeAllergy()
        allergy.id = 4
        allergy.name = "Food Allergy"
        typeList.add(allergy)

        view.setAllergyType(typeList)
    }

    override fun fetchMedicalDiseasesList() {
        val typeList = ArrayList<TypeDiseases>()
        var diseasesType = TypeDiseases()
        diseasesType.id = 1
        diseasesType.name = "Heart Disease"
        typeList.add(diseasesType)

        diseasesType = TypeDiseases()
        diseasesType.id = 2
        diseasesType.name = "Heart Stroke"
        typeList.add(diseasesType)

        diseasesType = TypeDiseases()
        diseasesType.id = 3
        diseasesType.name = "Acne"
        typeList.add(diseasesType)

        diseasesType = TypeDiseases()
        diseasesType.id = 4
        diseasesType.name = "Dermatitis"
        typeList.add(diseasesType)

        view.setDiseaseType(typeList)
    }

    override fun fetchSurgeryPerformedList() {
        val typeList = ArrayList<TypeSurgery>()
        var surgery = TypeSurgery()
        surgery.id = 1
        surgery.name = "Biventricular Pacemaker"
        typeList.add(surgery)

        surgery = TypeSurgery()
        surgery.id = 2
        surgery.name = "Balloon Endoscopy"
        typeList.add(surgery)

        surgery = TypeSurgery()
        surgery.id = 3
        surgery.name = "Allergy Shots"
        typeList.add(surgery)

        surgery = TypeSurgery()
        surgery.id = 4
        surgery.name = "Skin Tests"
        typeList.add(surgery)

        view.setSurgeryType(typeList)
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }


}