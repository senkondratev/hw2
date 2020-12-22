package ru.senkondr.hw2.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import ru.senkondr.hw2.api.POST_PER_PAGE
import ru.senkondr.hw2.api.TMDBInterface
import ru.senkondr.hw2.data.Movie
import ru.senkondr.hw2.repository.MovieDataSource
import ru.senkondr.hw2.repository.MovieDataSourceFactory
import ru.senkondr.hw2.repository.NetworkState

class MovieViewModel (private val movieRepository : MovieRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}