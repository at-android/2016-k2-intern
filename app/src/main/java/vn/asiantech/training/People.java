package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 29/11/2016.
 */
public class People implements Parcelable {
    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };
    private String name;
    private String phoneNumber;

    protected People(Parcel in) {
        phoneNumber = in.readString();
        name = in.readString();
    }


    public People(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(name);
    }
}
