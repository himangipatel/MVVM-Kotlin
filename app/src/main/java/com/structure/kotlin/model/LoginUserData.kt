package com.structure.kotlin.model

import com.squareup.moshi.Json


/**
 * Created by Himangi on 7/6/18
 */
class LoginUserData {

    var userId: String? = null
    var userFirstName: String = ""
    var userLastName: String = ""
    @Json(name = "email")
    var userEmail: String = ""
    var password: String = ""
    var token: String = ""
    var error: String = ""

}
