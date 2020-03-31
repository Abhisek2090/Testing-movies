package com.embibe.tmdb.movies.ui.movieslist;
import android.os.Bundle;
import android.view.MenuItem;

import com.embibe.tmdb.movies.R;
import com.embibe.tmdb.movies.ui.movieslist.list.ListMoviesFragment;
import com.embibe.tmdb.movies.ui.movieslist.favorites.FavoritesFragment;
import com.embibe.tmdb.movies.ui.movieslist.search.SearchMoviesFragment;
import com.embibe.tmdb.movies.utils.ActivityUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
public class MoviesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewFragment();
//        if (savedInstanceState == null) {
//            Intent intent = new Intent(this, LoadingActivity.class);
//            startActivity(intent);
//
//        }
        setupToolbar();
        setupBottomNavigation();
    }
    private void setupViewFragment() {
        // show discover movies fragment by default
        ListMoviesFragment discoverMoviesFragment = ListMoviesFragment.newInstance();
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(), discoverMoviesFragment, R.id.fragment_container);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_discover:
                        ActivityUtils.replaceFragmentInActivity(
                                getSupportFragmentManager(), ListMoviesFragment.newInstance(),
                                R.id.fragment_container);
                        return true;
                    case R.id.action_favorites:
                        ActivityUtils.replaceFragmentInActivity(
                                getSupportFragmentManager(), FavoritesFragment.newInstance(),
                                R.id.fragment_container);
                        return true;

                    case R.id.action_search:
                        ActivityUtils.replaceFragmentInActivity(
                                getSupportFragmentManager(), SearchMoviesFragment.newInstance(),
                                R.id.fragment_container);
                        return true;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
