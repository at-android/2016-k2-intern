package vn.asiantech.training.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HoangDuy on 16/12/2016.
 */
public class People extends RealmObject {
    @PrimaryKey
    private String phoneNumber;
    private String name;

    public People(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public People() {

    }

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

}
