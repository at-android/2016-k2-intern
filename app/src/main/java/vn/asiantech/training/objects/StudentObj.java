package vn.asiantech.training.objects;

/**
 * Created by MaiManhDuy on 12/15/2016.
 */

public class StudentObj {
    private String school;
    private String name;
    private String address;
    private int old;

    public StudentObj(String school, String name, String address, int old) {
        this.school = school;
        this.name = name;
        this.address = address;
        this.old = old;
    }

    public StudentObj() {
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

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }
}
