package ru.senkondr.hw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.senkondr.hw2.api.TMDB
import ru.senkondr.hw2.api.TMDBInterface
import ru.senkondr.hw2.databinding.ActivityMainBinding
import ru.senkondr.hw2.databinding.ActivitySingleMovieBinding
import ru.senkondr.hw2.popular_movie.MoviePagedListAdapter
import ru.senkondr.hw2.popular_movie.MovieRepository
import ru.senkondr.hw2.popular_movie.MovieViewModel
import ru.senkondr.hw2.repository.NetworkState
import ru.senkondr.hw2.single_movie.SingleMovie

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel

    lateinit var movieRepository: MovieRepository
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : TMDBInterface = TMDB.getClient()

        movieRepository = MovieRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = MoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE займет треть
                else return 3                                              // NETWORK_VIEW_TYPE - целиком
            }
        };

        findViewById<RecyclerView>(R.id.rv_movie_list).layoutManager = gridLayoutManager
        findViewById<RecyclerView>(R.id.rv_movie_list).setHasFixedSize(true)
        findViewById<RecyclerView>(R.id.rv_movie_list).adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progress_bar_popular).visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            findViewById<TextView>(R.id.txt_error_popular).visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })

    }


    private fun getViewModel(): MovieViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(movieRepository) as T
            }
        })[MovieViewModel::class.java]
    }


}


