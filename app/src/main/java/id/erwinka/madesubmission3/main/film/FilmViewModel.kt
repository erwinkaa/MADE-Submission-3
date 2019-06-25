package id.erwinka.madesubmission3.main.film

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class FilmViewModel : ViewModel() {

    private var listMovies = MutableLiveData<MoviesResponseModel>()
    private var listTVShows = MutableLiveData<TVShowsResponseModel>()

    fun setMovies(data: MoviesResponseModel) {
        listMovies.postValue(data)
    }

    fun getMovies() : LiveData<MoviesResponseModel> {
        return listMovies
    }

    fun setTVShows(data: TVShowsResponseModel) {
        listTVShows.postValue(data)
    }

    fun getTVShows() : LiveData<TVShowsResponseModel> {
        return listTVShows
    }

}