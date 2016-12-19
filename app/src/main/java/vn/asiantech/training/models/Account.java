package vn.asiantech.training.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 18/12/2016.
 */
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
