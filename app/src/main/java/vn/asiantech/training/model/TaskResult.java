package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HoangDuy on 19/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class TaskResult {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
}
