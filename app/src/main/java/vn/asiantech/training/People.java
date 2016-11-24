package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 22/11/2016.
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
    private int iconFavorite;

    public People(String name, String phoneNumber, int iconFavorite) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.iconFavorite = iconFavorite;
    }

    protected People(Parcel in) {
        phoneNumber = in.readString();
        name = in.readString();
        iconFavorite = in.readInt();
    }

    public String toString() {
        return this.name + " " + this.phoneNumber;
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

    public int getIconFavorite() {
        return iconFavorite;
    }

    public void setIconFavorite(int iconFavorite) {
        this.iconFavorite = iconFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(phoneNumber);
        parcel.writeString(name);
        parcel.writeInt(iconFavorite);
    }
}
