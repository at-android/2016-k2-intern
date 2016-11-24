package vn.asiantech.training.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by phuong on 22/11/2016.
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
    private String mTitle;
    private String mContent;
    private Timestamp mTimeCreated;

    public Note(String mTitle, String mContent) {
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    public Note(String mTitle, String mContent, Timestamp timeCreated) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mTimeCreated = timeCreated;
    }

    protected Note(Parcel in) {
        mTitle = in.readString();
        mContent = in.readString();
    }

    public Note() {
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mContent);
    }

    @Override
    public String toString() {
        return "Note{" +
                "mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mTimeCreated=" + mTimeCreated +
                '}';
    }

    public Timestamp getmTimeCreated() {
        return mTimeCreated;
    }

    public void setmTimeCreated(Timestamp mTimeCreated) {
        this.mTimeCreated = mTimeCreated;
    }
}
