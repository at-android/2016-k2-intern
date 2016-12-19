package vn.asiantech.training.models;

import java.util.Random;

import io.realm.RealmObject;
import lombok.Data;

/**
 * Created by phuong on 16/12/2016.
 */
@Data
public class Task extends RealmObject {
    private String mTitle;
    private String mContent;
    private boolean mIsFavorite;
    private String mRandomColor;

    public Task() {
        this.mRandomColor = (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200);
    }
}
