package hr.foi.air.webservice;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;

import hr.foi.air.database.entities.Discount;
import hr.foi.air.database.entities.Store;
import hr.foi.air.webservice.handlers.MyWebserviceHandler;
import hr.foi.air.webservice.responses.MyWebserviceResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyWebserviceCaller {
    // retrofit object
    Retrofit retrofit;
    // base URL of the web service
    private final String baseUrl = "http://cortex.foi.hr/mtl/courses/air/";
    private MyWebserviceHandler myWebserviceHandler;

    // constructor
    public MyWebserviceCaller(MyWebserviceHandler myWebserviceHandler){

        //caller received reference to response handler to enable callback when data is ready
        this.myWebserviceHandler = myWebserviceHandler;

        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    // get all records from a web service
    public void getAll(String method, final Type entityType){

        //TODO: fix get all to work with stores and discounts

        MyWebservice serviceAPI = retrofit.create(MyWebservice.class);
        Call<MyWebserviceResponse> call;
        if(entityType == Store.class){
            call = serviceAPI.getStores(method);
        }
        else {
            call = serviceAPI.getDiscounts(method);
        }

        if(call != null){
            call.enqueue(new Callback<MyWebserviceResponse>() {
                @Override
                public void onResponse(Response<MyWebserviceResponse> response, Retrofit retrofit) {
                    try {
                        if(response.isSuccess()){
                            if(entityType == Store.class){
                                System.out.println("Got stores...");
                            } else if(entityType == Discount.class){
                                System.out.println("Got discounts...");
                            } else
                            {
                                System.out.println("Unrecognized class");
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
