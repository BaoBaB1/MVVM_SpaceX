package com.example.android_lab4.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_lab4.Model.SpaceXShip;
import com.example.android_lab4.Repository.SpaceXShipRepository;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private SpaceXShipRepository repo;
    private LiveData<List<SpaceXShip>> ships;

    public AppViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repo = new SpaceXShipRepository(getApplication());
        ships = repo.getShips();
    }

    public void insert(SpaceXShip ship) {
        repo.insert(ship);
    }
    public void update(SpaceXShip ship) {
        repo.update(ship);
    }
    public void delete(SpaceXShip ship) {
        repo.delete(ship);
    }

    public void searchShips() {
        repo.searchSpaceXShips();
    }

    public LiveData<List<SpaceXShip>> getShips() {
        return ships;
    }

}
