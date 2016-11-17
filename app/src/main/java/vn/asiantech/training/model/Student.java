package vn.asiantech.training.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phuong on 14/11/2016.
 */

public class Student implements Parcelable {
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    private String mName;
    private String mAge;
    private String mAddress;
    private String mSchool;

    protected Student(Parcel in) {
        mName = in.readString();
        mAge = in.readString();
        mAddress = in.readString();
        mSchool = in.readString();
    }

    public Student(String mName, String mAge, String mAddress, String mSchool) {
        this.mName = mName;
        this.mAge = mAge;
        this.mAddress = mAddress;
        this.mSchool = mSchool;
    }

    public Student() {
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mAge);
        parcel.writeString(mAddress);
        parcel.writeString(mSchool);
    }

    @Override
    public String toString() {
        return "Student{" +
                "mName='" + mName + '\'' +
                ", mAge='" + mAge + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mSchool='" + mSchool + '\'' +
                '}';
    }
}
