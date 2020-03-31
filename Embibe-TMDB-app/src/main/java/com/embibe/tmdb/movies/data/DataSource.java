package com.embibe.tmdb.movies.data;

import com.embibe.tmdb.movies.data.local.model.Movie;
import com.embibe.tmdb.movies.data.local.model.MovieDetails;
import com.embibe.tmdb.movies.data.local.model.RepoMoviesResult;
import com.embibe.tmdb.movies.data.local.model.Resource;
import com.embibe.tmdb.movies.ui.movieslist.MoviesFilterType;


import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author Abhisek.
 */
public interface DataSource {

    LiveData<Resource<MovieDetails>> loadMovie(long movieId);

    RepoMoviesResult loadMoviesFilteredBy(MoviesFilterType sortBy);

    RepoMoviesResult loadMoviesSearchBy(String searchBy);

    LiveData<List<Movie>> getAllFavoriteMovies();

    void favoriteMovie(Movie movie);

    void unfavoriteMovie(Movie movie);
}
