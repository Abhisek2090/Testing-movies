package com.embibe.tmdb.movies.ui.movieslist.search;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.embibe.tmdb.movies.data.MovieRepository;
import com.embibe.tmdb.movies.data.local.model.Movie;
import com.embibe.tmdb.movies.data.local.model.RepoMoviesResult;
import com.embibe.tmdb.movies.data.local.model.Resource;

/**
 * @author Abhisek.
 */
public class SearchMoviesViewModel extends ViewModel {

    private LiveData<RepoMoviesResult> repoMoviesResult;

    private LiveData<PagedList<Movie>> pagedList;

    private LiveData<Resource> networkState;

    private MutableLiveData<Integer> currentTitle = new MutableLiveData<>();

    private MutableLiveData<String> searchBy = new MutableLiveData<>();

    public SearchMoviesViewModel(final MovieRepository movieRepository) {


        repoMoviesResult = Transformations.map(searchBy, new Function<String, RepoMoviesResult>() {
            @Override
            public RepoMoviesResult apply(String sort) {
                return movieRepository.loadMoviesSearchBy(sort);
            }
        });
        pagedList = Transformations.switchMap(repoMoviesResult,
                new Function<RepoMoviesResult, LiveData<PagedList<Movie>>>() {
                    @Override
                    public LiveData<PagedList<Movie>> apply(RepoMoviesResult input) {
                        return input.data;
                    }
                });

        networkState = Transformations.switchMap(repoMoviesResult, new Function<RepoMoviesResult, LiveData<Resource>>() {
            @Override
            public LiveData<Resource> apply(RepoMoviesResult input) {
                return input.resource;
            }
        });
    }

    public void setSearchBy (String searchBy) {
       this.searchBy.setValue(searchBy);
    }

    public LiveData<PagedList<Movie>> getPagedList() {
        return pagedList;
    }

    public LiveData<Resource> getNetworkState() {
        return networkState;
    }


    // retry any failed requests.
    public void retry() {
        repoMoviesResult.getValue().sourceLiveData.getValue().retryCallback.invoke();
    }
}