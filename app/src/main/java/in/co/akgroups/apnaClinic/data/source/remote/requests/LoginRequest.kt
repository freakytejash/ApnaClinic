package `in`.co.akgroups.apnaClinic.data.source.remote.requests

class LoginRequest {
    var email: String
    var password: String
    var userProfileRequest: UserProfileRequest
    var type = 0


    constructor(email: String, password: String, userProfileRequest: UserProfileRequest, type: Int) {
        this.email = email
        this.password = password
        this.userProfileRequest = userProfileRequest
        this.type = type
    }

    class UserProfileRequest {
        var loginType: String
        var appVersion: String

        constructor(loginType: String, appVersion: String) {
            this.loginType = loginType
            this.appVersion = appVersion
        }
    }
}