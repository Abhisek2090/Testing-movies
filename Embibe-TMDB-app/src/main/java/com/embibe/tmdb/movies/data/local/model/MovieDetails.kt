package com.embibe.tmdb.movies.data.local.model

import java.util.ArrayList

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class is used to load full movie details including (Trailers, Cast, etc)
 *
 * @author Abhisek
*/
data class MovieDetails (

    @Embedded
    var movie: Movie? = null

//    @Relation(parentColumn = "id", entityColumn = "movie_id")
//    var trailers: List<Trailer> = ArrayList(),
//
//    @Relation(parentColumn = "id", entityColumn = "movie_id")
//    var castList: List<Cast> = ArrayList(),
//
//    @Relation(parentColumn = "id", entityColumn = "movie_id")
//    var reviews: List<Review> = ArrayList()
)
