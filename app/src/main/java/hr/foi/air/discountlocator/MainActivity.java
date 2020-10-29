package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.data.MockData;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

import static hr.foi.air.database.MyDatabase.getInstance;

public class MainActivity extends AppCompatActivity {

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
        final List<String> listItems = database.getDAO().getDiscounts();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
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
}