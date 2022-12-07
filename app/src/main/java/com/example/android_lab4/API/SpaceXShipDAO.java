package com.example.android_lab4.API;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_lab4.Model.SpaceXShip;

import java.util.List;

@Dao
public interface SpaceXShipDAO {
    @Query("SELECT * FROM SpaceXShip")
    List<SpaceXShip> getAllShips();

    @Query("SELECT * FROM SpaceXShip WHERE ship_name = :ship_name")
    SpaceXShip getShipByName(String ship_name);

    @Delete
    void delete(SpaceXShip ship);

    @Insert
    void insert(SpaceXShip ship);

    @Update //(onConflict = OnConflictStrategy.REPLACE)
    void update(SpaceXShip ship);

}
