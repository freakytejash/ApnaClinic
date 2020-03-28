package `in`.co.akgroups.apnaClinic.patient.profile.listener

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent

interface UpdateProfileListener {
    fun updateProfile(dependent: Dependent)
}