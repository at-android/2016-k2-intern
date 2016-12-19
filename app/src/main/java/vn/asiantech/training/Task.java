package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 22/11/2016.
 */

public class Task implements Parcelable {
    private String title;
    private String content;
    private int interest;

    public Task() {
    }

    public Task(String title, String content, int interest) {
        this.title = title;
        this.content = content;
        this.interest = interest;
    }

    protected Task(Parcel in) {
        title = in.readString();
        content = in.readString();
        interest = in.readInt();
    }

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

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeInt(interest);
    }
}
