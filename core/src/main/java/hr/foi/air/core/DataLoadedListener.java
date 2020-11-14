package hr.foi.air.core;

import java.util.List;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

public interface DataLoadedListener {
    void onDataLoaded(
            List<Store> stores,
            List<Discount> discounts);
}
