package vn.asiantech.training.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 19/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class TaskResult {
    private boolean error;
    private String message;
    @SerializedName("task_id")
    private int taskId;
}
