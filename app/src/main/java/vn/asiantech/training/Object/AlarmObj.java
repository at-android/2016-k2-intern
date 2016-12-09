package vn.asiantech.training.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaiManhDuy on 12/2/2016.
 */

public class AlarmObj implements Parcelable {
    public static final Creator<AlarmObj> CREATOR = new Creator<AlarmObj>() {
        @Override
        public AlarmObj createFromParcel(Parcel in) {
            return new AlarmObj(in);
        }

        @Override
        public AlarmObj[] newArray(int size) {
            return new AlarmObj[size];
        }
    };
    private String title;
    private int hour;
    private int minute;
    private String dayofweek;

    protected AlarmObj(Parcel in) {
        title = in.readString();
        hour = in.readInt();
        minute = in.readInt();
        dayofweek = in.readString();
    }

    public AlarmObj() {
    }

    public AlarmObj(String title, int hour, int minute, String dayofweek) {
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.dayofweek = dayofweek;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeString(dayofweek);
    }

}
