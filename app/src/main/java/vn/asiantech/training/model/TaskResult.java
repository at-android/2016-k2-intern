package vn.asiantech.training.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(suppressConstructorProperties = true)
@Data

public class TaskResult {
    private boolean error;
    private String message;
    @SerializedName("task_id")
    private int taskId;
    @SerializedName("list_tasks")
    List<Task> listTasks;
}
