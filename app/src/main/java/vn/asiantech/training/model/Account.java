package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Account {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("created_at")
    private String created_at;
}
