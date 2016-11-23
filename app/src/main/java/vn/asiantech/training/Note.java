package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 23/11/2016.
 */
public class Note implements Parcelable {
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private String title;
    private String content;
    private String dateTime;

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        dateTime = in.readString();
    }

    public Note(String title, String content, String dateTime) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(dateTime);
    }
}
