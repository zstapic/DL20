package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.database.MyDatabase;
import hr.foi.air.database.data.MockData;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

import static hr.foi.air.database.MyDatabase.getInstance;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.test_button)
    Button testButton;

    public static MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = getInstance(this);

        ButterKnife.bind(this);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mockData();
            }
        });
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