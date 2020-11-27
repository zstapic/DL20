package hr.foi.air.discountlocator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.discountlocator.R;
import hr.foi.air.discountlocator.recyclerview.ExpandableStoreItem;
import hr.foi.air.discountlocator.recyclerview.StoreRecyclerAdapter;
import hr.foi.air.discountlocator.repository.Repository;
import hr.foi.air.discountlocator.repository.RepositoryListener;

public class DiscountListFragment extends Fragment implements RepositoryListener {
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discount_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    public void getData()
    {
        Repository r = new Repository();
        r.getData(this, this.getContext());
    }

    @Override
    public void onDataReady(List<ExpandableStoreItem> storeItems) {
        recyclerView.setAdapter(new StoreRecyclerAdapter(this.getContext(), storeItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
