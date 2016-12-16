package vn.asiantech.training.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.asiantech.training.objects.User;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */
public interface RequesService {
    @FormUrlEncoded
    @POST("register")
    Call<User> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);
}
