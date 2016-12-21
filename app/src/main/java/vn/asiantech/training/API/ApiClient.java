package vn.asiantech.training.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 20/12/2016.
 */

public class ApiClient {
    private static Retrofit retrofit = null;
    private static String mBaseUrl = "http://atintership.esy.es/intership/v1/";


    public static Retrofit retrofit () {

        retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
