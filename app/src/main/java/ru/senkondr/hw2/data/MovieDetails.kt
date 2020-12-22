package ru.senkondr.hw2.data

import com.google.gson.annotations.SerializedName

// класс для хранения данных, сеттеры и геттеры доступны по умолчанию
// в таком виде приходят данные об ОДНОМ конкретном фильме
data class MovieDetails(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") // аналогично JsonProperty("property name")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double
)