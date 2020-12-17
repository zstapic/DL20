package hr.foi.air.discountlocator_g2.loaders;

import android.content.Context;

import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.DAO;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.data.MockData;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

public class DbDataLoader implements DataLoader {
    private boolean dataLoaded = false;
    private Context context;

    public DbDataLoader(Context context)
    {
        this.context = context;
    }

    @Override
    public void loadData(DataLoadedListener listener) {
        DAO dao = MyDatabase.getInstance(context).getDAO();
        List<Store> stores = dao.loadAllStores();
        List<Discount> discounts = dao.loadAllDiscounts();

        //mock data if necessary
        if (stores.size() == 0) {
            MockData.writeAll(null);
            stores = dao.loadAllStores();
            discounts = dao.loadAllDiscounts();
        }

        dataLoaded = true;
        listener.onDataLoaded(stores, discounts);
    }

    @Override
    public boolean isDataLoaded() {
        return dataLoaded;
    }
}
