package vn.asiantech.training;

import java.io.Serializable;

/**
 * Created by Administrator on 14/11/2016.
 */

public class SinhVien implements Serializable {
    private String schoolName;
    private String name;
    private String age;
    private String address;

    public SinhVien() {

    }

    public SinhVien(String schoolName, String name, String age, String address) {
        this.schoolName = schoolName;
        this.name = name;
        this.age = age;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String toString() {
        return this.getSchoolName() + "-" + this.getName() + "-" + this.getAge() + "-" + this.getAddress();
    }
}
