package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory

import `in`.co.akgroups.apnaClinic.R
import `in`.co.akgroups.apnaClinic.custom.ItemOffsetDecoration
import `in`.co.akgroups.apnaClinic.data.Injection
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters.AllergyAdapter
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters.DiseaseAdapter
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters.HabitAdapter
import `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory.adapters.SurgeryAdapter
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Utils
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.layout_medical_history.*
import kotlinx.android.synthetic.main.toolbar.*

class MedicalHistoryActivity : AppCompatActivity(), MedicalHistoryContract.View, View.OnClickListener{
    override lateinit var presenter: MedicalHistoryContract.Presenter
    lateinit var mUserData: UserData

    private lateinit var selectedDiseasesSet: HashSet<TypeDiseases>
    private lateinit var selectedSurgerySet: HashSet<TypeSurgery>
    private lateinit var selectedAllergySet: HashSet<TypeAllergy>
    private lateinit var selectedHabitSet: HashSet<TypeHabit>

    private lateinit var mDiseasesList: List<TypeDiseases>
    private lateinit var mSurgeryList: List<TypeSurgery>
    private lateinit var mAllergyList: List<TypeAllergy>
    private lateinit var mHabitList: List<TypeHabit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_medical_history)

        val data = intent
        val bundle = data.getBundleExtra(Const.ACTION_DATA)
        mUserData = bundle.getParcelable(Const.KEY_USER_DATA)
        presenter = MedicalHistoryPresenter(
            Injection.provideDataRepository(this),
            this,
            mUserData,
            AppPreferenceHelper.getInstance(applicationContext!!)
        )
        setToolbar()
        setClickListener()
        presenter.start()
    }

    override fun setToolbar() {
        img_left.visibility = View.VISIBLE
        img_right.visibility = View.GONE
        txt_title.visibility = View.VISIBLE
        img_filter.visibility = View.GONE

        img_left.setImageResource(R.drawable.ic_back)
        txt_title.text = getString(R.string.medical_history)

        DrawableCompat.setTint(img_left.drawable, ContextCompat.getColor(applicationContext, R.color.colorPrimary))
    }

    private fun setClickListener(){
        img_left.setOnClickListener(this)
    }

    override fun setDiseaseType(list: List<TypeDiseases>) {
        this.mDiseasesList = list
        if(list.isNotEmpty()){
            if(!::selectedDiseasesSet.isInitialized){
                selectedDiseasesSet = HashSet()
            }
            var position = 0
            for(typeDisease in list){
                if(selectedDiseasesSet.contains(typeDisease)){
                    typeDisease.isSelected = true
                }
                position++
            }
            rv_disease_history.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            if(rv_disease_history.itemDecorationCount == 0){
                val itemDecoration = ItemOffsetDecoration(applicationContext, R.dimen.grid_item_spacing_for_filter)
                rv_disease_history.addItemDecoration(itemDecoration)
            }
            rv_disease_history.adapter = DiseaseAdapter(applicationContext,list, object : DiseaseAdapter.ClickListener{
                override fun onClick(type: TypeDiseases) {
                    if(selectedDiseasesSet.contains(type)){
                        selectedDiseasesSet.remove(type)
                        type.isSelected = false
                    } else {
                        selectedDiseasesSet.add(type)
                        type.isSelected = true
                    }
                }
            })
        }
    }

    override fun setSurgeryType(surgeryList: List<TypeSurgery>) {
        this.mSurgeryList = surgeryList

        if(surgeryList.isNotEmpty()){
            if(!::selectedSurgerySet.isInitialized){
                selectedSurgerySet = HashSet()
            }
            var position = 0
            for(typeDisease in surgeryList){
                if(selectedSurgerySet.contains(typeDisease)){
                    typeDisease.isSelected = true
                }
                position++
            }
            rv_surgery_history.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            if(rv_surgery_history.itemDecorationCount == 0){
                val itemDecoration = ItemOffsetDecoration(applicationContext, R.dimen.grid_item_spacing_for_filter)
                rv_surgery_history.addItemDecoration(itemDecoration)
            }
            rv_surgery_history.adapter = SurgeryAdapter(applicationContext,surgeryList, object : SurgeryAdapter.ClickListener{
                override fun onClick(type: TypeSurgery) {
                    if(selectedSurgerySet.contains(type)){
                        selectedSurgerySet.remove(type)
                        type.isSelected = false
                    } else {
                        selectedSurgerySet.add(type)
                        type.isSelected = true
                    }
                }
            })
        }
    }

    override fun setAllergyType(allergyList: List<TypeAllergy>) {
        this.mAllergyList = allergyList

        if(allergyList.isNotEmpty()){
            if(!::selectedAllergySet.isInitialized){
                selectedAllergySet = HashSet()
            }
            var position = 0
            for(typeDisease in allergyList){
                if(selectedAllergySet.contains(typeDisease)){
                    typeDisease.isSelected = true
                }
                position++
            }
            rv_medical_allergy.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            if(rv_medical_allergy.itemDecorationCount == 0){
                val itemDecoration = ItemOffsetDecoration(applicationContext, R.dimen.grid_item_spacing_for_filter)
                rv_medical_allergy.addItemDecoration(itemDecoration)
            }
            rv_medical_allergy.adapter = AllergyAdapter(applicationContext,allergyList, object : AllergyAdapter.ClickListener{
                override fun onClick(type: TypeAllergy) {
                    if(selectedAllergySet.contains(type)){
                        selectedAllergySet.remove(type)
                        type.isSelected = false
                    } else {
                        selectedAllergySet.add(type)
                        type.isSelected = true
                    }
                }
            })
        }
    }

    override fun setHabitType(habitList: List<TypeHabit>) {
        this.mHabitList = habitList

        if(habitList.isNotEmpty()){
            if(!::selectedHabitSet.isInitialized){
                selectedHabitSet = HashSet()
            }
            var position = 0
            for(typeDisease in habitList){
                if(selectedHabitSet.contains(typeDisease)){
                    typeDisease.isSelected = true
                }
                position++
            }
            rv_habit_history.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            if(rv_habit_history.itemDecorationCount == 0){
                val itemDecoration = ItemOffsetDecoration(applicationContext, R.dimen.grid_item_spacing_for_filter)
                rv_habit_history.addItemDecoration(itemDecoration)
            }
            rv_habit_history.adapter = HabitAdapter(applicationContext,habitList, object : HabitAdapter.ClickListener{
                override fun onClick(type: TypeHabit) {
                    if(selectedHabitSet.contains(type)){
                        selectedHabitSet.remove(type)
                        type.isSelected = false
                    } else {
                        selectedHabitSet.add(type)
                        type.isSelected = true
                    }
                }
            })
        }
    }


    override fun isConnectedToInternet(): Boolean {
        return Utils.isOnline(applicationContext)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.img_left -> {
                onBackPressed()
            }
        }
    }
}