package vn.asiantech.training.models;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

import io.realm.RealmObject;
import lombok.Data;

/**
 * Created by phuong on 16/12/2016.
 */
@Data
public class Task extends RealmObject {
    private int id;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("content")
    private String mContent;
    private int favorite;
    private String created_at;
    private boolean mIsFavorite;
    private String mRandomColor;

    public Task() {
        this.mRandomColor = (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200);
    }
}
