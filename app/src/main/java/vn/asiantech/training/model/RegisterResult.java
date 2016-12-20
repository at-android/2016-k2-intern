package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class RegisterResult {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
}
