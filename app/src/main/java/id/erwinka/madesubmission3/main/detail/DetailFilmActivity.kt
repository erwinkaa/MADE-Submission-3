package id.erwinka.madesubmission3.main.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.erwinka.madesubmission3.R
import id.erwinka.madesubmission3.api.ApiRepository
import id.erwinka.madesubmission3.main.MainActivity
import id.erwinka.madesubmission3.main.film.FilmFragment
import id.erwinka.madesubmission3.util.invisible
import id.erwinka.madesubmission3.util.visible
import kotlinx.android.synthetic.main.activity_detail_film.*
import kotlinx.android.synthetic.main.activity_detail_film.tv_film_title
import kotlinx.android.synthetic.main.inflater_film.*

class DetailFilmActivity : AppCompatActivity(), DetailFilmView {

    private lateinit var presenter: DetailFilmPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = resources.getString(R.string.detailfilm)

        val type = intent.getStringExtra(FilmFragment.TYPE)
        val id = intent.getStringExtra(MainActivity.DATA_EXTRA)

        val service = ApiRepository.create()
        presenter = DetailFilmPresenter(this, service)

        if (type == MainActivity.MOVIE) {
            presenter.loadMovieDetail(id)
        } else if (type == MainActivity.TVSHOW) {
            presenter.loadTVShowDetail(id)
        }
    }

    override fun showLoading() {
        progress_circular.visible()
        layout_detail.invisible()
    }

    override fun hideLoading() {
        progress_circular.invisible()
        layout_detail.visible()
    }

    override fun processMovieData(data: DetailMovieModel) {
        tv_film_title.text = data.title
        tv_film_releasedate.text = data.release_date
        tv_film_runningtime.text = data.runtime

        var companies = ""
        for (i in 0 until data.production_companies.size) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }

        tv_film_productioncompanies.text = companies
        tv_film_overview.text = data.overview
        Glide.with(this).load(ApiRepository.BASE_IMAGE_URL + data.poster_path).into(iv_film_posterdetail)
    }

    override fun processTVShowData(data: DetailTVShowModel) {
        tv_film_title.text = data.name
        tv_film_releasedate.text = data.first_air_date
        tv_film_runningtime.text = data.episode_run_time[0].toString()

        var companies = ""
        for (i in 0 until data.production_companies.size) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }

        tv_film_productioncompanies.text = companies
        tv_film_overview.text = data.overview
        Glide.with(this).load(ApiRepository.BASE_IMAGE_URL + data.poster_path).into(iv_film_posterdetail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
