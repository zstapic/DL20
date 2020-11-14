package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.data.MockData;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator.loaders.DbDataLoader;

import static hr.foi.air.database.MyDatabase.getInstance;

public class MainActivity extends AppCompatActivity implements DataLoadedListener {

    @BindView(R.id.discount_list)
    ListView mListView;

    public static MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = getInstance(this);

        ButterKnife.bind(this);
        mockData();
    }

    @OnClick(R.id.test_button)
    public void buttonClicked(View view)
    {
        DataLoader dataLoader = new DbDataLoader(this);
        dataLoader.loadData(this);
    }


    private void mockData()
    {
        List<Store> stores = database.getDAO().loadAllStores();
        if (!stores.isEmpty()) {
            for (Store s : stores) {
                Log.d("AIRAIR","Store: " + s.getName());
                List<Discount> discounts
                        = database.getDAO().loadAllDiscountsByStore(s.getId());
                for (Discount d : discounts) {
                    Log.d("AIRAIR","Discount: " + d.getName());
                    //System.out.println("Discount: " + d.getName());
                }
            }
        }else {
            MockData.writeAll(this);
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void onDataLoaded(List<Store> stores, List<Discount> discounts) {
        final List<String> listItems = new ArrayList<>();
        for (Discount d: discounts) {
            listItems.add(d.getName());
        }

        ArrayAdapter adapter =
                new ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        listItems.toArray());
        mListView.setAdapter(adapter);
    }
}