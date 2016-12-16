package vn.asiantech.training.object;

import io.realm.RealmObject;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */

public class Address extends RealmObject {
    private String name;
    private String phoneNumber;

    public Address(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Address() {
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
