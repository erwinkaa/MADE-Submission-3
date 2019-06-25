package id.erwinka.madesubmission3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

    var BASE_URL: String = "https://api.themoviedb.org/3/"
    var API_KEY: String = "11a80a36b6fe99bfe6fe1322d3cc95b5"
    var BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/original"

    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}