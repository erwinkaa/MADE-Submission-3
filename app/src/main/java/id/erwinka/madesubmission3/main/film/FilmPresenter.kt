package id.erwinka.madesubmission3.main.film

import android.util.Log
import id.erwinka.madesubmission3.api.ApiRepository
import id.erwinka.madesubmission3.api.ApiService
import id.erwinka.madesubmission3.util.LOG_TAG
import id.erwinka.madesubmission3.util.getLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmPresenter(
    private val view: FilmView,
    private val apiService: ApiService
) {
    fun loadMovies() {
        view.showLoading()
        apiService.loadMovies(ApiRepository.API_KEY, getLocale()).enqueue(object : Callback<MoviesResponseModel> {
            override fun onResponse(call: Call<MoviesResponseModel>, response: Response<MoviesResponseModel>) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    view.processMoviesData(data)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<MoviesResponseModel>, error: Throwable) {
                Log.e(LOG_TAG, "${error.message}")
                view.hideLoading()
            }
        })
    }

    fun loadTVShows() {
        view.showLoading()
        apiService.loadTVShows(ApiRepository.API_KEY, getLocale()).enqueue(object : Callback<TVShowsResponseModel> {
            override fun onResponse(call: Call<TVShowsResponseModel>, response: Response<TVShowsResponseModel>) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    view.processTVShowsData(data)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<TVShowsResponseModel>, error: Throwable) {
                Log.e(LOG_TAG, "${error.message}")
                view.hideLoading()
            }
        })
    }
}