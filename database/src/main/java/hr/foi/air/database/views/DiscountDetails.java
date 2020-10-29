package hr.foi.air.database.views;

import androidx.room.DatabaseView;

@DatabaseView("SELECT " +
        "discounts.id, " +
        "discounts.name, " +
        "discounts.description, " +
        "discounts.discountValue AS value, " +
        "stores.name AS storeName, " +
        "stores.latitude, " +
        "stores.longitude FROM discounts " +
        "INNER JOIN stores ON discounts.storeId = stores.id")
public class DiscountDetails {
    public int id;
    public String name;
    public String description;
    public int value;
    public String storeName;
    public long latitude;
    public long longitude;
}