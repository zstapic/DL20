package hr.foi.air.discountlocator.repository;

import java.util.List;

import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;

public interface RepositoryListener {
    void onDataReady(List<ExpandableStoreItem> storeItems);
}
