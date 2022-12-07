package com.example.android_lab4.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    // Converter for List<String>
   @TypeConverter
   public static List<String> stringToListString(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListStringToString(List<String> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }

    // Converter for Position
    @TypeConverter
    public static Position toPosition(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return null;
        }
        Type listType = new TypeToken<Position>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String positionToString(Position position) {
        Gson gson = new Gson();
        return gson.toJson(position);
    }

    // Converter for List<Mission>
    @TypeConverter
    public static List<Mission> storedStringToMission(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Mission>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String missionToStoredString(List<Mission> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
