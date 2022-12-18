package com.example.autosalon.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carTable")
public class CarModel {
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarAge() {
        return carAge;
    }

    public void setCarAge(String carAge) {
        this.carAge = carAge;
    }

    @PrimaryKey(autoGenerate = true)
    int carId;
    @ColumnInfo(name = "carName")
    String carName;
    @ColumnInfo(name = "carColor")
    String carColor;
    @ColumnInfo(name = "carPrice")
    String carPrice;
    @ColumnInfo(name = "carAge")
    String carAge;
}
