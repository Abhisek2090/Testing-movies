package com.embibe.tmdb.movies.data.local;


import com.embibe.tmdb.movies.data.local.model.Movie;
import com.embibe.tmdb.movies.data.local.model.MovieDetails;
import com.embibe.tmdb.movies.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;
import timber.log.Timber;

/**
 * @author Abhisek.
 */
public class MoviesLocalDataSource {

    private static volatile MoviesLocalDataSource sInstance;

    private final MoviesDatabase mDatabase;

    private MoviesLocalDataSource(MoviesDatabase database) {
        mDatabase = database;
    }

    public static MoviesLocalDataSource getInstance(MoviesDatabase database) {
        if (sInstance == null) {
            synchronized (AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new MoviesLocalDataSource(database);
                }
            }
        }
        return sInstance;
    }

    public void saveMovie(Movie movie) {
        mDatabase.moviesDao().insertMovie(movie);
    }

    public LiveData<MovieDetails> getMovie(long movieId) {
        Timber.d("Loading movie details.");
        return mDatabase.moviesDao().getMovie(movieId);
    }

    public LiveData<List<Movie>> getAllFavoriteMovies() {
        return mDatabase.moviesDao().getAllFavoriteMovies();
    }

    public void favoriteMovie(Movie movie) {
        mDatabase.moviesDao().favoriteMovie(movie.getId());
    }

    public void unfavoriteMovie(Movie movie) {
        mDatabase.moviesDao().unFavoriteMovie(movie.getId());
    }
}
