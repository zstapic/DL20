package hr.foi.air.discountlocator.repository;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator.loaders.WsDataLoader;
import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;

public class Repository implements DataLoadedListener {
    RepositoryListener repositoryListener;

    public void getData(RepositoryListener repositoryListener)
    {
        this.repositoryListener = repositoryListener;

        DataLoader dataLoader = new WsDataLoader();
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
