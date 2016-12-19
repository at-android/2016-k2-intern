package vn.asiantech.training.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.asiantech.training.objects.Result;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */
public interface RequesService {

    @FormUrlEncoded
    @POST("register")
    Call<Result> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<Result> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("tasks")
    Call<Result> addTask(@Field("title") String title, @Field("content") String content, @Field("favorite") int favorite);

    @GET("tasks")
    Call<Result> getTask(@Query("offset") int offset, @Query("limit") int limit);
}
