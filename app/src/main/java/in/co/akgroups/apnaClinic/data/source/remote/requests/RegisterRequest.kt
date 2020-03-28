package `in`.co.akgroups.apnaClinic.data.source.remote.requests

class RegisterRequest {
   var first_name: String
    var last_name: String
    var email: String
    var telephone: Long
    var password: String
    var type: Int

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        mobile: Long,
        password: String,
        type: Int
    ){
        this.first_name = firstName
        this.last_name = lastName
        this.email = email
        this.telephone = mobile
        this.password = password
        this.type = type
    }
}