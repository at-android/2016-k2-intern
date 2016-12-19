package vn.asiantech.training.networks;

import android.accounts.Account;
import android.app.DownloadManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;
import vn.asiantech.training.models.Task;

/**
 * Created by phuong on 17/12/2016.
 */

public class ApiClient{
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
