package com.example.showspotter.data.api

import com.example.showspotter.data.model.TitleDetailsResponse
import com.example.showspotter.data.model.TitleListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TitleApi {
    @GET("list-titles")
    fun getMovies(
        @Query("types") type: String = "movie"
    ): Single<TitleListResponse>

    @GET("list-titles")
    fun getTVShows(
        @Query("types") type: String = "tv_series"
    ): Single<TitleListResponse>

    @GET("title/{id}/details")
    fun getTitleDetails(@Path("id") id: String): Single<TitleDetailsResponse>

}
