package com.structure.kotlin.utills

object ApiUtility {

    /** The base URL of the API */
    const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    const val LOGIN_SAMPLE_URL: String = "https://reqres.in/"

    var GET_POSTS = BASE_URL + "posts"
    var LOGIN = LOGIN_SAMPLE_URL + "api/login"


    val EMAIL = "email"
    val PASSWORD = "password"
    val DEVICE_TYPE = "device_type"
    val DEVICE_TOKEN = "device_token"


    /*https://reqres.in/api/users?page=4*/

}