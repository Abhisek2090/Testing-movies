package com.embibe.tmdb.movies;

import android.app.Application;

import timber.log.Timber;

/**
 * @author Abhisek
*/
public class MoviesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
