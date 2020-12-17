package hr.foi.air.discountlocator_g2.recyclerview;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;

public class ExpandableStoreItem extends Store implements Parent<Discount> {
    private List<Discount> discounts;

    public ExpandableStoreItem(Store store, List<Discount> allDiscounts)
    {
        super(store);
        discounts = new ArrayList<>();
        for(Discount d : allDiscounts)
            if (d.getStoreId() == this.getId())
                discounts.add(d);
    }

    @Override
    public List<Discount> getChildList() {
        return discounts;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
