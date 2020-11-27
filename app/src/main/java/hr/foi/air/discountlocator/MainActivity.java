package hr.foi.air.discountlocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;
import hr.foi.air.discountlocator.recyclerview.StoreRecyclerAdapter;
import hr.foi.air.discountlocator.repository.Repository;
import hr.foi.air.discountlocator.repository.RepositoryListener;

public class MainActivity extends AppCompatActivity implements RepositoryListener {

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getData();
    }

    public void getData()
    {
        Repository r = new Repository();
        r.getData(this);
    }

    @Override
    public void onDataReady(List<ExpandableStoreItem> storeItems) {
        recyclerView.setAdapter(new StoreRecyclerAdapter(this, storeItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}