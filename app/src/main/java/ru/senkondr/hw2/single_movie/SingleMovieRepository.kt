package ru.senkondr.hw2.single_movie

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import ru.senkondr.hw2.api.TMDBInterface
import ru.senkondr.hw2.data.MovieDetails
import ru.senkondr.hw2.repository.MovieDetailsDataSource
import ru.senkondr.hw2.repository.NetworkState

class SingleMovieRepository (private val apiService : TMDBInterface){
    lateinit var movieDetailsDataSource: MovieDetailsDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsDataSource = MovieDetailsDataSource(apiService,compositeDisposable)
        movieDetailsDataSource.fetchMovieDetails(movieId)

        return movieDetailsDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsDataSource.networkState
    }

}