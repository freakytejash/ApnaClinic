package `in`.co.akgroups.apnaClinic.data.source.remote.requests

/**
 * Created by amitacharya on 3/2/20.
 */
class ChangePasswordCurRequest {

    var type: Int
    var Id: Long
    var currentPassword: String
    var newPassword: String

    constructor(id: Long, type: Int, currentPassword: String, newPassword: String) {
        this.type = type
        this.Id = id
        this.currentPassword = currentPassword
        this.newPassword = newPassword
    }
}