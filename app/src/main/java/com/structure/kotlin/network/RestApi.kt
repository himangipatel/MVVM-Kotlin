package com.structure.kotlin.network


import com.structure.kotlin.model.LoginUserData
import com.structure.kotlin.model.Post
import com.structure.kotlin.ui.paging.NewsModelClass
import com.structure.kotlin.ui.paging.NewsResponse
import io.reactivex.Observable
import retrofit2.http.*


/**
 * The interface which provides methods to get result of webservices
 */
interface RestApi {
    /**
     * Get the list of the pots from the API
     */
    @GET
    fun getPosts(@Url url: String): Observable<List<Post>>

    @POST
    fun authenticateUser(@Url url: String, @Body login: LoginUserData): Observable<LoginUserData>

    @GET("https://api.github.com/users")
    fun getUser(@Query("since") since: Int, @Query("per_page") perPage: Int): Observable<List<NewsModelClass>>

    @GET("https://newsapi.org/v1/articles")
    fun fetchListNews(@Query("source") source: String, @Query("apiKey") apiKey: String): Observable<NewsResponse>

}

