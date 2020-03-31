package com.embibe.tmdb.movies.ui.movieslist.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.embibe.tmdb.movies.R;
import com.embibe.tmdb.movies.data.local.model.Movie;
import com.embibe.tmdb.movies.data.local.model.Resource;
import com.embibe.tmdb.movies.databinding.FragmentFavoriteMoviesBinding;
import com.embibe.tmdb.movies.databinding.FragmentSearchMoviesBinding;
import com.embibe.tmdb.movies.ui.movieslist.MoviesActivity;
import com.embibe.tmdb.movies.utils.Injection;
import com.embibe.tmdb.movies.utils.ItemOffsetDecoration;
import com.embibe.tmdb.movies.utils.ViewModelFactory;

/**
 * @author Abhisek.
 */
public class SearchMoviesFragment extends Fragment implements TextWatcher {

    private SearchMoviesViewModel viewModel;
    private FragmentSearchMoviesBinding binding;

    public static SearchMoviesFragment newInstance() {
        return new SearchMoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.searchMoviesEditText.addTextChangedListener(this);

        ((MoviesActivity) getActivity()).getSupportActionBar().setTitle("Search Movies");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = obtainViewModel(getActivity());
        setupListAdapter();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }



    public static SearchMoviesViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = Injection.provideViewModelFactory(activity);
        return ViewModelProviders.of(activity, factory).get(SearchMoviesViewModel.class);
    }

    private void setupListAdapter() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_movie_list);
        final SearchMoviesAdapter discoverMoviesAdapter =
                new SearchMoviesAdapter(viewModel);
        final GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.span_count));

        // draw network status and errors messages to fit the whole row(3 spans)
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (discoverMoviesAdapter.getItemViewType(position)) {
                    case R.layout.item_network_state:
                        return layoutManager.getSpanCount();
                    default:
                        return 1;
                }
            }
        });

        // setup recyclerView
        recyclerView.setAdapter(discoverMoviesAdapter);
        recyclerView.setLayoutManager(layoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);

        // observe paged list
        viewModel.getPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                discoverMoviesAdapter.submitList(movies);
            }
        });

        // observe network state
        viewModel.getNetworkState().observe(getViewLifecycleOwner(), new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {
                discoverMoviesAdapter.setNetworkState(resource);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        viewModel.setSearchBy(s.toString());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
