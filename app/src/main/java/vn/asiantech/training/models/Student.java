package vn.asiantech.training.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phuong on 14/12/2016.
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
    private String mSchool;
    private String mAddress;

    public Student(String mName, String mAge, String mSchool, String mAddress) {
        this.mName = mName;
        this.mAge = mAge;
        this.mSchool = mSchool;
        this.mAddress = mAddress;
    }

    public Student() {
    }

    protected Student(Parcel in) {
        mName = in.readString();
        mAge = in.readString();
        mSchool = in.readString();
        mAddress = in.readString();
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getAge() {
        return mAge;
    }

    public void setAge(String mAge) {
        this.mAge = mAge;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setmchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mAge);
        parcel.writeString(mSchool);
        parcel.writeString(mAddress);
    }

    @Override
    public String toString() {
        return "Student{" +
                "mName='" + mName + '\'' +
                ", mAge='" + mAge + '\'' +
                ", mSchool='" + mSchool + '\'' +
                ", mAddress='" + mAddress + '\'' +
                '}';
    }
}
