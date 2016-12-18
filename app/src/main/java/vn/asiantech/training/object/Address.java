package vn.asiantech.training.object;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */

public class Address extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String phoneNumber;

    public Address(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Address() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
