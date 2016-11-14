package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/14/2016.
 */

public class StudentObject {
    private String school;
    private String name;
    private String address;
    private String old;

    public StudentObject(String school, String name, String address, String old) {
        this.school = school;
        this.name = name;
        this.address = address;
        this.old = old;
    }

    public StudentObject() {
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

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }
}
