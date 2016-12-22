package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HoangDuy on 19/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
@lombok.Getter
@lombok.Setter
public class LoginResult {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("user")
    private User user;
}
