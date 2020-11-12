package hr.foi.air.webservice;

import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;

import retrofit.GsonConverterFactory;
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

        //todo: implement web service call and response handling

    }
}
