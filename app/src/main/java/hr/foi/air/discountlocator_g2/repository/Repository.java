package hr.foi.air.discountlocator_g2.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.DAO;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator_g2.loaders.DbDataLoader;
import hr.foi.air.discountlocator_g2.loaders.WsDataLoader;
import hr.foi.air.discountlocator_g2.recyclerview.ExpandableStoreItem;
import hr.foi.air.discountlocator_g2.utils.SystemServices;

public class Repository implements DataLoadedListener {
    RepositoryListener repositoryListener;
    private Context context;
    DataLoader dataLoader;

    public void getData(RepositoryListener repositoryListener, Context context)
    {
        this.repositoryListener = repositoryListener;
        this.context = context;

        if (SystemServices.isNetworkAvailable(context))
            dataLoader = new WsDataLoader();
        else
            dataLoader = new DbDataLoader(context);
        dataLoader.loadData(this);
    }

    @Override
    public void onDataLoaded(List<Store> stores, List<Discount> discounts) {
        SendDataToListener(stores, discounts);

        if (dataLoader.getClass() == WsDataLoader.class)
            CacheDataToDatabase(stores, discounts);
    }

    private void SendDataToListener(List<Store> stores, List<Discount> discounts) {
        List<ExpandableStoreItem> storeItems = new ArrayList<>();
        for(Store s : stores)
            storeItems.add(new ExpandableStoreItem(s, discounts));

        repositoryListener.onDataReady(storeItems);
    }

    private void CacheDataToDatabase(List<Store> stores, List<Discount> discounts) {
        DAO dao = MyDatabase.getInstance(context).getDAO();
        dao.deleteStores();
        dao.deleteDiscounts();

        for(Store s: stores)
            dao.insertStores(s);
        for(Discount d: discounts)
            dao.insertDiscounts(d);
    }
}
