package vn.asiantech.training.Model;

import io.realm.RealmObject;


/**
 * Created by Administrator on 16/12/2016.
 */

public class RealmPeople extends RealmObject {

    private int id;
    private String name;
    private String phoneNumber;

    public RealmPeople(){

    }

    public RealmPeople(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String toString(){
        return this.id+"--"+this.name+"--"+this.phoneNumber;
    }
}
