package hr.foi.air.webservice;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.webservice.handlers.MyWebserviceHandler;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class WebserviceCallTest {
    @Test
    public void callWebService() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        MyWebserviceCaller storeCaller = new MyWebserviceCaller(storeHandler);
        MyWebserviceCaller discountCaller = new MyWebserviceCaller(discountsHandler);
        storeCaller.getAll("getAll", Store.class);
        discountCaller.getAll("getAll", Discount.class);

    }

    private MyWebserviceHandler storeHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timeStamp) {
            if (ok) {
                List<Store> stores = (List<Store>) result;
                for(Store s : stores)
                {
                    Log.d("AIRTEST", s.getName());
                }
                assertEquals("Expected 2, got \" + stores.size() + \" discounts!", 2, stores.size());
            }
        }
    };

    private MyWebserviceHandler discountsHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok, long timeStamp) {
            if (ok) {
                List<Discount> discounts = (List<Discount>) result;
                for(Discount d : discounts)
                {
                    Log.d("AIRTEST", d.getName());
                }
                assertEquals("Expected 4, got " + discounts.size() + " discounts!", 4, discounts.size());
            }
        }
    };
}
