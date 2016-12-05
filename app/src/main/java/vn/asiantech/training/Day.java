package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 4/12/2016.
 */

public class Day implements Parcelable{
    private String nameOfDay;

    public Day() {

    }

    public Day(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    protected Day(Parcel in) {
        nameOfDay = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getNameOfDay() {
        return nameOfDay;
    }

    public void setNameOfDay(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameOfDay);
    }
}
