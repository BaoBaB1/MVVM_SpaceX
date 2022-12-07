package com.example.android_lab4.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "SpaceXShip")
public class SpaceXShip implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true)

    @PrimaryKey(autoGenerate = true)
    private long digit_ship_id;

    @ColumnInfo(name = "ship_id")
    private String ship_id;

    @ColumnInfo(name = "ship_name")
    private String ship_name;

    @ColumnInfo(name = "ship_model")
    private String ship_model;

    @ColumnInfo(name = "ship_type")
    private String ship_type;

    // converter for this exists
    private List<String> roles;

    @ColumnInfo(name = "active")
    private boolean active;

    @ColumnInfo(name = "imo")
    private int imo;

    @ColumnInfo(name = "mmsi")
    private int mmsi;

    @ColumnInfo(name = "abs")
    private int abs;

    @ColumnInfo(name = "class")
    @JsonProperty("class")
    private int _class;

    @ColumnInfo(name = "weight_lbs")
    private int weight_lbs;

    @ColumnInfo(name = "weight_kg")
    private int weight_kg;

    @ColumnInfo(name = "year_built")
    private int year_built;

    @ColumnInfo(name = "home_port")
    private String home_port;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "speed_kn")
    private int speed_kn;

    @ColumnInfo(name = "course_deg")
    private int course_deg;

    // converter for this exists
    private Position position;

    @ColumnInfo(name = "successful_landings")
    private int successful_landings;

    @ColumnInfo(name = "attempted_landings")
    private int attempted_landings;

    // converter for this exists
    private List<Mission> missions;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "image_url")
    private String image;

    public long getDigit_ship_id() {
        return digit_ship_id;
    }

    public void setDigit_ship_id(long digit_ship_id) {
        this.digit_ship_id = digit_ship_id;
    }

    public int getSuccessful_landings() {
        return successful_landings;
    }

    public void setSuccessful_landings(int successful_landings) {
        this.successful_landings = successful_landings;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public int getAttempted_landings() {
        return attempted_landings;
    }

    public void setAttempted_landings(int attempted_landings) {
        this.attempted_landings = attempted_landings;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() { return roles; }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageURL() {
        return image;
    }

    public void setImageURL(String image) {
        this.image = image;
    }

    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_model() {
        return ship_model;
    }

    public void setShip_model(String ship_model) {
        this.ship_model = ship_model;
    }

    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getImo() {
        return imo;
    }

    public void setImo(int imo) {
        this.imo = imo;
    }

    public int getMmsi() {
        return mmsi;
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public int getAbs() {
        return abs;
    }

    public void setAbs(int abs) {
        this.abs = abs;
    }

    public int get_class() {
        return _class;
    }

    public void set_class(int _class) {
        this._class = _class;
    }

    public int getWeight_lbs() {
        return weight_lbs;
    }

    public void setWeight_lbs(int weight_lbs) {
        this.weight_lbs = weight_lbs;
    }

    public int getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(int weight_kg) {
        this.weight_kg = weight_kg;
    }

    public int getYear_built() {
        return year_built;
    }

    public void setYear_built(int year_built) {
        this.year_built = year_built;
    }

    public String getHome_port() {
        return home_port;
    }

    public void setHome_port(String home_port) {
        this.home_port = home_port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSpeed_kn() {
        return speed_kn;
    }

    public void setSpeed_kn(int speed_kn) {
        this.speed_kn = speed_kn;
    }

    public int getCourse_deg() {
        return course_deg;
    }

    public void setCourse_deg(int course_deg) {
        this.course_deg = course_deg;
    }
}
