package com.embibe.tmdb.movies.data.remote.paging;

import com.embibe.tmdb.movies.data.remote.api.MovieService;
import com.embibe.tmdb.movies.data.local.model.Movie;
import com.embibe.tmdb.movies.ui.movieslist.MoviesFilterType;

import java.util.concurrent.Executor;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * A simple data source factory provides a way to observe the last created data source.
 *
 * @author Abhisek.
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    public MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();

    private final MovieService movieService;
    private final Executor networkExecutor;
    private final MoviesFilterType sortBy;
    private String searchBy = null;

    public MovieDataSourceFactory(MovieService movieService,
                                  Executor networkExecutor, MoviesFilterType sortBy, String searchBy) {
        this.movieService = movieService;
        this.sortBy = sortBy;
        this.networkExecutor = networkExecutor;
        this.searchBy = searchBy;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MoviePageKeyedDataSource movieDataSource =
                new MoviePageKeyedDataSource(movieService, networkExecutor, sortBy, searchBy);
        sourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
