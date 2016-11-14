package vn.asiantech.training;

/**
 * Created by HoangDuy on 14/11/2016.
 */
public class Student {
    private String school;
    private String name;
    private String address;
    private String age;

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
}
