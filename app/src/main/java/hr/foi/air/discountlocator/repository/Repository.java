package hr.foi.air.discountlocator.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator.loaders.DbDataLoader;
import hr.foi.air.discountlocator.loaders.WsDataLoader;
import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;
import hr.foi.air.discountlocator.utils.SystemServices;

public class Repository implements DataLoadedListener {
    RepositoryListener repositoryListener;

    public void getData(RepositoryListener repositoryListener, Context context)
    {
        this.repositoryListener = repositoryListener;

        DataLoader dataLoader;
        if (SystemServices.isNetworkAvailable(context))
            dataLoader = new WsDataLoader();
        else
            dataLoader = new DbDataLoader(context);
        dataLoader.loadData(this);
    }

    @Override
    public void onDataLoaded(List<Store> stores, List<Discount> discounts) {
        List<ExpandableStoreItem> storeItems = new ArrayList<>();
        for(Store s : stores)
            storeItems.add(new ExpandableStoreItem(s, discounts));

        repositoryListener.onDataReady(storeItems);
    }

}
