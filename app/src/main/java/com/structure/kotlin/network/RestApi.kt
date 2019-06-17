package com.structure.kotlin.network


import com.structure.kotlin.model.LoginUserData
import io.reactivex.Observable
import retrofit2.http.*


/**
 * The interface which provides methods to get result of webservices
 */
interface RestApi {

    @POST
    fun authenticateUser(@Url url: String, @Body login: LoginUserData): Observable<LoginUserData>

}

