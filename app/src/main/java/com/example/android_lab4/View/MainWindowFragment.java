package com.example.android_lab4.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_lab4.Adapters.SpaceXShipsAdapter;
import com.example.android_lab4.Model.SpaceXShip;
import com.example.android_lab4.R;
import com.example.android_lab4.ViewModel.AppViewModel;

import java.util.List;

public class MainWindowFragment extends Fragment {

    private final String TAG = "MainWindowFragment";
    private AppViewModel appViewModel;
    private SpaceXShipsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SpaceXShipsAdapter();
        adapter.setActivity(getActivity());
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
        Log.d(TAG, "Starting observing appViewModel");
        appViewModel.getShips().observe(this, new Observer<List<SpaceXShip>>() {
            @Override
            public void onChanged(List<SpaceXShip> spaceXShips) {
                if (spaceXShips != null) {
                    Log.d(TAG, "Observed appViewModel onChanged");
                    adapter.setShips(spaceXShips);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.search_layout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
        return view;
    }

    public void performSearch() {
        appViewModel.searchShips();
    }
}
