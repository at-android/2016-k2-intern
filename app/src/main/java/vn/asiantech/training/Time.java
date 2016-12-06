package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 5/12/2016.
 */

public class Time implements Parcelable {
    private String date;
    private String hour;
    private String minute;

    public Time() {

    }

    public Time(String date, String hour, String minute) {
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    protected Time(Parcel in) {
        date = in.readString();
        hour = in.readString();
        minute = in.readString();
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(hour);
        parcel.writeString(minute);
    }
}
