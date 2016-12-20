package vn.asiantech.training.networks;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.asiantech.training.models.LoginResult;
import vn.asiantech.training.models.RegisterResult;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.models.TaskResult;

/**
 * Created by phuong on 17/12/2016.
 */

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResult> createAccount(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@Field("email") String email, @Field("password") String password);

    @GET("tasks")
    Call<TaskResult> listTasks(@Query("offset") int offset, @Query("limit") int limit, @Header("Authorization") String access_token);

    @FormUrlEncoded
    @POST("tasks")
    Call<TaskResult> createTask(@Field("title") String title, @Field("content") String content, @Field("favorite") int fav, @Header("Authorization") String access_token);

    @FormUrlEncoded
    @PUT("tasks/{id}")
    Call<TaskResult> editTask(@Path("id") int id, @Field("title") String title, @Field("content") String content, @Field("favorite") int fav, @Header("Authorization") String access_token);

    @DELETE("tasks/{id}")
    Call<TaskResult> deleteTask(@Path("id") int id,@Header("Authorization") String access_token);
}
