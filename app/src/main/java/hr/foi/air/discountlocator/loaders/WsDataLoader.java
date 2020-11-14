package hr.foi.air.discountlocator.loaders;

import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.webservice.MyWebserviceCaller;
import hr.foi.air.webservice.handlers.MyWebserviceHandler;

public class WsDataLoader implements DataLoader {
    private DataLoadedListener listener;
    private List<Store> stores;
    private List<Discount> discounts;

    private boolean storesArrived = false;
    private boolean discountsArrived = false;

    @Override
    public void loadData(DataLoadedListener listener) {
        this.listener = listener;

        MyWebserviceCaller storeCaller = new MyWebserviceCaller(storeHandler);
        MyWebserviceCaller discountCaller = new MyWebserviceCaller(discountsHandler);
        storeCaller.getAll("getAll", Store.class);
        discountCaller.getAll("getAll", Discount.class);
    }

    @Override
    public boolean isDataLoaded() {
        return storesArrived && discountsArrived;
    }

    @SuppressWarnings("unchecked")
    private MyWebserviceHandler storeHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timeStamp) {
            if (ok) {
                stores = (List<Store>) result;
            }
            storesArrived = true;
            tryToSyncAndSendData();
        }
    };

    @SuppressWarnings("unchecked")
    private MyWebserviceHandler discountsHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timeStamp) {
            if (ok) {
                discounts = (List<Discount>) result;
            }
            discountsArrived = true;
            tryToSyncAndSendData();
        }
    };

    private void tryToSyncAndSendData()
    {
        if (isDataLoaded())
            listener.onDataLoaded(stores, discounts);
    }
}