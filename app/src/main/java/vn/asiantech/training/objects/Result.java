package vn.asiantech.training.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MaiManhDuy on 12/19/2016.
 */

public class Result {
    @SerializedName("error")
    public String error;
    @SerializedName("message")
    public String message;
    @SerializedName("user")
    public User user;
    @SerializedName("task_id")
    public String taskID;
    @SerializedName("list_tasks")
    public List<Task> listTasks;
}
