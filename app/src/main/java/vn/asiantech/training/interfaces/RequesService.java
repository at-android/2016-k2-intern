package vn.asiantech.training.interfaces;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @FormUrlEncoded
    @PUT("tasks/{id}")
    Call<Result> editTask(@Path("id") String id, @Field("title") String title, @Field("content") String content, @Field("favorite") int favorite);

    @FormUrlEncoded
    @PUT("tasks/{id}")
    Call<Result> changeFavorite(@Path("id") String id, @Field("favorite") int favorite);

    @DELETE("tasks/{id}")
    Call<Result> deleteTask(@Path("id") String id);
}
