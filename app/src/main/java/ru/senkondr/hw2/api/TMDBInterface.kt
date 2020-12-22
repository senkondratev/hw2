package ru.senkondr.hw2.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.senkondr.hw2.data.MovieDetails
import ru.senkondr.hw2.data.MovieResponse

// Ключи для апи выдают мгновенно и бесплатно, но с регистрацией, хоть и без смс
interface TMDBInterface {
    // https://api.themoviedb.org/3/movie/popular?api_key=872c88c8c993fe0cb2a86b6baa7cdf66&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=872c88c8c993fe0cb2a86b6baa7cdf66
    // https://api.themoviedb.org/3/

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page: Int): Single<MovieResponse>
}