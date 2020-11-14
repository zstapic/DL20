package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity implements DataLoadedListener {

    @BindView(R.id.discount_list)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.test_button)
    public void buttonClicked(View view)
    {
        DataLoader dataLoader = new WsDataLoader();
        dataLoader.loadData(this);
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