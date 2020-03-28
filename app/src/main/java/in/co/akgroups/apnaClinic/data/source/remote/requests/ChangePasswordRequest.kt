package `in`.co.akgroups.apnaClinic.data.source.remote.requests

class ChangePasswordRequest {
    var id: Long
    var currentPassword: String
    var newPassword: String

    constructor(id: Long, currentPassword: String, newPassword: String){
        this.id = id
        this.currentPassword = currentPassword
        this.newPassword = newPassword
    }
}