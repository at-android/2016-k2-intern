package vn.asiantech.training;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 14/11/2016.
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
    private String school;
    private String name;
    private String address;
    private String age;

    protected Student(Parcel in) {
        school = in.readString();
        name = in.readString();
        address = in.readString();
        age = in.readString();
    }

    public Student(String school, String name, String address, String age) {
        this.school = school;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(school);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(age);
    }
}
