package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 20/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class LoginResult {
    @SerializedName("error")
    private boolean error;
    @SerializedName("user")
    private Account account;
}
