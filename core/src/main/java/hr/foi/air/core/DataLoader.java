package hr.foi.air.core;

public interface DataLoader {
    void loadData(DataLoadedListener listener); //Async
    boolean isDataLoaded();
}
