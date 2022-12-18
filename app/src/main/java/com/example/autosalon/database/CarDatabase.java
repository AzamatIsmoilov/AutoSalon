

package com.example.autosalon.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.autosalon.models.CarDao;
import com.example.autosalon.models.CarModel;

@Database(entities = {CarModel.class},version = 1)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDao carDao();
}
