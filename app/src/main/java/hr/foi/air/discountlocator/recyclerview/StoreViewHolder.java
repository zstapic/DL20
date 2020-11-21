package hr.foi.air.discountlocator.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.database.entities.Store;
import hr.foi.air.discountlocator.R;

public class StoreViewHolder extends ParentViewHolder {

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.store_desc)
    TextView storeDesc;
    @BindView(R.id.store_image)
    ImageView storeImage;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindToData(Store store)
    {
        storeName.setText(store.getName());
        storeDesc.setText(store.getDescription());
        Picasso.with(storeImage.getContext())
                .load(store.getImgUrl())
                .into(storeImage);
    }
}
