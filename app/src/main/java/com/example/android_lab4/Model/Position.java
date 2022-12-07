package com.example.android_lab4.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "position")
public class Position {

    @PrimaryKey
    @ColumnInfo(name = "position_id")
    public long position_id;

    @ColumnInfo(name = "latitude")
    private int latitude;

    @ColumnInfo(name = "longitude")
    private int longitude;

    public long getId() {
        return position_id;
    }

    public void setId(long id) {
        this.position_id = id;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

}
