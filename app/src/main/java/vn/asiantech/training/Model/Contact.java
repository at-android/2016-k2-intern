package vn.asiantech.training.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 29/11/2016.
 */

public class Contact implements Parcelable{
    private String name;
    private String phoneNumber;

    public Contact(){

    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phoneNumber);
    }

    public String toString(){
        return this.name + "---" + this.phoneNumber;
    }
}
