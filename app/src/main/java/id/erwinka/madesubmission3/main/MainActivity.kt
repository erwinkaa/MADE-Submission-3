package id.erwinka.madesubmission3.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import id.erwinka.madesubmission3.R
import id.erwinka.madesubmission3.main.film.FilmFragment
import id.erwinka.madesubmission3.util.LOG_TAG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DATA_EXTRA = "data"
        const val BUNDLE_EXTRA = "bundle"
        const val MOVIE = "movie"
        const val TVSHOW = "tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.movies -> {
                    loadFilmFragment(savedInstanceState, MOVIE)
                }
                R.id.tvshows -> {
                    loadFilmFragment(savedInstanceState, TVSHOW)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.movies
    }

    private fun loadFilmFragment(savedInstanceState: Bundle?, type: String) {
        if (savedInstanceState == null) {
            val filmFragment = FilmFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_EXTRA, type)
            filmFragment.arguments = bundle

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFrame, filmFragment, FilmFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_localization -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
