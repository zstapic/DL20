package hr.foi.air.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertStores(Store... stores);
    @Update public void updateStores(Store... stores);
    @Delete public void deleteStores(Store... stores);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDiscounts(Discount... discounts);
    @Update public void updateDiscounts(Discount... discounts);
    @Delete public void deleteDiscounts(Discount... discounts);

    @Query("SELECT * FROM stores")
    public List<Store> loadAllStores();

    @Query("SELECT * FROM discounts WHERE storeId = :storeId")
    public List<Discount> loadAllDiscountsByStore(int storeId);

    @Query("SELECT * FROM stores WHERE name LIKE :name")
    public List<Store> loadAllStoreByName(String name);

    @Query("SELECT name FROM discounts")
    public List<String> getDiscounts();
}