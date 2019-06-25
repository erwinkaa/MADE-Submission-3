package id.erwinka.madesubmission3.main.film

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import id.erwinka.madesubmission3.R
import id.erwinka.madesubmission3.api.ApiRepository
import id.erwinka.madesubmission3.main.MainActivity
import id.erwinka.madesubmission3.main.detail.DetailFilmActivity
import id.erwinka.madesubmission3.util.invisible
import id.erwinka.madesubmission3.util.visible
import org.jetbrains.anko.support.v4.startActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import id.erwinka.madesubmission3.util.LOG_TAG

class FilmFragment : Fragment(), FilmView {

    private lateinit var presenter: FilmPresenter
    private lateinit var adapterMovies: MoviesAdapter
    private lateinit var adapterTVShows: TVShowsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var dataMovies = mutableListOf<MoviesModel>()
    private var dataTVShows = mutableListOf<TVShowsModel>()
    private lateinit var viewModel: FilmViewModel

    private var fragment_type: String? = "fragment type"

    companion object {
        const val TYPE = "type"
        const val INSTANCE = "instance"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerview)
        progressBar = view.findViewById(R.id.progress_circular)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(FilmViewModel::class.java)
        viewModel.getMovies().observe(this, getMovies)
        viewModel.getTVShows().observe(this, getTVShows)

        val service = ApiRepository.create()
        presenter = FilmPresenter(this, service)

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapterMovies = MoviesAdapter(requireContext(), dataMovies) {
            startActivity<DetailFilmActivity>(MainActivity.DATA_EXTRA to it.id, TYPE to MainActivity.MOVIE)
        }
        adapterTVShows = TVShowsAdapter(requireContext(), dataTVShows) {
            startActivity<DetailFilmActivity>(MainActivity.DATA_EXTRA to it.id, TYPE to MainActivity.TVSHOW)
        }

        fragment_type = arguments?.getString(MainActivity.BUNDLE_EXTRA)
        if (fragment_type.equals(MainActivity.MOVIE)) {
            recyclerView.adapter = adapterMovies
            if (savedInstanceState == null) {
                presenter.loadMovies()
            } else {
                progressBar.invisible()
            }
        } else if (fragment_type.equals(MainActivity.TVSHOW)) {
            recyclerView.adapter = adapterTVShows
            if (savedInstanceState == null) {
                presenter.loadTVShows()
            } else {
                progressBar.invisible()
            }
        }

        super.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE, fragment_type)
        super.onSaveInstanceState(outState)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun processMoviesData(datas: MoviesResponseModel) {
        viewModel.setMovies(datas)
    }

    override fun processTVShowsData(datas: TVShowsResponseModel) {
        viewModel.setTVShows(datas)
    }

    private val getMovies = Observer<MoviesResponseModel> {
        if (it != null) {
            adapterMovies.setData(it.results)
        }
    }

    private val getTVShows = Observer<TVShowsResponseModel> {
        if (it != null) {
            adapterTVShows.setData(it.results)
        }
    }
}
