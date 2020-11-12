package hr.foi.air.webservice;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;

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

    // constructor
    public MyWebserviceCaller(){

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
        Call<MyWebserviceResponse> call = serviceAPI.getStores(method);

        if(call != null){
            call.enqueue(new Callback<MyWebserviceResponse>() {
                @Override
                public void onResponse(Response<MyWebserviceResponse> response, Retrofit retrofit) {
                    try {
                        if(response.isSuccess()){
                            System.out.println("Got stores...");
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
