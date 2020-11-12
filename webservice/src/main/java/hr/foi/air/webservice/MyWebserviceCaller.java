package hr.foi.air.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

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
                                processStoresResponse(response);
                            } else if(entityType == Discount.class){
                                System.out.println("Got discounts...");
                                processDiscountsResponse(response);
                            } else
                            {
                                System.out.println("Unrecognized class");
                                processFailureResponse("Unrecognized class");
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        processFailureResponse(ex.getMessage());
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    processFailureResponse(t.getMessage());
                }
            });
        }
    }

    private void processStoresResponse(Response<MyWebserviceResponse> response) {
        Gson gson = new Gson();
        Store[] storeItems = gson.fromJson(
                response.body().getItems(),
                Store[].class
        );

        myWebserviceHandler.onDataArrived(
                Arrays.asList(storeItems),
                true,
                response.body().getTimeStamp());
    }


    private void processDiscountsResponse(Response<MyWebserviceResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Discount[] discountItems = gson.fromJson(
                response.body().getItems(),
                Discount[].class
        );

        myWebserviceHandler.onDataArrived(
                Arrays.asList(discountItems),
                true,
                response.body().getTimeStamp());
    }

    private void processFailureResponse(String message) {
        //TODO - Refactor to send message to handler

        myWebserviceHandler.onDataArrived(
                null,
                false,
                new Date().getTime());
    }
}
