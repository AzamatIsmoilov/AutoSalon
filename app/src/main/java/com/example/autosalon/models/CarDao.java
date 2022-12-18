package com.example.autosalon.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CarDao {
    @Query("SELECT * FROM carTable")
    List<CarModel> getAll();


    @Query("SELECT * FROM carTable WHERE carName LIKE :carname")
    CarModel findByName(String carname);


    @Insert
    void insertAll(CarModel... carModels);

    @Delete
    void delete(CarModel carModels);

    @Query("DELETE FROM carTable WHERE carId=:carId")
    void deleteById(int carId);

    @Insert
    void insertAll(CarModel carModel);

    @Query("UPDATE carTable SET carColor=:carNewColor, carPrice=:carNewPrice, carName =:carNewName   WHERE carId = :id")
    void updateCar(int id, String carNewName, String carNewColor, String carNewPrice);

}

