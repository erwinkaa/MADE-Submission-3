package id.erwinka.madesubmission3.main.film

interface FilmView {
    fun showLoading()
    fun hideLoading()
    fun processMoviesData(datas: MoviesResponseModel)
    fun processTVShowsData(datas: TVShowsResponseModel)
}