package vn.asiantech.training.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

import lombok.Data;

/**
 * Created by phuong on 16/12/2016.
 */
@Data
public class Task implements Parcelable {
    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    private int id;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("content")
    private String mContent;
    private int favorite;
    private String created_at;
    private String mRandomColor;

    public Task() {
        this.mRandomColor = (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200) + ";" + (new Random()).nextInt(200);
    }

    protected Task(Parcel in) {
        id = in.readInt();
        mTitle = in.readString();
        mContent = in.readString();
        favorite = in.readInt();
        created_at = in.readString();
        mRandomColor = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(mTitle);
        parcel.writeString(mContent);
        parcel.writeInt(favorite);
        parcel.writeString(created_at);
        parcel.writeString(mRandomColor);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                ", favorite=" + favorite +
                ", created_at='" + created_at + '\'' +
                ", mRandomColor='" + mRandomColor + '\'' +
                '}';
    }
}
