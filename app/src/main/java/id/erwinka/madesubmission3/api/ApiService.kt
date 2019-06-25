package id.erwinka.madesubmission3.api

import id.erwinka.madesubmission3.main.detail.DetailMovieModel
import id.erwinka.madesubmission3.main.detail.DetailTVShowModel
import id.erwinka.madesubmission3.main.film.MoviesResponseModel
import id.erwinka.madesubmission3.main.film.TVShowsResponseModel
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun loadMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US"
    ): Call<MoviesResponseModel>

    @GET("discover/tv")
    fun loadTVShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US"
    ): Call<TVShowsResponseModel>

    @GET("movie/{id}")
    fun loadMovieDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US"
    ): Call<DetailMovieModel>

    @GET("tv/{id}")
    fun loadTVShowDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US"
    ): Call<DetailTVShowModel>


}