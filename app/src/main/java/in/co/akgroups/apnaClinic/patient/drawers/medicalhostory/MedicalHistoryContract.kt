package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface MedicalHistoryContract {

    interface View : BaseView<Presenter> {

        fun setToolbar()

        fun setDiseaseType(list: List<TypeDiseases>)

        fun setSurgeryType(surgeryList: List<TypeSurgery>)

        fun setAllergyType(allergyList: List<TypeAllergy>)

        fun setHabitType(habitList: List<TypeHabit>)
    }

    interface Presenter : BasePresenter {
        fun fetchMedicalHistoryData()

        fun fetchMedicalDiseasesList()

        fun fetchSurgeryPerformedList()

        fun fetchMedicalAllergyList()

        fun fetchHabitHistory()
    }
}