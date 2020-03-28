package `in`.co.akgroups.apnaClinic.data.source.remote.requests

/**
 * Created by narendrapal on 15/01/2020
 */

class OtpVerificationRequest {

    var Id: Long
    var type: Int
    var email_otp = -1
    var mobile_otp = -1
    var verified: Boolean
    var otp = -1

    constructor(Id: Long, type: Int, email_otp: Int, mobile_otp: Int, verified: Boolean) {
        this.Id = Id
        this.type = type
        this.email_otp = email_otp
        this.mobile_otp = mobile_otp
        this.verified = verified
    }

    constructor(Id: Long, type: Int, verified: Boolean, otp: Int){
        this.Id = Id
        this.type = type
        this.verified = verified
        this.otp = otp
    }
}