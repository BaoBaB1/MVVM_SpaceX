package com.example.android_lab4.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.android_lab4.API.SpaceXShipDAO;

@Database(entities = {SpaceXShip.class, Mission.class, Position.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SpaceXShipDatabase extends RoomDatabase {

    private static SpaceXShipDatabase instance;

    public abstract SpaceXShipDAO spaceXShipDAO();

    public static synchronized SpaceXShipDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SpaceXShipDatabase.class ,
                            "SpaceXShipDatabase").build();
        }
        return instance;
    }
}
