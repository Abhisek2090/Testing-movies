package com.embibe.tmdb.movies.utils;

import com.embibe.tmdb.movies.data.MovieRepository;
import com.embibe.tmdb.movies.ui.moviedetails.MovieDetailsViewModel;
import com.embibe.tmdb.movies.ui.movieslist.list.LIstMoviesViewModel;
import com.embibe.tmdb.movies.ui.movieslist.favorites.FavoritesViewModel;
import com.embibe.tmdb.movies.ui.movieslist.search.SearchMoviesViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author Abhisek.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    public static ViewModelFactory getInstance(MovieRepository repository) {
        return new ViewModelFactory(repository);
    }

    private ViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LIstMoviesViewModel.class)) {
            //noinspection unchecked
            return (T) new LIstMoviesViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoritesViewModel(repository);
        }else if (modelClass.isAssignableFrom(SearchMoviesViewModel.class)) {
            //noinspection unchecked
            return (T) new SearchMoviesViewModel(repository);
        }
        else if (modelClass.isAssignableFrom(MovieDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieDetailsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
