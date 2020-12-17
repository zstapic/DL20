package hr.foi.air.discountlocator_g2.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.discountlocator_g2.R;

public class StoreRecyclerAdapter extends ExpandableRecyclerAdapter
        <ExpandableStoreItem, Discount, StoreViewHolder, DiscountViewHolder>
{
    private Context context;

    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public StoreRecyclerAdapter(Context context, @NonNull List<ExpandableStoreItem> parentList) {
        super(parentList);
        this.context = context;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View storeView =
                LayoutInflater.from(context).inflate(
                        R.layout.store_list_item, parentViewGroup, false);
        return new StoreViewHolder(storeView);
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View discountView =
                LayoutInflater.from(context).inflate(
                        R.layout.discount_list_item, childViewGroup, false);
        return new DiscountViewHolder(discountView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull StoreViewHolder parentViewHolder, int parentPosition, @NonNull ExpandableStoreItem parent) {
        parentViewHolder.bindToData(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull DiscountViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull Discount child) {
        childViewHolder.bindToData(child);
    }
}
