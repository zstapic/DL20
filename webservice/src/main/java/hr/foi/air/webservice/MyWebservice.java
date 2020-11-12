package hr.foi.air.webservice;

import hr.foi.air.webservice.responses.MyWebserviceResponse;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface MyWebservice {
    @FormUrlEncoded
    @POST("stores.php")
    Call<MyWebserviceResponse> getStores(@Field("method") String method);

    @FormUrlEncoded
    @POST("discounts.php")
    Call<MyWebserviceResponse> getDiscounts(@Field("method") String method);
}
