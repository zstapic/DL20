package hr.foi.air.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

@Database(version = 1, entities = {Store.class, Discount.class},
        views = {}, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
}