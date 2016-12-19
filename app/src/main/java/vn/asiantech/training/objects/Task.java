package vn.asiantech.training.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MaiManhDuy on 12/18/2016.
 */

public class Task {
    @SerializedName("id")
    public String id;
    private String title;
    private String content;
    private int favorite;

    public Task() {
    }


    public Task(String id, String title, String content, int favorite) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
