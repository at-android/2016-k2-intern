package vn.asiantech.training.networks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Call<LoginResult> login(@Field("email") String email,@Field("password") String password);

    @GET("tasks")
    Call<List<Task>> listTasks(@Path("offset") int offset, @Path("limit") int limit, @Header("Authorization") String access_token);

    @FormUrlEncoded
    @POST("tasks")
    Call<TaskResult> createTask(@Field("title") String title,@Field("content") String content, @Field("favorite") int fav,@Header("Authorization") String access_token);
}
