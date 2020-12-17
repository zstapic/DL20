package hr.foi.air.discountlocator_g2.repository;

import java.util.List;

import hr.foi.air.discountlocator_g2.recyclerview.ExpandableStoreItem;

public interface RepositoryListener {
    void onDataReady(List<ExpandableStoreItem> storeItems);
}
