package vn.asiantech.training.model;

import android.os.Parcel;

/**
 * Created by phuong on 29/11/2016.
 */

public class Contact {
    private String mName;
    private String mEmail;
    private String mPassword;

    public Contact(String mName, String mEmail, String mPassword) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    protected Contact(Parcel in) {
        mName = in.readString();
        mEmail = in.readString();
        mPassword = in.readString();
    }

    public Contact() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

}
