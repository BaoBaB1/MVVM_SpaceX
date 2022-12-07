package com.example.android_lab4.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_lab4.API.IRetrofit;
import com.example.android_lab4.API.SpaceXShipDAO;
import com.example.android_lab4.Model.SpaceXShip;
import com.example.android_lab4.Model.SpaceXShipDatabase;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

// This repository will interact with the remote data source and local Room DB
public class SpaceXShipRepository {

    private final String TAG = "SpaceXShipRepository";
    private final String BASE_URL = "https://api.spacexdata.com/";
    // this is the data that we will fetch asynchronously
    private final MutableLiveData<List<SpaceXShip>> shipsList;
    private final IRetrofit api;

    private final Application application;
    private final SpaceXShipDAO spaceXShipDAO;

    public SpaceXShipRepository(Application application) {
        this.application = application;
        shipsList = new MutableLiveData<>();
        // create singleton DB
        SpaceXShipDatabase database = SpaceXShipDatabase.getInstance(this.application);
        spaceXShipDAO = database.spaceXShipDAO();

        // create HTTP client for request
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        api = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IRetrofit.class);
    }

    // this method called when user presses "Search" button in main window
    public void searchSpaceXShips() {
        Log.d(TAG, "searchSpaceXShips");
        Toast toast = new Toast(this.application.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        // perform GET request
        api.getAllSpaceXShips().enqueue(new Callback<List<SpaceXShip>>() {
            @Override
            public void onResponse(@NonNull Call<List<SpaceXShip>> call, @NonNull Response<List<SpaceXShip>> response) {
                Log.d(TAG, "onResponse\n");
                if (response.body() != null) {
                    // convert JSON response as list of SpaceXShip objects
                    shipsList.setValue(response.body());
                    List<SpaceXShip> ships = shipsList.getValue();
                    if (ships == null) {
                        Log.d(TAG, "shipsList.getValue() == null");
                        return;
                    }
                    // any operation with DB should be in other thread and async
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            boolean isInserted = false;
                            for (SpaceXShip ship : ships) {
                                // if in local DB no such record
                                if (spaceXShipDAO.getShipByName(ship.getShip_name()) == null) {
                                    Log.d(TAG, "Inserting to local DB " + ship.getShip_name());
                                    isInserted = true;
                                    insert(ship);
                                } else {
                                    update(ship);
                                }
                            }
                            if (isInserted) {
                                toast.setText("New rows are inserted into ROOM DB");
                            } else {
                                toast.setText("Everything is up to date");
                            }
                            toast.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SpaceXShip>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure\n" + t);
                toast.setText("Error occured when sending request to server");
                // try to load data from local DB
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        shipsList.postValue(spaceXShipDAO.getAllShips());
                        toast.setText("Error occured when sending request to server. " +
                                "Retrieving data from local Room DB.");
                        toast.show();
                    }
                });
            }
        });
    }

    public LiveData<List<SpaceXShip>> getShips() {
        return shipsList;
    }

    // async methods for DB operations

    public void insert(SpaceXShip ship) {
        new InsertShipAsyncTask(spaceXShipDAO).execute(ship);
    }

    public void update(SpaceXShip ship) {
        new UpdateShipAsyncTask(spaceXShipDAO).execute(ship);
    }

    public void delete(SpaceXShip ship) {
        new DeleteShipAsyncTask(spaceXShipDAO).execute(ship);
    }

    //static : doesnt have reference to the repo itself otherwise it could cause memory leak!
    private static class InsertShipAsyncTask extends AsyncTask<SpaceXShip, Void, Void> {
        private SpaceXShipDAO spaceXShipDAO;

        private InsertShipAsyncTask(SpaceXShipDAO spaceXShipDAO) {
            this.spaceXShipDAO = spaceXShipDAO;
        }

        @Override
        protected Void doInBackground(SpaceXShip... ships) { // ...  is similar to array
            spaceXShipDAO.insert(ships[0]); //single note
            return null;
        }
    }

    private static class UpdateShipAsyncTask extends AsyncTask<SpaceXShip, Void, Void> {
        private SpaceXShipDAO spaceXShipDAO;

        private UpdateShipAsyncTask(SpaceXShipDAO spaceXShipDAO) {
            this.spaceXShipDAO = spaceXShipDAO;
        }

        @Override
        protected Void doInBackground(SpaceXShip... ships) { // ...  is similar to array
            spaceXShipDAO.update(ships[0]); //single note
            return null;
        }
    }

    private static class DeleteShipAsyncTask extends AsyncTask<SpaceXShip, Void, Void> {
        private SpaceXShipDAO spaceXShipDAO;

        private DeleteShipAsyncTask(SpaceXShipDAO spaceXShipDAO) {
            this.spaceXShipDAO = spaceXShipDAO;
        }

        @Override
        protected Void doInBackground(SpaceXShip... ships) { // ...  is similar to array
            spaceXShipDAO.delete(ships[0]); //single note
            return null;
        }
    }
}
