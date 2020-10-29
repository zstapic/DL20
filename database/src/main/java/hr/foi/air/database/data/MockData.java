package hr.foi.air.database.data;

import android.content.Context;

import hr.foi.air.database.DAO;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

public class MockData {
    private static DAO dao;

    public static void writeAll(Context context)
    {
        //get dao
        dao = MyDatabase.getInstance(context).getDAO();

        Store acmeStore = new Store();
        acmeStore.setName("ACME Store");
        acmeStore.setId((int)dao.insertStores(acmeStore)[0]);

        Discount apples = new Discount();
        apples.setName("Apples off 10%");
        apples.setDiscountValue(10);
        apples.setStoreId(acmeStore.getId());

        Discount tuna = new Discount();
        tuna.setName("Three for two!");
        tuna.setDiscountValue(33);
        tuna.setStoreId(acmeStore.getId());

        dao.insertDiscounts(apples, tuna);
    }
}
