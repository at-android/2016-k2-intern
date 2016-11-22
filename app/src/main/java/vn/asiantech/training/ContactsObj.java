package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/22/2016.
 */

public class ContactsObj {
    private String name;
    private String phoneNumber;
    private boolean like;

    public ContactsObj(boolean like, String name, String phoneNumber) {
        this.like = like;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public ContactsObj() {
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

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
