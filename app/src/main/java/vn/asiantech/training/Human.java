package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 22/11/2016.
 */

public class Human implements Parcelable {
    private String name;
    private String phoneNumber;
    private boolean interest;

    public Human() {
    }

    public Human(String name, String phoneNumber, boolean interest) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.interest = interest;
    }

    protected Human(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        interest = in.readByte() != 0;
    }

    public static final Creator<Human> CREATOR = new Creator<Human>() {
        @Override
        public Human createFromParcel(Parcel in) {
            return new Human(in);
        }

        @Override
        public Human[] newArray(int size) {
            return new Human[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeByte((byte) (interest ? 1 : 0));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInterest() {
        return interest;
    }

    public void setInterest(boolean interest) {
        this.interest = interest;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
