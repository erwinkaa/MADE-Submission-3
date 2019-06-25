package id.erwinka.madesubmission3.main.detail

import android.util.Log
import id.erwinka.madesubmission3.api.ApiRepository
import id.erwinka.madesubmission3.api.ApiService
import id.erwinka.madesubmission3.util.LOG_TAG
import id.erwinka.madesubmission3.util.getLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFilmPresenter(
    private val view: DetailFilmView,
    private val apiService: ApiService
) {
    fun loadMovieDetail(id: String) {
        view.showLoading()
        apiService.loadMovieDetail(id, ApiRepository.API_KEY, getLocale()).enqueue(object : Callback<DetailMovieModel> {
            override fun onResponse(call: Call<DetailMovieModel>, response: Response<DetailMovieModel>) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    view.processMovieData(data)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<DetailMovieModel>, error: Throwable) {
                Log.e(LOG_TAG, "${error.message}")
                view.hideLoading()
            }
        })
    }

    fun loadTVShowDetail(id: String) {
        view.showLoading()
        apiService.loadTVShowDetail(id, ApiRepository.API_KEY, getLocale()).enqueue(object : Callback<DetailTVShowModel> {
            override fun onResponse(call: Call<DetailTVShowModel>, response: Response<DetailTVShowModel>) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    view.processTVShowData(data)
                }
                view.hideLoading()
            }

            override fun onFailure(call: Call<DetailTVShowModel>, error: Throwable) {
                Log.e(LOG_TAG, "${error.message}")
                view.hideLoading()
            }
        })
    }
}