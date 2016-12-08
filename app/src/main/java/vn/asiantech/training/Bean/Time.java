package vn.asiantech.training.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by HoangDuy on 03/12/2016.
 */
public class Time implements Parcelable, Serializable {


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
    private int hour;
    private int minute;
    private String dayofweek;
    private int flag;

    protected Time(Parcel in) {
        hour = in.readInt();
        minute = in.readInt();
        dayofweek = in.readString();
        flag = in.readInt();
    }

    public Time(int hour, int minute, String dayofweek, int flag) {
        this.hour = hour;
        this.minute = minute;
        this.dayofweek = dayofweek;
        this.flag = flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeString(dayofweek);
        parcel.writeInt(flag);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
