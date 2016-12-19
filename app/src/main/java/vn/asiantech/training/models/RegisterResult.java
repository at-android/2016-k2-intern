package vn.asiantech.training.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 18/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class RegisterResult {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
}
