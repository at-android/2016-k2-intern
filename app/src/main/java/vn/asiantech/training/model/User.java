package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HoangDuy on 19/12/2016.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("created_at")
    private String created_at;
}
