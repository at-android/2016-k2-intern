package vn.asiantech.training.model;

/**
 * Created by phuong on 14/11/2016.
 */

public class Student {
    private String mName;
    private String mAge;
    private String mAddress;
    private String mSchool;

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

    public Student(String mName, String mAge, String mAddress, String mSchool) {
        this.mName = mName;
        this.mAge = mAge;
        this.mAddress = mAddress;
        this.mSchool = mSchool;
    }

    public Student() {
    }
}
