package vn.asiantech.training.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmPeople extends RealmObject {
    private String name;
    @PrimaryKey
    private String phoneNumber;

    public RealmPeople() {

    }

    public RealmPeople(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String toString() {
        return this.name + "--" + this.phoneNumber;
    }
}
