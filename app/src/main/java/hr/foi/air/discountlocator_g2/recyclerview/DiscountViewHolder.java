package hr.foi.air.discountlocator_g2.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.database.entities.Discount;
import hr.foi.air.discountlocator_g2.R;

public class DiscountViewHolder extends ChildViewHolder<Discount> {

    @BindView(R.id.discount_name)
    TextView discountName;
    @BindView(R.id.discount_desc)
    TextView discountDesc;
    @BindView(R.id.discount_value)
    TextView discountValue;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public DiscountViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindToData(Discount discount)
    {
        discountName.setText(discount.getName());
        discountDesc.setText(discount.getDescription());
        discountValue.setText(discount.getDiscountValue() + "%");
    }
}
