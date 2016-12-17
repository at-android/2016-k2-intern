package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 22/11/2016.
 */

public class Task implements Parcelable {
    private String name;
    private String phoneNumber;
    private int interest;

    public Task() {
    }

    public Task(String name, String phoneNumber, int interest) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.interest = interest;
    }

    protected Task(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
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
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
        parcel.writeInt(interest);
    }
}
