package com.example.android_lab4.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(tableName = "mission")
public class Mission {

    @JsonIgnoreProperties(ignoreUnknown=true)

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mission_id")
    private long mission_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "flight")
    private int flight;

    public long getMission_id() {
        return mission_id;
    }

    public void setMission_id(long mission_id) {
        this.mission_id = mission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlight() {
        return flight;
    }

    public void setFlight(int flight) {
        this.flight = flight;
    }

}
