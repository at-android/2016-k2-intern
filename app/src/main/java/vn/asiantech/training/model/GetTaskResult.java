package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HoangDuy on 20/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
@lombok.Getter
public class GetTaskResult {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("list_tasks")
    private List<Task> tasks;
}
