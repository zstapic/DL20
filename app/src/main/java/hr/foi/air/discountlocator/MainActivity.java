package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator.loaders.DbDataLoader;
import hr.foi.air.discountlocator.loaders.WsDataLoader;
import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;
import hr.foi.air.discountlocator.recyclerview.StoreRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements DataLoadedListener {

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        loadData();
    }

    public void loadData()
    {
        DataLoader dataLoader = new WsDataLoader();
        dataLoader.loadData(this);
    }

    @Override
    public void onDataLoaded(List<Store> stores, List<Discount> discounts) {
        List<ExpandableStoreItem> storeItems = new ArrayList<>();
        for(Store s : stores)
            storeItems.add(new ExpandableStoreItem(s, discounts));

        recyclerView.setAdapter(new StoreRecyclerAdapter(this, storeItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}